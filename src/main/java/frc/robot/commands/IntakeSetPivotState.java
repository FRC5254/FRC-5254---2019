/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.PivotState;

/**
 * Add your docs here.
 */
public class IntakeSetPivotState extends InstantCommand {
  /**
   * Add your docs here.
   */
  public IntakeSetPivotState(PivotState state) {
    super(Robot.intake, () -> Robot.intake.setPivotState(state));
  }
}
