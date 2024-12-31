package config;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

public class Lift extends Subsystem {
    public static final Lift INSTANCE =  new Lift();
    private Lift() {}
    public MotorEx left, right;
    public MotorGroup lift;
    public PIDFController controller = new PIDFController(0.01,0,0,0.005);
    public String leftName = "leftLift", rightName = "rightLift";

    public Command toHighBucket() {
        return new RunToPosition(lift, 1200.0, controller, this);
    }

    public Command toLowBucket() {
        return new RunToPosition(lift, 600.0, controller, this);
    }

    public Command toZero() {
        return new RunToPosition(lift, 10, controller, this);
    }

    @Override
    public void initialize() {
        left = new MotorEx(leftName);
        right = new MotorEx(rightName);
        left.getMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        right.getMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        lift = new MotorGroup(right,left);
    }
}
