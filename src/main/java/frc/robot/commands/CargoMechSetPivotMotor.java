/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoMechSetPivotMotor extends Command {

  double speed;
  public CargoMechSetPivotMotor(double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoMech); //TODO how to make it so we can both intake and lift w/ pivot motor?
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoMech.setPivotMotor(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(speed < 0) {
      return Robot.cargoMech.atBottomLimit();
    } else {
      return Robot.cargoMech.atTopLimit();
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(Robot.cargoMech.atBottomLimit()) {
      Robot.cargoMech.zeroEncoder();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
