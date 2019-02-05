/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;

public class HatchMechCollect extends CommandGroup { //TODO combime these sequentials after intial testing
  /**
   * Add your docs here.
   */
  public HatchMechCollect() {
    
    requires(Robot.hatchMech);

    addSequential(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.IN, SliderState.IN));
    addSequential(new HatchMechSetMechState(FinState.UNCLAMPED, KickerState.IN, SliderState.OUT));
    
    addSequential(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.OUT));
    
    addSequential(new HatchMechSetMechState(FinState.CLAMPED, KickerState.IN, SliderState.IN));
    end();//TODO need?
  }
}
