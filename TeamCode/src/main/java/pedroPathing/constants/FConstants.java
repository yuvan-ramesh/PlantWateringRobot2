package pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;

public class FConstants {
    static {
        FollowerConstants.mass = 5;
        FollowerConstants.localizers = Localizers.OTOS;
        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
    }
}
