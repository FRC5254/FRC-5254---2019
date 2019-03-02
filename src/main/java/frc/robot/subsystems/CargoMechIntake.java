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
public class CargoMechIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static TalonSRX intakeMotor;

  private static CargoMechIntake instance = new CargoMechIntake();


  private static final int intakeStallLimit = 25;

  private CargoMechIntake() {

    intakeMotor = new TalonSRX(RobotMap.CARGO_MOTOR);

    intakeMotor.setInverted(true);
  }

  public static CargoMechIntake getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new CargoMechDriveWithJoystick());
  }

  public void setIntakeMotor(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public boolean ballIntook() {
    return getIntakeCurrent() > intakeStallLimit;
  }

  public double getIntakeCurrent() {
    return intakeMotor.getOutputCurrent();
  }
}
