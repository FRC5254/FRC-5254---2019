/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeHold;

/**
 * Add your docs here.
 */
public class CargoMechIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static TalonSRX intakeMotor;
  public static DigitalInput ballLimit;


  private static CargoMechIntake instance = new CargoMechIntake();


  private static final double intakeStallLimit = 30;

  private CargoMechIntake() {

    intakeMotor = new TalonSRX(RobotMap.CARGO_INTAKE_MOTOR);
    ballLimit = new DigitalInput(RobotMap.BALL_LIMIT);

    intakeMotor.setInverted(false);
  }

  public static CargoMechIntake getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeHold());
  }

  public boolean reachedCurrentLimit() {
    return getIntakeCurrent() > intakeStallLimit;
  }

  public double getIntakeCurrent() {
    return intakeMotor.getOutputCurrent();
  }

  public void setIntakeMotor(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean ballIntook() {
    return (!ballLimit.get());
  }

  public void setHoldSpeed() {
    intakeMotor.set(ControlMode.PercentOutput, 0.1);
  }
}
