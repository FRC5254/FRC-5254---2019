package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * A custom button that is triggered when two buttons on a Joystick are
 * simultaneously pressed.
 * 
 * TODO add this into HJoystick
 */
public class DoubleButton extends Trigger {
	private Joystick joy;
	private int button1;
	private int button2;

	public DoubleButton(Joystick joy, int button1, int button2) {
		this.joy = joy;
		this.button1 = button1;
		this.button2 = button2;
	}

	@Override
	public boolean get() {
		return joy.getRawButton(button1) && joy.getRawButton(button2);
	}
}