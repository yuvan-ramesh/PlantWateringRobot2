package pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.OTOS;
        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.mass = 5;
        FollowerConstants.xMovement = 57.8741;
        FollowerConstants.yMovement = 52.295;
        FollowerConstants.forwardZeroPowerAcceleration = -41.278;
        FollowerConstants.lateralZeroPowerAcceleration = -59.7819;
        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
        FollowerConstants.translationalPIDFCoefficients = new CustomPIDFCoefficients(0.1,0,0.01,0);
        FollowerConstants.headingPIDFCoefficients = new CustomPIDFCoefficients(2,0,0.1,0);
        FollowerConstants.drivePIDFCoefficients = new CustomFilteredPIDFCoefficients(0.1,0,0,0.6,0);
        FollowerConstants.centripetalScaling = 0.0005;


    }
}
