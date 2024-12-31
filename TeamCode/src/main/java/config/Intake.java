package config;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToSeperatePositions;

import java.util.Map;

public class Intake extends Subsystem {
    public static final Intake INSTANCE =  new Intake();
    private Intake() {}
}
