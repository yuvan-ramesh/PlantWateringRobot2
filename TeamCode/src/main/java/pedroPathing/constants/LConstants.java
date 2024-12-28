package pedroPathing.constants;

import android.graphics.Path;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Encoder;
import com.pedropathing.localization.constants.OTOSConstants;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS.Pose2D;

@Config
public class LConstants {
    static {
        OTOSConstants.offset = new Pose2D(3, 2, (3  *Math.PI) / 2);
    }
}
