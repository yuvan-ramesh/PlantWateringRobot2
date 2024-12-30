package pedroPathing.tuners_tests.verification;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name = "Triangle")
public class Triangle extends OpMode {
    private ElapsedTime timer = new ElapsedTime();
    private Follower follower;
    private Timer pathTimer;
    private boolean pathStarted = false;

    private final Pose startPose = new Pose(0,0, Math.toRadians(0));
    private final Pose interPose = new Pose(24, -24, Math.toRadians(90));
    private final Pose endPose = new Pose(24, 24, Math.toRadians(45));

    private PathChain path;

    public void buildPaths() {
        path = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(interPose)))
                .setLinearHeadingInterpolation(startPose.getHeading(), interPose.getHeading())
                .addPath(new BezierLine(new Point(interPose), new Point(endPose)))
                .setLinearHeadingInterpolation(interPose.getHeading(), endPose.getHeading())
                .addPath(new BezierLine(new Point(endPose), new Point(startPose)))
                .setLinearHeadingInterpolation(endPose.getHeading(), startPose.getHeading())
                .build();
    }

    public void pathUpdate() {
        if (pathStarted) {
            follower.followPath(path, true);
            setPathStarted(false);
        }
    }
    public void setPathStarted(boolean pathStarted) {
        this.pathStarted = pathStarted;
    }

    @Override
    public void loop() {
        follower.update();

        double elapsedTime = timer.milliseconds();
        timer.reset();
        telemetry.addData("Loop time (ms)", elapsedTime);

        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading (in Radians)", follower.getPose().getHeading());
        telemetry.update();
        pathUpdate();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void start() {
        pathTimer.resetTimer();
        setPathStarted(true);
    }

}
