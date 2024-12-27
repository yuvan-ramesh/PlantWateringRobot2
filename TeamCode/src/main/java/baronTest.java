import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

public class baronTest extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Follower Constants Localizer", FollowerConstants.localizers);
        telemetry.update();
    }

    @Override
    public void start() {
        Follower f = new Follower(hardwareMap, FConstants.class, LConstants.class);
    }

    @Override
    public void loop() {
        telemetry.addData("Follower Constants Localizer", FollowerConstants.localizers);
        telemetry.update();
    }
}
