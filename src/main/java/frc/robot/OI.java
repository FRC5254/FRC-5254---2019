/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.CargoMechIntake;
import frc.robot.commands.CargoMechOuttake;
import frc.robot.commands.CargoMechSetToAngle;
import frc.robot.commands.ClimberLineUp;
import frc.robot.commands.ClimberSetMode;
import frc.robot.commands.ClimberSetSpeed1;
import frc.robot.commands.DrivetrainDriveWithJoystick;
import frc.robot.commands.DrivetrainLineUp;
import frc.robot.commands.DrivetrainSetShiftState;
import frc.robot.commands.HatchMechPlace;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetMechState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.subsystems.Climber.ClimberMode;
import frc.robot.subsystems.Drivetrain.ShiftState;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;
import frc.robot.utils.HXboxController;
import frc.robot.utils.Limelight;
import frc.robot.utils.Limelight.CamMode;

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
    // driver.aButton.whenPressed(new CargoMechSetIntakeSpeed(-1.0)); // Shoot ball
    // driver.aButton.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    driver.aButton.whenPressed(new DrivetrainLineUp());
    driver.aButton.whenReleased(new DrivetrainDriveWithJoystick());
    driver.bButton.whenPressed(new HatchMechSetKickerState(KickerState.OUT));// Place hatch
    driver.bButton.whenReleased(new HatchMechSetKickerState(KickerState.IN));
    driver.xButton.whenPressed(new DrivetrainSetShiftState(ShiftState.LOW_GEAR)); //shift
    driver.xButton.whenReleased(new DrivetrainSetShiftState(ShiftState.HIGH_GEAR));
    driver.yButton.whenPressed(new ClimberLineUp()); // Pistions for climber
    driver.leftBumper.whenPressed(new ClimberSetSpeed1(1.0)); // Climb
    driver.leftBumper.whenReleased(new ClimberSetSpeed1(0.0)); // note safety is on right bumper the command does nothing until thats pressed
    driver.startButton.whenPressed(new ClimberSetMode(ClimberMode.SECURED_MODE));
    driver.backButton.whenPressed(new ClimberSetSpeed1(-0.5));
    driver.backButton.whenReleased(new ClimberSetSpeed1(0.0));

    driver.dpad.up.whenPressed(new InstantCommand(() -> Limelight.setCamMode(CamMode.DRIVER_CAM)));
    driver.dpad.up.whenReleased(new InstantCommand(() -> Limelight.setCamMode(CamMode.VISION_CAM)));
    
    // Operator
    operator.leftTriggerButton.configureThreshold(0.2);
    operator.rightTriggerButton.configureThreshold(0.2);

    // operator.aButton.whenPressed(new HatchMechSetFinState(FinState.UNCLAMPED)); // Fins unclamp when pressed
    // operator.aButton.whenReleased(new HatchMechSetFinState(FinState.CLAMPED));
    // operator.bButton.whenPressed(new HatchMechSetKickerState(KickerState.OUT)); // Kicker out whne pressed
    // operator.bButton.whenReleased(new HatchMechSetKickerState(KickerState.IN));
    // operator.xButton.whenPressed(new HatchMechSetSliderState(SliderState.IN)); // Slider in
    // operator.yButton.whenPressed(new HatchMechSetSliderState(SliderState.OUT)); // Slider out

    // New controls
    // operator.aButton.whenPressed(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.IN, SliderState.OUT));
    // operator.aButton.whenReleased(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.IN));
    // operator.bButton.whenPressed(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.OUT, SliderState.OUT));
    // operator.bButton.whenReleased(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.IN));
    // or 

    // This
    operator.aButton.whenPressed(new HatchMechSetFinState(FinState.UNCLAMPED)); // Fins unclamp when pressed
    operator.aButton.whenReleased(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.IN));
    operator.bButton.whenPressed(new HatchMechPlace());
    operator.bButton.whenReleased(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.IN));
    operator.xButton.whenPressed(new HatchMechSetSliderState(SliderState.IN)); // Slider in
    operator.yButton.whenPressed(new HatchMechSetSliderState(SliderState.OUT)); // Slider out
    // That
    
    operator.rightBumper.whenPressed(new CargoMechIntake(0.65)); // Intake when Pressed
    operator.leftBumper.whenPressed(new CargoMechOuttake(-1.0)); // Outtake when pressed
    operator.leftBumper.whenReleased(new CargoMechOuttake(0.0));

    // operator.startButton.whenPressed(new CargoMechSetPivotMotor(1.0)); // Cargo down (TODO should invert motors so polarity reflects intaking and outtaking)
    // operator.backButton.whenPressed(new CargoMechSetPivotMotor(-1.0)); //Cargo up
    operator.startButton.whenPressed(new CargoMechSetToAngle(0));
    operator.backButton.whenPressed(new CargoMechSetToAngle(90));

    operator.rjc.whenPressed(new CargoMechSetToAngle(50));

    operator.leftTriggerButton.whenPressed(new CargoMechOuttake(1.0));
    operator.leftTriggerButton.whenReleased(new CargoMechOuttake(0.0));
    
    operator.leftTriggerButton.whenPressed(new CargoMechOuttake(1.0));
    operator.leftTriggerButton.whenReleased(new CargoMechOuttake(0.0));
  
    // operator.dpad.left.whenPressed(new CargoMechSetToAngle(70)); // TODO this doesnt work?
   
    operator.dpad.up.whenPressed(new InstantCommand(() -> Limelight.setCamMode(CamMode.DRIVER_CAM)));
    operator.dpad.up.whenReleased(new InstantCommand(() -> Limelight.setCamMode(CamMode.VISION_CAM)));
  }
}

