/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.PathUtil;
import frc.robot.subsystems.Climber.ClimberMode;

public class ClimberLineUp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public 
  ClimberLineUp() {
    // addSequential(command);
    addSequential(new FollowPath(PathUtil.createStraightPath(6.0), -0.075)); // from frame 9.5
    addSequential(new ClimberSetMode(ClimberMode.CLIMB_MODE));
  }
}
