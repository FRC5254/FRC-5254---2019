/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoMechDriveWithJoystick;

/**
 * Add your docs here.
 */
public class CargoMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Victor cargoMotor;
  public static TalonSRX pivotMotor, pivotMotor_2;

  private DigitalInput topLimitSwitch;
  private DigitalInput bottomLimitSwitch;

  private static CargoMech instance = new CargoMech();

  private static final int topEncoderLimit = 10; //TODO real values
  private static final int bottomEncoderLimit = -10;

  private CargoMech() {

    cargoMotor = new Victor(RobotMap.CARGO_MOTOR);
    pivotMotor = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR);
    pivotMotor.configOpenloopRamp(0.0);
    pivotMotor.configClosedloopRamp(0.0);
    pivotMotor.setNeutralMode(NeutralMode.Brake);
    pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    pivotMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    pivotMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    pivotMotor.overrideLimitSwitchesEnable(true);
    pivotMotor.configNominalOutputForward(0.0);
    pivotMotor.configNominalOutputReverse(0.0);
    pivotMotor.configPeakOutputForward(0.25);//TDO chanage back
    pivotMotor.configPeakOutputReverse(-0.25);
    pivotMotor.configVoltageCompSaturation(12);
    pivotMotor.enableVoltageCompensation(true);

    pivotMotor.config_kP(0, 0.5);
    pivotMotor.configAllowableClosedloopError(0, (int) (angleToEncoderTicks(2)));

    pivotMotor_2.follow(pivotMotor);
    // pivotMotor_2.setInverted(true);
  }

  public static CargoMech getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoMechDriveWithJoystick());
  }

  public void setIntakeMotor(double speed) {
    cargoMotor.set(speed);
  }

  public boolean atTopLimit() {
    return pivotMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public boolean atBottomLimit() {
    return pivotMotor.getSensorCollection().isRevLimitSwitchClosed();
  }

  public double getPosition() {
    return pivotMotor.getSelectedSensorPosition(0);
  }

  public void setToAngle(double angle) {
    // cargomechPivotMotor.set();
    pivotMotor.set(ControlMode.Position, angle);
  }


  public double angleToEncoderTicks(double angle) {
    return angle * (topEncoderLimit - bottomEncoderLimit) / 90;
  }

  public double encoderTicksToAngle(double ticks) {
    return ticks / (topEncoderLimit - bottomEncoderLimit) * 90;
  }

  public boolean isAtAngle() {
    return pivotMotor.getClosedLoopError() < angleToEncoderTicks(2);
  }

  public double getAngle() {
    return encoderTicksToAngle(pivotMotor.getSelectedSensorPosition(0));
  }
  public void setPivotMotor(double speed) {
      pivotMotor.set(ControlMode.PercentOutput, speed);
  }
}