/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class CargoMechGrab extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoMechGrab(double speed, double angle) {
    requires(Robot.cargoMech);

    addParallel(new CargoMechSetIntakeSpeed(speed));
    addSequential(new CargoMechSetToAngle(angle));
  }
}
