/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchMechHoldPanel;

/**
 * Add your docs here.
 */
public class HatchMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static DoubleSolenoid haloSolenoid;
  private static Solenoid fingersSolenoid;
  private static DoubleSolenoid actuateSolenoid;

  public HatchMech() {

    haloSolenoid = new DoubleSolenoid(RobotMap.HALO_SOLENOID_OUT, RobotMap.HALO_SOLENOID_IN);
    fingersSolenoid = new Solenoid(RobotMap.FINGERS_SOLENOID);
    actuateSolenoid = new DoubleSolenoid(RobotMap.ACTUATE_SOLENOID_OUT, RobotMap.ACTUATE_SOLENOID_IN);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new HatchMechHoldPanel());
  }

  public void halo(boolean direction) {

    if (direction) {
      haloSolenoid.set(Value.kForward);
    } else {
      haloSolenoid.set(Value.kReverse);
    }

  }

  public void fingers(boolean direction) {

    fingersSolenoid.set(direction);
  }

  public void actuate(boolean direction) {

    if (direction) {
      actuateSolenoid.set(Value.kForward);
    } else {
      actuateSolenoid.set(Value.kReverse);
    }
  }

}
