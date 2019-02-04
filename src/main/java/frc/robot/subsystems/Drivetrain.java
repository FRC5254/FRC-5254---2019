/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveWithJoystick;

/**
 * Add your docs here.
 */
public class Drivetrain extends PIDSubsystem {

  public enum ShiftState {
    HIGH_GEAR(true), LOW_GEAR(false);

    private boolean state;
    ShiftState(boolean state){
      this.state = state;
    }
  }

  private WPI_TalonSRX left1, left2, right1, right2;
  private DifferentialDrive drive;
  private Encoder leftEncoder, rightEncoder;
  private ADXRS450_Gyro gyro;
  
  private static Solenoid shiftingSolenoid;

  public ShiftState shiftState;
  
  public Drivetrain() {
    super("Drivetrain", 0.0, 0.0, 0.0);
    left1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT);
    left2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_2);
    right1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT);
    right2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_2);
    
    
    left2.follow(left1);
    right2.follow(right1);

    left1.setInverted(true);
    left2.setInverted(true);
    
//    	drive = new DifferentialDrive(left1, right1);
    
    // leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
    // rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
    
    gyro = new ADXRS450_Gyro();
    setInputRange(0, 360); //our gyro might not be 0 to 360
    setOutputRange(-1, 1);
    
    setAbsoluteTolerance(0.5); //need to test if 0.5 works
    getPIDController().setContinuous();
    
    // shiftingSolenoid = new Solenoid(RobotMap.shiftingSolenoid);
  }
  
public void shift(ShiftState newState) {
  shiftingSolenoid.set(newState.state);
  shiftState = newState;
}

  public void arcadeDrive(double throttle, double turn) {
//    	drive.arcadeDrive(throttle, turn);
  }
  
  public void customDrive(double leftTrigger, double rightTrigger, double turn) {
    

    if (-0.1 <= turn && turn <= 0.1) {
      turn = 0.0;
    }

    turn = turn * turn * Math.signum(turn); //TODO add negative?

    double left = rightTrigger - leftTrigger +  turn;
    double right = rightTrigger - leftTrigger - turn;
    left = Math.min(1.0, Math.max(-1.0, left));
    right = Math.max(-1.0, Math.min(1.0, right));
    
    
    right1.set(right); // for Panel mech -right
    left1.set(left); // -left
    
  
  }
  
  protected double returnPIDInput() {
    return gyro.getAngle();
  }
  
  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainDriveWithJoystick());
  }

@Override
protected void usePIDOutput(double output) {
  left1.set(output);
  right1.set(-output);
}


}