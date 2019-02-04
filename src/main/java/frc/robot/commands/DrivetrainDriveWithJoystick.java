/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DrivetrainDriveWithJoystick extends Command {
  public DrivetrainDriveWithJoystick() {
    requires(Robot.drivetrain);
}

// Called just before this Command runs the first time
protected void initialize() {
}

// Called repeatedly when this Command is scheduled to run
protected void execute() {
//    	double throttle = Robot.oi.driver.getRawAxis(1);
//    	double turn = -Robot.oi.driver.getRawAxis(4);
//    	Robot.drivetrain.arcadeDrive(throttle, turn);
  
  
  Robot.drivetrain.customDrive(
      Robot.m_oi.driverLeftTrigger,
      Robot.m_oi.driverRightTrigger,
      Robot.m_oi.driverRightJoystickXAxis
  );
  
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished() {
    return false;
}

// Called once after isFinished returns true
protected void end() {
  Robot.drivetrain.arcadeDrive(0, 0);
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
  end();
}
}
