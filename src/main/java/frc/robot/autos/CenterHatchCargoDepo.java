/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;
import frc.robot.commands.CargoMechIntake;
import frc.robot.commands.CargoMechOuttake;
import frc.robot.commands.CargoMechSetToAngle;
import frc.robot.commands.DrivetrainLineUp2;
import frc.robot.commands.HatchMechSetFinState;
import frc.robot.commands.HatchMechSetKickerState;
import frc.robot.commands.HatchMechSetSliderState;
import frc.robot.easypath.FollowPath;
import frc.robot.easypath.Path;
import frc.robot.easypath.Paths;
import frc.robot.subsystems.HatchMech.FinState;
import frc.robot.subsystems.HatchMech.KickerState;
import frc.robot.subsystems.HatchMech.SliderState;

public class CenterHatchCargoDepo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CenterHatchCargoDepo(Path crossHabline, Path driveToPlace, Path driveToCargo) {
   
    // Places preloaded hatch
    addSequential(new FollowPath(crossHabline, 0.35));
    addSequential(new FollowPath(driveToPlace, 0.25));
    addSequential(new DrivetrainLineUp2());
    addSequential(new HatchMechSetSliderState(SliderState.OUT));
    addSequential(new HatchMechSetFinState(FinState.UNCLAMPED));
    addSequential(new HatchMechSetKickerState(KickerState.OUT));

    addSequential(new WaitCommand(1));

    addParallel(new CargoMechSetToAngle(0.0));
    addParallel(new CargoMechIntake(RobotMap.CARGO_AUTO_INTAKE_SPEED));
    addSequential(new FollowPath(driveToCargo, -0.25)); // TODO variation in visionlineup can really wonk this...
    addSequential(new WaitCommand(0.5));
    addParallel(new CargoMechSetToAngle(RobotMap.CARGO_BACKSHOT_SETPOINT));
    // addSequential(new FollowPath(Paths.LEFT_CARGO_DEPO_TO_CLOSE_BACKSHOT, 0.25));
    addSequential(new FollowPath(Paths.LEFT_CARGO_DEPO_TO_CLOSE_CARGOSHIP, 0.25));
    // addSequential(new FollowPath(Paths.LEFT_CURVE_TO_CARGOSHIP, -0.25));
    // addParallel(new CargoMechOuttake(-0.75));
  }
}
