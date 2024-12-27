package pedroPathing.constants;

import android.graphics.Path;

import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.OTOSConstants;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D;

public class LConstants {
    static {
        OTOSConstants.offset = new Pose2D(1, 1, 0);
    }
}
