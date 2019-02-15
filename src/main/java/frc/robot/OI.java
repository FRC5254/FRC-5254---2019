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
import frc.robot.commands.DrivetrainDriveWithJoystick;
import frc.robot.commands.DrivetrainLineUp;
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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driver = new Joystick(0);
  public static Joystick operator = new Joystick(1);

  public static final int DRIVER_LEFT_JOYSTICK_X_AXIS = 0;
  public static final int DRIVER_LEFT_JOYSTICK_Y_AXIS = 1;
  public static final int DRIVER_LEFT_TRIGGER = 2;
  public static final int DRIVER_RIGHT_TRIGGER = 3;
  public static final int DRIVER_RIGHT_JOYTICK_X_AXIS = 4;
  public static final int DRIVER_RIGHT_JOYSTICK_Y_AXIS = 5;

  public OI() {

    Button aButton = new JoystickButton(driver, 1);
    Button bButton = new JoystickButton(driver, 2);
    Button xButton = new JoystickButton(driver, 3);
    Button yButton = new JoystickButton(driver, 4);
    Button leftBumper = new JoystickButton(driver, 5);
    Button rightBumper = new JoystickButton(driver, 6);
    Button backButton = new JoystickButton(driver, 7);
    Button startButton = new JoystickButton(driver, 8);
    Button leftJoystickClick = new JoystickButton(driver, 9);
    Button rightJoystickClick = new JoystickButton(driver, 10);

    aButton.whenPressed(new DrivetrainLineUp());
    aButton.whenReleased(new DrivetrainDriveWithJoystick());//TODO insant command?
    // bButton.whenPressed(new DrivetrainSquareUp());
    leftBumper.whenPressed(new DrivetrainSetShiftState(ShiftState.HIGH_GEAR));
    rightBumper.whenPressed(new DrivetrainSetShiftState(ShiftState.LOW_GEAR));
    // leftBumper.whenPressed(new CargoMechSetIntakeSpeed(0.5));
    // leftBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    // rightBumper.whenPressed(new CargoMechSetIntakeSpeed(-1.0));
    // rightBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    backButton.whenPressed(new HatchMechPlace());
    startButton.whenPressed(new HatchMechCollect());
    leftJoystickClick.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.PANEL));
    rightJoystickClick.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.CARGO));


  }
}
