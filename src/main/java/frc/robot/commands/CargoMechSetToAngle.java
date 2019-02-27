/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class CargoMechSetToAngle extends Command {

  double angle;
  double startAngle;
  Timer sinceStarted;

  public CargoMechSetToAngle(double angle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    // requires(Robot.cargoMech);

    this.angle = angle;
    sinceStarted = new Timer();
    sinceStarted.stop();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // startAngle = Robot.cargoMech.getAngle();
    sinceStarted.reset();
    sinceStarted.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putString("SetToAngle status", "executing");
    Robot.cargoMech.setToAngle(angle);
    // SmartDashboard.putNumber("Cargo set point", Robot.cargoMech.angleToEncoderTicks(angle));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("SetToAngle completion", Robot.cargoMech.isAtAngle());
    SmartDashboard.putNumber("eroor", Robot.cargoMech.getError());

    if (sinceStarted.get() < 0.25) {
      return false;
    }
    
    if (angle < 85) {
      return Robot.cargoMech.isAtAngle() || Robot.cargoMech.atBottomLimit();
    } else if (angle > 5) {
      return Robot.cargoMech.isAtAngle() || Robot.cargoMech.atTopLimit();
    } else {
      return Robot.cargoMech.isAtAngle();
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("SetToAngle status", "end");
    sinceStarted.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
