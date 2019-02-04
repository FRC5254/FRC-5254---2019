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

public class HatchMechSetMechState extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchMechSetMechState(FinState finState, KickerState kickerState, SliderState sliderState) {
    if(Robot.hatchMech.finState != finState) {
      addSequential(new HatchMechSetFinState(finState));
    }

    if (Robot.hatchMech.kickerState != kickerState){
      addSequential(new HatchMechSetKickerState(kickerState));
    }

    if(Robot.hatchMech.sliderState != sliderState) {
      addSequential(new HatchMechSetSliderState(sliderState));
    }
  }
}
