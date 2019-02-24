/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.utils.HAxisButton.ThresholdType;
import frc.robot.utils.HXboxController.XboxAxis;
import frc.robot.utils.HXboxController.XboxDpad;

/**
 * Add your docs here.
 */
public class HDpad {

    public final Joystick joystick;
    public HAxisButton up;
    public HAxisButton down;
    public HAxisButton left;
    public HAxisButton right;
    public HAxisButton upLeft;
    public HAxisButton upRight;
    public HAxisButton downLeft;
    public HAxisButton downRight;

    public HDpad(Joystick joystick) {
        this.joystick = joystick;
        this.up = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.UP.value, ThresholdType.POV);
        this.down = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.DOWN.value, ThresholdType.POV);
        this.left = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.LEFT.value, ThresholdType.POV);
        this.right = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.RIGHT.value, ThresholdType.POV);

        this.upLeft = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.UP_LEFT.value, ThresholdType.POV);
        this.upRight = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.UP_RIGHT.value, ThresholdType.POV);
        this.downLeft = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.DOWN_LEFT.value, ThresholdType.POV);
        this.downRight = new HAxisButton(joystick, XboxAxis.DPAD, XboxDpad.DOWN_RIGHT.value, ThresholdType.POV);
    }

    public double getValue() {
        return joystick.getRawAxis(XboxAxis.DPAD.value);
    }
}
