/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.PathUtil;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;

public class HatchMechPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchMechPlace() {  
    addSequential(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.OUT));
    addSequential(new WaitCommand(0.1));
    addSequential(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.OUT, SliderState.OUT));
    // addSequential(new WaitCommand(1));
    // addSequential(new FollowPath(PathUtil.createStraightPath(15), -.25));
    // addSequential(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.IN, SliderState.IN));

  }
}
