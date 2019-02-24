/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.utils.HAxisButton.ThresholdType;

/**
 * Add your docs here.
 */
public class HXboxController extends Joystick {

    static enum XboxButton {
        A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6),
        BACK(7), START(8), LJC(9), RJC(10);

        final int value;
        XboxButton(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static enum XboxAxis {
        LEFT_X(0), LEFT_Y(1), LEFT_TRIGGER(2), RIGHT_TRIGGER(3),
        RIGHT_X(4), RIGHT_Y(5), DPAD(6);

        final int value;
        XboxAxis(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    static enum XboxDpad {
        UNPRESSED(-1), UP(1), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN (180),
        DOWN_LEFT(225), LEFT(270), UP_LEFT(315);

        final int value;
        XboxDpad(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public HXboxController(int port) {
        super(port);
    }

    public HXboxController(int port, double xDeadband, double yDeadband) {
        this(port);
        this.leftStick.setDeadbands(xDeadband, yDeadband);
        this.rightStick.setDeadbands(xDeadband, yDeadband);
    }

    public HButton aButton = new HButton(this, XboxButton.A);
    public HButton bButton = new HButton(this, XboxButton.B);
    public HButton xButton = new HButton(this, XboxButton.X);
    public HButton yButton = new HButton(this, XboxButton.Y);

    public HButton leftBumper = new HButton(this, XboxButton.LEFT_BUMPER);
    public HButton rightBumper = new HButton(this, XboxButton.RIGHT_BUMPER);

    public HButton backButton = new HButton(this, XboxButton.BACK);
    public HButton startButton = new HButton(this, XboxButton.START);

    public HButton ljc = new HButton(this, XboxButton.LJC);
    public HButton rjc = new HButton(this, XboxButton.RJC);

    public HAxisButton leftTriggerButton = new HAxisButton(this, XboxAxis.LEFT_TRIGGER, 0.25, ThresholdType.GREATER_THAN);
    public HAxisButton rightTriggerButton = new HAxisButton(this, XboxAxis.RIGHT_TRIGGER, 0.25, ThresholdType.GREATER_THAN);

    public HDpad dpad = new HDpad(this);

    public HThumbStick leftStick = new HThumbStick(this, XboxAxis.LEFT_X, XboxAxis.LEFT_Y);
    public HThumbStick rightStick = new HThumbStick(this, XboxAxis.RIGHT_X, XboxAxis.RIGHT_Y);

    public HTriggers triggers = new HTriggers(this);

    public void setRumble(double leftValue, double rightValue) {
        setRumble(RumbleType.kLeftRumble, leftValue);
        setRumble(RumbleType.kRightRumble, rightValue);
    }
}
