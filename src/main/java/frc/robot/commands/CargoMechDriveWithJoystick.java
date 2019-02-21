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
    double upAxis = OI.operator.triggers.getLeft();
    double downAxis = OI.operator.triggers.getRight();
   
    if(upAxis > 0.1) {
      Robot.cargoMech.setPivotMotor(upAxis);//  * 0.25
    } else if(-downAxis < -0.1) {
      Robot.cargoMech.setPivotMotor(-downAxis);
    } else {
      Robot.cargoMech.setPivotMotor(0.0);
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
