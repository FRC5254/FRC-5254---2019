/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.CargoMechSetIntakeSpeed;
import frc.robot.commands.CargoMechSetToAngle;
import frc.robot.commands.DrivetrainLineUp2;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.easypath.PathUtil;
import frc.robot.easypath.Paths;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;

public class CenterCargoRight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CenterCargoRight() {
    addSequential(new FollowPath(PathUtil.createStraightPath(100.0), 0.25));
    // addSequential(new FollowPath(new Path(t -> 
		// /* {"start":{"x":0,"y":0},"mid1":{"x":37,"y":0},"mid2":{"x":37,"y":-4},"end":{"x":74,"y":-4}} */
		// (24 * Math.pow(t, 2) + -24 * t + 0) / (222 * Math.pow(t, 2) + -222 * t + 111),
    // 74.154), 0.25));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetSliderState(SliderState.OUT));
    addSequential(new HatchMechSetFinState(FinState.UNCLAMPED));
    addSequential(new HatchMechSetKickerState(KickerState.OUT));
    addSequential(new WaitCommand(1));
    // TODO ths path is an unhappy one
    // addSequential(new FollowPath(new Path(t -> 
		// /* {"start":{"x":0,"y":0},"mid1":{"x":40,"y":0},"mid2":{"x":33,"y":22},"end":{"x":68,"y":58}} */
		// (-24 * Math.pow(t, 2) + 132 * t + 0) / (267 * Math.pow(t, 2) + -282 * t + 120),
		// 94.246), 0.25));
    

    addParallel(new CargoMechSetToAngle(0.0));
    addSequential(new FollowPath(Paths.FROM_CENTER.CENETER_RIGHT_HATCH_TO_RIGHT_CARGO_STATION, -0.25));
    addParallel(new CargoMechSetIntakeSpeed(0.5), 0.5);

  }
}
