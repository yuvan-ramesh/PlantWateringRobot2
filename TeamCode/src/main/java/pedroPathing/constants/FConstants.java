package pedroPathing.constants;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;

@Config
public class FConstants {
    static {
        FollowerConstants.mass = 5;
        FollowerConstants.localizers = Localizers.OTOS;
        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
    }
}
