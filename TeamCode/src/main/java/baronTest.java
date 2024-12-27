import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@TeleOp(name = "barontest")
public class baronTest extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Follower Constants Localizer", FollowerConstants.localizers);
        telemetry.update();
    }

    @Override
    public void start() {
        Follower f = new Follower(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Follower Constants Localizer", FollowerConstants.localizers);
        telemetry.update();
    }
}
