/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.utils.Limelight;
import frc.robot.utils.Limelight.CamMode;
import frc.robot.utils.Limelight.Pipeline;

public class DrivetrainLineUp2 extends Command {
  public DrivetrainLineUp2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Limelight.setCamMode(CamMode.VISION_CAM);
    Limelight.setSnapshotMode(Limelight.SnapshotMode.TWO_PER_SECOND);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.LineUp();
    SmartDashboard.putBoolean("Lineup", false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drivetrain.LineUpIsFinished()
    ;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.setLeftRightSpeeds(0.0, 0.0);
    SmartDashboard.putNumber("lastvertical", Limelight.getVerticalOffset());
    Limelight.setSnapshotMode(Limelight.SnapshotMode.TWO_PER_SECOND);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
