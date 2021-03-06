/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMech.KickerState;

/**
 * Add your docs here.
 */
public class HatchMechSetKickerState extends InstantCommand {
  /**
   * Add your docs here.
   */
  public HatchMechSetKickerState(KickerState kickerState) {
    super(Robot.hatchMech, () -> Robot.hatchMech.setKickerState(kickerState));
  }
}
