/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoMechSetIntakeSpeed;
import frc.robot.commands.CargoMechSetToAngle;
import frc.robot.commands.CargoMechSetPivotMotor;
import frc.robot.commands.DrivetrainSetManipulationMode;
import frc.robot.commands.DrivetrainSetShiftState;
import frc.robot.commands.HatchMechCollect;
import frc.robot.commands.HatchMechPlace;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.subsystems.Drivetrain.ManipulationMode;
import frc.robot.subsystems.Drivetrain.ShiftState;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;
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
    driver.leftBumper.whenPressed(new DrivetrainSetShiftState(ShiftState.LOW_GEAR));
    driver.rightBumper.whenPressed(new DrivetrainSetShiftState(ShiftState.HIGH_GEAR));

    driver.ljc.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.PANEL));
    driver.rjc.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.CARGO));

    // Operator
    operator.leftTriggerButton.configureThreshold(0.01);
    
    // operator.aButton.whenPressed(new CargoMechSetPivotMotor(-1.0));
    // operator.aButton.whenReleased(new CargoMechSetPivotMotor(0.0));
    // operator.bButton.whenPressed(new CargoMechSetPivotMotor(1.0));
    // operator.bButton.whenPressed(new CargoMechSetPivotMotor(0.0));

    operator.aButton.whenPressed(new HatchMechSetFinState(FinState.UNCLAMPED));
    operator.aButton.whenReleased(new HatchMechSetFinState(FinState.CLAMPED));
    operator.bButton.whenPressed(new HatchMechSetKickerState(KickerState.OUT));
    operator.bButton.whenReleased(new HatchMechSetKickerState(KickerState.IN));
    operator.xButton.whenPressed(new HatchMechSetSliderState(SliderState.IN));
    operator.yButton.whenPressed(new HatchMechSetSliderState(SliderState.OUT));

    // operator.leftTriggerButton.whenPressed(new CargoMechSetIntakeSpeed(0.5));
    // operator.rightTriggerButton.whenPressed(new CargoMechSetIntakeSpeed(-1.0));

    // operator.backButton.whenPressed(new HatchMechPlace());
    // operator.startButton.whenPressed(new HatchMechCollect());

    operator.rightBumper.whenPressed(new CargoMechSetIntakeSpeed(0.5));
    operator.rightBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    operator.leftBumper.whenPressed(new CargoMechSetIntakeSpeed(-1.0));
    operator.leftBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));

    // operator.ljc.whenPressed(new CargoMechSetPivotMotor(1.0));
    // operator.rjc.whenPressed(new CargoMechSetPivotMotor(-1.0));

      // operator.dpad.up.whenPressed(new HatchMechCollect());
  }
}
