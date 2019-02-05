/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoMechDriveWithJoystick;;

/**
 * Add your docs here.
 */
public class CargoMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Victor cargoMotor;
  private static TalonSRX pivotMotor, pivotMotor_2;

  public static CargoMech instance = new CargoMech();

  private CargoMech() {

    cargoMotor = new Victor(RobotMap.CARGO_MOTOR);
    pivotMotor = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR);
    pivotMotor_2 = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR_2);
    
    pivotMotor_2.follow(pivotMotor);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoMechDriveWithJoystick());
  }

  public void setIntakeMotor(double speed) {
    cargoMotor.set(speed);
  }

  public void  setPivotMotor(double speed) {
    pivotMotor.set(ControlMode.PercentOutput, speed);
  }
}
