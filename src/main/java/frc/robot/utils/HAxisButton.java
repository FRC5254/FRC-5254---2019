/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.utils.HXboxController.XboxAxis;

/**
 * Add your docs here.
 */
public class HAxisButton extends Button {

    static enum ThresholdType {
        EXACT, LESS_THAN, GREATER_THAN, POV; // TODO add a UNKNOWN param?
    }

    private final Joystick joystick;
    private final int axis;
    private double targetValue;
    private ThresholdType thresholdType;

    public HAxisButton(Joystick joystick, int axis, double targetValue, ThresholdType thresholdType) {
        this.joystick = joystick;
        this.axis = axis;
        this.targetValue = targetValue;
        this.thresholdType = thresholdType;
    }

    public HAxisButton(Joystick joystick, XboxAxis axis, double targetValue, ThresholdType thresholdType) {
        this(joystick, axis.value, targetValue, thresholdType);
    }

    public boolean get() {
        switch(this.thresholdType) {
            case EXACT:
                System.out.println("axis value: " + joystick.getRawAxis(this.axis));
                return joystick.getRawAxis(this.axis) == this.targetValue;
            case LESS_THAN:
                return joystick.getRawAxis(this.axis) < this.targetValue;
            case GREATER_THAN:
                return joystick.getRawAxis(this.axis) > this.targetValue;
            case POV:
                return joystick.getPOV() == this.targetValue;
            default:
                return false;
        }
    }

    public void configureThreshold(double targetValue) {
        this.targetValue = targetValue;
    }
}
