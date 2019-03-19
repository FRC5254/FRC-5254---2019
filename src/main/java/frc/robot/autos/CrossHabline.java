/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;

public class CrossHabline extends CommandGroup {
  /**
   * TODO is it smarter to precode paths here?
   */
  public CrossHabline(double wait, Path path) {
    
    addSequential(new WaitCommand(wait));
    addSequential(new FollowPath(path, 0.5));
  }
}
