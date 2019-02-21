/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.utils.HXboxController.XboxButton;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Add your docs here.
 */
public class HButton extends JoystickButton {

    public HButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
    }

    public HButton(XboxController joystick, XboxButton button) {
        super(joystick, button.value);
    }

    public HButton(HXboxController joystick, XboxButton button) {
        super(joystick, button.value);
    }
}
