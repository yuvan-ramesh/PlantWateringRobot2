package pedroPathing.constants;

import com.pedropathing.localization.constants.OTOSConstants;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

public class LConstants {
    static {
        OTOSConstants.offset = new SparkFunOTOS.Pose2D(3,2,Math.toRadians(270));

    }
}




