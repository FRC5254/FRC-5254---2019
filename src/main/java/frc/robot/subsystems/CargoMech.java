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
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Victor cargoMotor;
  private static TalonSRX pivotMotor, pivotMotor_2;

  private DigitalInput topLimitSwitch;
  private DigitalInput bottomLimitSwitch;

  private static CargoMech instance = new CargoMech();

  private CargoMech() {

    cargoMotor = new Victor(RobotMap.CARGO_MOTOR);
    pivotMotor = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR);
    pivotMotor_2 = new TalonSRX(RobotMap.CARGO_PIVOT_MOTOR_2);
    
    pivotMotor_2.follow(pivotMotor);

    topLimitSwitch = new DigitalInput(0);
    bottomLimitSwitch = new DigitalInput(1);
  }

  public static CargoMech getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new CargoMechDriveWithJoystick());
  }

  public void setIntakeMotor(double speed) {
    cargoMotor.set(speed);
  }

  public boolean atTopLimit() {//TODO remove?
    return topLimitSwitch.get();
  }

  public boolean atBottomLimit() {
    return bottomLimitSwitch.get();
  }

  public void setPivotMotor(double speed) {
    if(atTopLimit()) {
      if (speed >= 0) {
        pivotMotor.set(ControlMode.PercentOutput, speed);
      } else {
        pivotMotor.set(ControlMode.PercentOutput, 0.0);
      }
    } else if(atBottomLimit()) {
        if(speed <= 0) {
          pivotMotor.set(ControlMode.PercentOutput, speed);
        } else {
          pivotMotor.set(ControlMode.PercentOutput, 0.0);
        } 
    } else {
      pivotMotor.set(ControlMode.PercentOutput, speed);
    }
  }
}