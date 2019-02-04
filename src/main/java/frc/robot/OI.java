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
import frc.robot.commands.CargoMechSetIntake;
import frc.robot.commands.HatchMechCollect;
import frc.robot.commands.HatchMechPlace;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driver = new Joystick(0); //TODO should this be static?

  public double driverLeftJoystickXAxis = driver.getRawAxis(0);
  public double driverLeftJoystickYAxis = driver.getRawAxis(1);
  public double driverLeftTrigger = driver.getRawAxis(2);
  public double driverRightTrigger = driver.getRawAxis(3);
  public double driverRightJoystickXAxis = driver.getRawAxis(4);
  public double driverRightJoystickYAxis = driver.getRawAxis(5);

  public OI() {
    Button aButton = new JoystickButton(driver, 1);
    Button bButton = new JoystickButton(driver, 2);
    Button xButton = new JoystickButton(driver, 3);
    Button yButton = new JoystickButton(driver, 4);


    Button leftBumper = new JoystickButton(driver, 5);
    Button rightBumper =  new JoystickButton(driver, 6);

    aButton.whenPressed(new HatchMechCollect());
    bButton.whenPressed(new HatchMechPlace());
    // yButton.whenPressed(new HatchMechSlide(true));
    // yButton.whenReleased(new HatchMechSlide(false));
    leftBumper.whenPressed(new CargoMechSetIntake(0.5));
    leftBumper.whenReleased(new CargoMechSetIntake(0.0));
    rightBumper.whenPressed(new CargoMechSetIntake(-1.0));
    rightBumper.whenReleased(new CargoMechSetIntake(0.0));

  }
}
