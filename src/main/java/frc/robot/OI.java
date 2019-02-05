/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static org.junit.Assume.assumeNoException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CargoMechSetIntake;
import frc.robot.commands.CargoMechSetIntakeSpeed;
import frc.robot.commands.DrivetrainSetManipulationMode;
import frc.robot.commands.HatchMechCollect;
import frc.robot.commands.HatchMechPlace;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.subsystems.Drivetrain.ManipulationMode;
import frc.robot.subsystems.HatchMech.FinState;

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

  private static OI instance = new OI();

  private OI() {

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

    aButton.whenPressed(new HatchMechCollect());
    bButton.whenPressed(new HatchMechPlace());
    xButton.whenPressed(new HatchMechSetFinState(FinState.CLAMPED));
    // yButton.whenPressed(new HatchMechSlide(true));
    // yButton.whenReleased(new HatchMechSlide(false));
    leftBumper.whenPressed(new CargoMechSetIntakeSpeed(0.5));
    leftBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    rightBumper.whenPressed(new CargoMechSetIntakeSpeed(-1.0));
    rightBumper.whenReleased(new CargoMechSetIntakeSpeed(0.0));
    leftJoystickClick.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.PANEL));
    rightJoystickClick.whenPressed(new DrivetrainSetManipulationMode(ManipulationMode.CARGO));


  }

  public static OI getInstance() {
    return instance;
  }
}
