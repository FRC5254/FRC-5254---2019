/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.CargoMechDriveWithJoystick;

/**
 * Add your docs here.
 */
public class CargoMechArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX pivotMotor, pivotMotor_2;

  private static CargoMechArm instance = new CargoMechArm();

  private static final int topEncoderLimit = 0; //TODO get real values
  private static final int bottomEncoderLimit = 2850;
  
  private final double errorThreshold = Math.abs(angleToEncoderTicks(2) - angleToEncoderTicks(0));

  private CargoMechArm() {
    pivotMotor = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR);
    pivotMotor_2 = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR_2);

    pivotMotor.configOpenloopRamp(0.0);
    pivotMotor.configClosedloopRamp(0.0);
    pivotMotor.setNeutralMode(NeutralMode.Brake);
    pivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10); // from example code
		pivotMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);// sets the encoder to the mag encoder on GB
    pivotMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    pivotMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    pivotMotor.overrideLimitSwitchesEnable(true); // this is backwards true means obey limit switches
    pivotMotor.configNominalOutputForward(0.0);
    pivotMotor.configNominalOutputReverse(0.0);
    pivotMotor.configPeakOutputForward(0.85);//TODO chanage back
    pivotMotor.configPeakOutputReverse(-0.85);
    pivotMotor.configVoltageCompSaturation(12);
    pivotMotor.enableVoltageCompensation(true);
    pivotMotor.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0);
    pivotMotor.configSetParameter(ParamEnum.eClearPositionOnLimitF, 0, 0, 0);
    pivotMotor.setSensorPhase(true);

    // pivotMotor.setSelectedSensorPosition(0, 0, 10); //zeros encoder

    pivotMotor.config_kP(0, 1.0);
    pivotMotor.configAllowableClosedloopError(0, (int) (errorThreshold));
    pivotMotor.setInverted(true);
    pivotMotor_2.setInverted(true);

    pivotMotor_2.follow(pivotMotor);
    // pivotMotor_2.setInverted(true); TODO make sure all polarities in the code are correct for mechanism
  }

  public static CargoMechArm getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoMechDriveWithJoystick());
  }

  public void zeroEncoder() {
    // pivotMotor.setSelectedSensorPosition(0, 0, 10);
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
    SmartDashboard.putNumber("Cargo set point", angleToEncoderTicks(angle));
    pivotMotor.set(ControlMode.Position, angleToEncoderTicks(angle));
  }


  public double angleToEncoderTicks(double angle) {
    return (angle - 90) * (topEncoderLimit - bottomEncoderLimit) / 90;
  }

  public double encoderTicksToAngle(double ticks) {
    return (ticks / (topEncoderLimit - bottomEncoderLimit) * 90) + 90;
  }

  public double getError() {
    return pivotMotor.getClosedLoopError();
  }


  public boolean isAtAngle() {
    SmartDashboard.putNumber("Abs ClosedLoopError", Math.abs(pivotMotor.getClosedLoopError()));

    SmartDashboard.putNumber("error threshold", errorThreshold);
    return Math.abs(pivotMotor.getClosedLoopError()) < errorThreshold;
  }

  public double getAngle() {
    return encoderTicksToAngle(pivotMotor.getSelectedSensorPosition(0));
  }
  public void setPivotMotor(double speed) {
    // if (getPosition() > -400) {
    //   speed = speed * 0.5;
    // }

    pivotMotor.set(ControlMode.PercentOutput, speed );
  }
}
