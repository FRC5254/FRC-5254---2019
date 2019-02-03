/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Victor intakeMotor;
  private static Solenoid intakeSolenoid;

  public Intake() {

    intakeMotor = new Victor(RobotMap.INTAKE_MOTOR);
    intakeSolenoid = new Solenoid(RobotMap.INTAKE_SOLENOID);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setSpeed(double speed) {

    intakeMotor.set(speed);
  }

  public void actuate(boolean direction) {

    intakeSolenoid.set(direction);
  }
}
