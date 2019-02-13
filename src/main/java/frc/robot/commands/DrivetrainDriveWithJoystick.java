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
import frc.robot.subsystems.Drivetrain.ManipulationMode;
import frc.robot.utils.HXboxController;

public class DrivetrainDriveWithJoystick extends Command {
  public DrivetrainDriveWithJoystick() {
    requires(Robot.drivetrain);
}

// Called just before this Command runs the first time
protected void initialize() {
}

// Called repeatedly when this Command is scheduled to run
protected void execute() { 
  if(Robot.drivetrain.driverControls == DriverControls.ARCADE){
    double throttle = OI.driver.leftStick.getY();
    double turn = OI.driver.rightStick.getX();

    Robot.drivetrain.arcadeDrive(throttle, turn);
  }
  
  if(Robot.drivetrain.manipulationMode == ManipulationMode.PANEL) {
    if(Robot.drivetrain.driverControls == DriverControls.GTA_DRIVE) {
      Robot.drivetrain.GTADrive(
        OI.driver.triggers.getLeft(),
        OI.driver.triggers.getRight(),
        -(OI.driver.leftStick.getX())
      );
    }
  } else if (Robot.drivetrain.manipulationMode == ManipulationMode.CARGO){
    if(Robot.drivetrain.driverControls == DriverControls.GTA_DRIVE) {
      Robot.drivetrain.GTADrive(
        OI.driver.triggers.getLeft(),
        OI.driver.triggers.getRight(),
        OI.driver.leftStick.getX()
      );
    }
  } else {}
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
