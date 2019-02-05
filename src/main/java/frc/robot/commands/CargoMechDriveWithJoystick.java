/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.DriverControls;

public class CargoMechDriveWithJoystick extends Command {

  public CargoMechDriveWithJoystick() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoMech);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { //TODO move most if not all of this into the subsystem

    if(Robot.drivetrain.driverControls == DriverControls.GTA_DRIVE){
      double axis = OI.driver.getRawAxis(OI.DRIVER_LEFT_JOYSTICK_Y_AXIS);
      if(axis > 0.1 || axis < -0.1) {
      Robot.cargoMech.setPivotMotor(axis * 0.25);
     }
    }

    if(Robot.drivetrain.driverControls == DriverControls.ARCADE){
      double leftTrigger = OI.driver.getRawAxis(OI.DRIVER_LEFT_TRIGGER);
      double rightTrigger = OI.driver.getRawAxis(OI.DRIVER_RIGHT_TRIGGER);
     
     if(leftTrigger > 0.1) {
        Robot.cargoMech.setPivotMotor(leftTrigger * 0.25);
      }

      if(rightTrigger > 0.1) {
        Robot.cargoMech.setPivotMotor(-rightTrigger * 0.25);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
