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
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.easypath.PathUtil;
import frc.robot.subsystems.HatchMech.SliderState;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;

public class CenterHatchFeederStation extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CenterHatchFeederStation(Path crossHabline, Path driveToPlace, Path driveToFeederStation, Path driveToFeederStation2, Path backUpToCargoShip, Path curveToCargoShip) {

    // Places preloaded panel
    addSequential(new FollowPath(crossHabline, 0.25));
    addSequential(new FollowPath(driveToPlace, 0.25));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetSliderState(SliderState.OUT));
    addSequential(new HatchMechSetFinState(FinState.UNCLAMPED));
    addSequential(new HatchMechSetKickerState(KickerState.OUT));

    addSequential(new WaitCommand(1));

    // Drives to feeder station for another panel
    addSequential(new FollowPath(driveToFeederStation, -0.25));
    addSequential(new FollowPath(driveToFeederStation2, 0.25));
    addParallel(new HatchMechSetKickerState(KickerState.IN));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetFinState(FinState.CLAMPED));

    // Backs up to cargoship and plances seconds panel
    addSequential(new FollowPath(backUpToCargoShip, -0.25));
    addSequential(new FollowPath(curveToCargoShip, 0.25));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetSliderState(SliderState.OUT));
    addSequential(new HatchMechSetFinState(FinState.UNCLAMPED));
    addSequential(new HatchMechSetKickerState(KickerState.OUT));
    addSequential(new FollowPath(PathUtil.createStraightPath(10), -0.25)); 
  }
}
