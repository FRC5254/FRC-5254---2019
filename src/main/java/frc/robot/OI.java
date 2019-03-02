/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.CargoMechSetIntakeSpeed;
import frc.robot.commands.CargoMechSetPivotMotor;
import frc.robot.commands.CargoMechSetToAngle;
import frc.robot.commands.ClimberSetMode;
import frc.robot.commands.ClimberSetSpeed;
import frc.robot.commands.ClimberSetSpeed1;
import frc.robot.commands.DrivetrainDriveWithJoystick;
import frc.robot.commands.DrivetrainLineUp;
import frc.robot.commands.DrivetrainSetShiftState;
import frc.robot.commands.HatchMechPlace;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.subsystems.Climber.ClimberMode;
import frc.robot.subsystems.Drivetrain.ShiftState;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;
import frc.robot.utils.DoubleButton;
import frc.robot.utils.HXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static HXboxController driver; 
  public static HXboxController operator;

  public OI() {

    driver = new HXboxController(0);
    operator = new HXboxController(1);

    // Driver
    // Proposing
    // driver.aButton.whenPressed(new CargoMechSetIntakeSpeed(-1.0)); // Shoot ball
    // driver.aButton.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    driver.bButton.whenPressed(new HatchMechSetKickerState(KickerState.OUT));// Place hatch
    driver.bButton.whenReleased(new HatchMechSetKickerState(KickerState.IN));
    driver.xButton.whenPressed(new DrivetrainSetShiftState(ShiftState.LOW_GEAR)); //shift
    driver.xButton.whenReleased(new DrivetrainSetShiftState(ShiftState.HIGH_GEAR));
    driver.yButton.whenPressed(new ClimberSetMode(ClimberMode.CLIMB_MODE)); // Pistions for climber
    driver.leftBumper.whenPressed(new ClimberSetSpeed1(-1.0)); // Climb
    driver.leftBumper.whenReleased(new ClimberSetSpeed1(0.0)); // note safety is on right bumper the command does nothing until thats pressed

    

    // Used for testing on monday
    // driver.aButton.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.CARGO));
    // driver.bButton.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.PANEL));
    // driver.xButton.whenPressed(new DrivetrainSetShiftState(ShiftState.LOW_GEAR));
    // driver.xButton.whenReleased(new DrivetrainSetShiftState(ShiftState.HIGH_GEAR));

    // driver.leftBumper.whenPressed(new ClimberSetSpeed(1.0));
    // driver.leftBumper.whenReleased(new ClimberSetSpeed(0.0));


    // Operator
    operator.aButton.whenPressed(new HatchMechSetFinState(FinState.UNCLAMPED)); // Fins unclamp when pressed
    operator.aButton.whenReleased(new HatchMechSetFinState(FinState.CLAMPED));
    driver.aButton.whenPressed(new DrivetrainLineUp());
    driver.aButton.whenReleased(new DrivetrainDriveWithJoystick());
    operator.bButton.whenPressed(new HatchMechSetKickerState(KickerState.OUT)); // Kicker out whne pressed
    operator.bButton.whenReleased(new HatchMechSetKickerState(KickerState.IN));
    operator.xButton.whenPressed(new HatchMechSetSliderState(SliderState.IN)); // Slider in
    operator.yButton.whenPressed(new HatchMechSetSliderState(SliderState.OUT)); // Slider out
    // operator.startButton.whenPressed(new CargoMechSetPivotMotor(1.0)); // Cargo down (TODO should invert motors so polarity reflects intaking and outtaking)
    // operator.backButton.whenPressed(new CargoMechSetPivotMotor(-1.0)); //Cargo up
    operator.rightBumper.whenPressed(new CargoMechSetIntakeSpeed(0.65)); // Intake when Pressed
    // operator.rightBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    operator.leftBumper.whenPressed(new CargoMechSetIntakeSpeed(-0.75)); // Outtake when pressed
    operator.leftBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));

    operator.startButton.whenPressed(new CargoMechSetToAngle(0));
    operator.backButton.whenPressed(new CargoMechSetToAngle(90));
    // Used for testing on monday
    // operator.startButton.whenPressed(new ClimberSetMode(ClimberMode.CLIMB_MODE));

    operator.dpad.up.whenPressed(new CargoMechSetToAngle(45)); // TODO this doesnt work
  }
}

