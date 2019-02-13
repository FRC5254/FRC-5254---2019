/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.utils.HXboxController.XboxAxis;

/**
 * Add your docs here.
 */
public class HTriggers {

    Joystick joystick;

    public HTriggers(Joystick joystick) {
        this.joystick = joystick;
    }

    public double getLeft() {
        return this.joystick.getRawAxis(XboxAxis.LEFT_TRIGGER.value);
    }

    public double getRight() {
        return this.joystick.getRawAxis(XboxAxis.RIGHT_TRIGGER.value);
    }

    public double getTwist() {
        return -getLeft() + getRight();
    }
}
