/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DrivetrainLineUp2;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;

public class CenterHatchPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CenterHatchPlace(Path crossHabline, Path centerHatch) {
    addSequential(new FollowPath(crossHabline, 0.5));
    addSequential(new FollowPath(centerHatch, 0.25));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetSliderState(SliderState.OUT)); // TODO setMechState? or place command?
    addSequential(new HatchMechSetFinState(FinState.UNCLAMPED));
    addSequential(new HatchMechSetKickerState(KickerState.OUT));
  }
}
