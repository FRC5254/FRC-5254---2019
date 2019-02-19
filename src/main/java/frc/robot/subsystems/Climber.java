/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  private static Solenoid climberSolenoid;
  private static TalonSRX climbMotor1;
  private static TalonSRX climbMotor2;

  private static DigitalInput bottomLimit;

  private static ClimberMode climbMode;
  private static ClimberMode defaultClimbMode = ClimberMode.SECURED_MODE;

  private static Climber instance = new Climber();

  private Climber() {
    climberSolenoid = new Solenoid(RobotMap.CLIMBER_SOLENOID);

    climbMotor1 = new TalonSRX(RobotMap.CLIMB_MOTOR_1);
    climbMotor2 = new TalonSRX(RobotMap.CLIMB_MOTOR_1);

    bottomLimit = new DigitalInput(RobotMap.CLIMBER_BUTTON);

    climbMode = defaultClimbMode;

    climbMotor2.follow(climbMotor1);

    // setting ramping rates to zero incase they were configed
    climbMotor1.configOpenloopRamp(0.0);
    climbMotor1.configClosedloopRamp(0.0);
    // sets the mode to brake
    climbMotor1.setNeutralMode(NeutralMode.Brake);
    // sets Nominal (minimum) percent output to zero
    climbMotor1.configNominalOutputForward(0.0);
    climbMotor1.configNominalOutputReverse(0.0);
    // sets Peak (maximum) percent output
    climbMotor1.configPeakOutputForward(0.65);// TODO tune these
    climbMotor1.configPeakOutputReverse(-0.65);
    // This will ignore the battery volatge (if its lower than 12V) and pretend its at 12V
    // It makes PID tuning and preformance of the mechanism more consistent
    climbMotor1.configVoltageCompSaturation(12);
    climbMotor1.enableVoltageCompensation(true);

    
  }

  public static Climber getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
  }

  public enum ClimberMode {
    CLIMB_MODE(true), SECURED_MODE(false);

    private boolean mode;
      ClimberMode(boolean mode) {
      this.mode = mode;
    }
  }

  public void setMode(ClimberMode newMode) {
    climberSolenoid.set(newMode.mode);
  }

  public void setSpeed(double speed) {
    if (OI.driver.rightBumper.get()) { // TODO do the double button thing
    climbMotor1.set(ControlMode.PercentOutput, speed);
    }
  }
  
}
