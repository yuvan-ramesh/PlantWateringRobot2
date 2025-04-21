package auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.pedropathing.localization.Pose;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

import java.util.LinkedList;
import java.util.Queue;

@Autonomous(name = "Obstacle Mapping Auto", group = "Autonomous")
public class ObstacleMapping extends LinearOpMode {
    private Rev2mDistanceSensor laserSensor;
    private Servo servo;

    private static final int GRID_SIZE = 20;
    private static final double CELL_SIZE = 10.0;
    private static int[][] grid = new int[GRID_SIZE][GRID_SIZE];

    private Follower follower;

    @Override
    public void runOpMode() {
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);

        laserSensor = hardwareMap.get(Rev2mDistanceSensor.class, "laserSensor");
        servo = hardwareMap.get(Servo.class, "servo");

        waitForStart();

        while (opModeIsActive()) {
            scanSurroundings();
            moveToSafeLocation();
        }

        printGrid();
    }

    private void scanSurroundings() {
        Pose currentPose = follower.getPose();
        double robotHeading = currentPose.getHeading();

        for (int angle = 0; angle <= 180; angle += 10) {
            servo.setPosition(angle / 180.0);
            sleep(200);

            double distance = laserSensor.getDistance(DistanceUnit.CM);
            if (distance > 0 && distance < 200) {
                int[] obstaclePos = convertPolarToCartesian(distance, Math.toRadians(angle + robotHeading));
                markObstacle(obstaclePos[0], obstaclePos[1]);
            }
        }
    }

    private int[] convertPolarToCartesian(double distance, double angleRad) {
        Pose pose = follower.getPose();
        double x = pose.getX() + distance * Math.cos(angleRad);
        double y = pose.getY() + distance * Math.sin(angleRad);
        return new int[]{(int) (x / CELL_SIZE), (int) (y / CELL_SIZE)};
    }

    private void markObstacle(int x, int y) {
        if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
            grid[x][y] = 1;
        }
    }

    private void moveToSafeLocation() {
        Pose currentPose = follower.getPose();
        int robotGridX = (int) (currentPose.getX() / CELL_SIZE);
        int robotGridY = (int) (currentPose.getY() / CELL_SIZE);

        int[] newPosition = findClosestOpenSpace(robotGridX, robotGridY);
        if (newPosition != null) {
            double targetX = newPosition[0] * CELL_SIZE;
            double targetY = newPosition[1] * CELL_SIZE;

            Point start = new Point(currentPose.getX(), currentPose.getY());
            Point end = new Point(targetX, targetY);

            PathBuilder builder = new PathBuilder();
            builder.addBezierLine(start, end).setConstantHeadingInterpolation(currentPose.getHeading());
            PathChain path = builder.build();

            follower.followPath(path);

            // Continuously update follower while it's busy and opMode is active
            while (follower.isBusy() && opModeIsActive()) {
                follower.update();
                telemetry.addData("Moving to", "(" + newPosition[0] + ", " + newPosition[1] + ")");
                telemetry.update();
            }
        } else {
            telemetry.addData("No safe space found!", "Robot remains in place.");
            telemetry.update();
        }
    }

    private int[] findClosestOpenSpace(int startX, int startY) {
        boolean[][] visited = new boolean[GRID_SIZE][GRID_SIZE];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE && !visited[x][y]) {
                visited[x][y] = true;

                if (grid[x][y] == 0) return new int[]{x, y};

                for (int[] dir : directions) {
                    queue.add(new int[]{x + dir[0], y + dir[1]});
                }
            }
        }
        return null;
    }

    private void printGrid() {
        StringBuilder rowOutput = new StringBuilder();
        for (int y = 0; y < GRID_SIZE; y++) {
            rowOutput.setLength(0);
            for (int x = 0; x < GRID_SIZE; x++) {
                rowOutput.append(grid[x][y] == 1 ? "X " : ". ");
            }
            telemetry.addData("Row " + y, rowOutput.toString());
        }
        telemetry.update();
    }
}
