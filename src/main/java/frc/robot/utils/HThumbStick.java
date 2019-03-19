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
public class HThumbStick {

    Joystick joystick;
    XboxAxis xAxis;
    XboxAxis yAxis;
    double xDeadband = 0.0;
    double yDeadband = 0.0;

    public HThumbStick(Joystick joystick, XboxAxis xAxis, XboxAxis yAxis) {
        this.joystick = joystick;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public HThumbStick(Joystick joystick, XboxAxis xAxis, XboxAxis yAxis, double xDeadband, double yDeadband) {
        this(joystick, xAxis, yAxis);
        this.xDeadband = xDeadband;
        this.yDeadband =yDeadband;
    }

    public double getX() {
        double value = this.joystick.getRawAxis(xAxis.value);
        return handleDeadband(value, xDeadband);
    }

    public double getY() {
        double value = this.joystick.getRawAxis(yAxis.value);
        return handleDeadband(value, yDeadband);
    }

    public void setXDeadband(double deadband) {
        this.xDeadband = deadband;
    }

    public void setYDeadband(double deadband) {
        this.yDeadband = deadband;
    }

    public void setDeadbands(double xDeadband, double yDeadband) {
        setXDeadband(yDeadband);
        setXDeadband(xDeadband);
    }

    public double handleDeadband(double input, double deadband) {
        if (input > -deadband && input < deadband) {
            return 0;
        } else {
            return input;
        }
    }
}
