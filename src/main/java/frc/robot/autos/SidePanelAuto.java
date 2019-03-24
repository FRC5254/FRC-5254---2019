/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.DrivetrainLineUp2;
import frc.robot.commands.HatchMechCollect;
import frc.robot.commands.HatchMechPlace;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.easypath.PathUtil;
import frc.robot.subsystems.HatchMech.SliderState;

public class SidePanelAuto extends CommandGroup {
  /**
   * This auto only works on the right side!
   */
  public SidePanelAuto() {
    addSequential(new FollowPath(PathUtil.createStraightPath(23.0), 0.1));
    addSequential(new WaitCommand(0.5));
    addSequential(new FollowPath(new Path(t -> 
		/* {"start":{"x":76,"y":111},"mid1":{"x":120,"y":111},"mid2":{"x":259,"y":270},"end":{"x":259,"y":119}} */
		(-1407 * Math.pow(t, 2) + 954 * t + 0) / (-702 * Math.pow(t, 2) + 570 * t + 132),
		249.6), x -> {
			if (x < 0.05) return 0.5;// make .2 to .1
			if (x < 0.85) return 0.5;// make this faster and for more time
      else return 0.3; //faster!
    }));
    addParallel(new HatchMechSetSliderState(SliderState.OUT));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechPlace());
    addSequential(new FollowPath(PathUtil.createStraightPath(1.0), 0.2), 0.5);

    addSequential(new FollowPath(PathUtil.createStraightPath(20.0), -.2), 0.5);

    // This doesnt turn to a 90
    // addSequential(new FollowPath(new Path(t -> 
		// /* {"start":{"x":290,"y":183},"mid1":{"x":270,"y":183},"mid2":{"x":259,"y":166},"end":{"x":278,"y":135}} */
		// (9 * Math.pow(t, 2) + -102 * t + 0) / (63 * Math.pow(t, 2) + 54 * t + -60),
    // 63.547), -0.5));
    
    // This path needs to be adjusted to the fact that the prior path doesnt turn to a 90 (also unmirror it)
    // addSequential(new FollowPath(new Path(t -> 
		// /* {"start":{"x":290,"y":255},"mid1":{"x":148,"y":286},"mid2":{"x":140,"y":212},"end":{"x":59,"y":212}} */
		// (537 * Math.pow(t, 2) + -630 * t + 93) / (-621 * Math.pow(t, 2) + 804 * t + -426),
		// 241.337), 0.6));
    // addParallel(new HatchMechSetSliderState(SliderState.OUT));
    // addSequential(new DrivetrainLineUp2());
    // addSequential(new HatchMechCollect());
    // addSequential(new FollowPath(PathUtil.createStraightPath(10.0), -0.2));

    // addSequential(new FollowPath(new Path(t -> 
		// /* {"start":{"x":59,"y":298},"mid1":{"x":140,"y":298},"mid2":{"x":140,"y":255},"end":{"x":290,"y":255}} */
		// (258 * Math.pow(t, 2) + -258 * t + 0) / (693 * Math.pow(t, 2) + -486 * t + 243),
		// 236.723), -0.2));

  }
}
