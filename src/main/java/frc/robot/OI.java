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
import frc.robot.commands.HatchMechClamp;
import frc.robot.commands.HatchMechHalo;
import frc.robot.commands.HatchMechSlide;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driver = new Joystick(0);

  public OI() {
    Button aButton = new JoystickButton(driver, 1);
    Button bButton = new JoystickButton(driver, 2);
    Button xButton = new JoystickButton(driver, 3);
    Button yButton = new JoystickButton(driver, 4);


    Button leftBumper = new JoystickButton(driver, 5);
    Button rightBumper =  new JoystickButton(driver, 6);

    aButton.whenPressed(new HatchMechClamp(true));
    aButton.whenReleased(new HatchMechClamp(false));
    xButton.whenPressed(new HatchMechHalo(false));
    xButton.whenReleased(new HatchMechHalo(true));
    yButton.whenPressed(new HatchMechSlide(true));
    yButton.whenReleased(new HatchMechSlide(false));
    leftBumper.whenPressed(new CargoMechSetIntake(0.5));
    leftBumper.whenReleased(new CargoMechSetIntake(0.0));
    rightBumper.whenPressed(new CargoMechSetIntake(-1.0));
    rightBumper.whenReleased(new CargoMechSetIntake(0.0));

  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
