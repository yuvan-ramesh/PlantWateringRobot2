package pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.tuning.FollowerConstants;

public class FConstants {
    static {
        FollowerConstants.mass = 13;
        FollowerConstants.localizers = Localizers.THREE_WHEEL;
        FollowerConstants.zeroPowerAccelerationMultiplier = 4;
    }
}
