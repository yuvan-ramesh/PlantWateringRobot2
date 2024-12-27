package pedroPathing.constants;

import android.graphics.Path;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.ThreeWheelConstants;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class LConstants {
    static {
        ThreeWheelConstants.forwardTicksToInches = 0.0019;
        ThreeWheelConstants.strafeTicksToInches = 0.0019;
        ThreeWheelConstants.turnTicksToInches = 0.0019;
        ThreeWheelConstants.leftY = 0;
        ThreeWheelConstants.rightY = 0;
        ThreeWheelConstants.strafeX = -1;
        ThreeWheelConstants.leftEncoder_HardwareMapName = "leftFront";
        ThreeWheelConstants.rightEncoder_HardwareMapName = "rightRear";
        ThreeWheelConstants.strafeEncoder_HardwareMapName = "leftRear";
        ThreeWheelConstants.leftEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.rightEncoderDirection = Encoder.FORWARD;
        ThreeWheelConstants.strafeEncoderDirection = Encoder.REVERSE;
    }
}
