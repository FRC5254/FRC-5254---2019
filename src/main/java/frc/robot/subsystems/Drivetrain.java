/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveWithJoystick;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {

  public enum ShiftState {
    HIGH_GEAR(false), LOW_GEAR(true);

    private boolean state;
    ShiftState(boolean state){
      this.state = state;
    }
  }

  public enum DrivetrainMotorControllers {
    TALON_SRX, SPARK_MAX
  }

  public enum ManipulationMode {
    CARGO, PANEL;
  }

  private WPI_TalonSRX tLeft1, tLeft2, tRight1, tRight2;
  private CANSparkMax sLeft1, sLeft2, sLeft3, sRight1, sRight2, sRight3;

  private Encoder leftEncoder, rightEncoder;
  private double distancePerPulse;
  private ADXRS450_Gyro gyro;
  
  private static Solenoid shiftingSolenoid;

  public ShiftState shiftState;
  private DrivetrainMotorControllers drivetrainMotorContollers;

  private static Drivetrain instance = new Drivetrain();
  
  private Drivetrain() {

    drivetrainMotorContollers = DrivetrainMotorControllers.SPARK_MAX;

    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX) { 
      tLeft1 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_LEFT);
      tLeft2 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_LEFT_2);
      tRight1 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_RIGHT);
      tRight2 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_RIGHT_2);
      
      tLeft2.follow(tLeft1);
      tRight2.follow(tRight1);

      tLeft1.setInverted(false);
      tLeft2.setInverted(false);
    }

    if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX){

      sLeft1 = new CANSparkMax(RobotMap.S_DRIVETRAIN_LEFT, MotorType.kBrushless);
      sLeft2 = new CANSparkMax(RobotMap.S_DRIVETRAIN_LEFT_2, MotorType.kBrushless);
      sLeft3 = new CANSparkMax(RobotMap.S_DRIVETRAIN_LEFT_3, MotorType.kBrushless);

      sRight1 = new CANSparkMax(RobotMap.S_DRIVETRAIN_RIGHT, MotorType.kBrushless);
      sRight2 = new CANSparkMax(RobotMap.S_DRIVETRAIN_RIGHT_2, MotorType.kBrushless);
      sRight3 = new CANSparkMax(RobotMap.S_DRIVETRAIN_RIGHT_3, MotorType.kBrushless);


      sLeft2.follow(sLeft1);
      sLeft3.follow(sLeft1);

      sRight2.follow(sRight1);
      sRight3.follow(sRight1);

      sLeft1.setInverted(true);
      sLeft2.setInverted(true);
      sLeft3.setInverted(true);

      // sleft1.setOpenLoopRampRate(0.2);
      // sRight1.setOpenLoopRampRate(0.2);
      // sLeft1.setRampRate(0.2);
      // sRight1.setRampRate(0.2);

      sLeft1.setSmartCurrentLimit(30);
      sRight1.setSmartCurrentLimit(30);
    }
    
    shiftingSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID);

    gyro = new ADXRS450_Gyro();
    leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_1, RobotMap.LEFT_ENCODER_2, true, Encoder.EncodingType.k4X); // TODO need last two variables?
    rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_1, RobotMap.RIGHT_ENCODER_2, true, Encoder.EncodingType.k4X);

    distancePerPulse = Math.PI * RobotMap.WHEEL_DIAMETER / RobotMap.PULSE_PER_REV / RobotMap.GEAR_RATIO;

    leftEncoder.reset();
    // leftEncoder.setReverseDirection(false); Dont need, does this in config
    leftEncoder.setSamplesToAverage(7); // "whatever works for you"
    leftEncoder.setDistancePerPulse(distancePerPulse);

    rightEncoder.reset();
    rightEncoder.setSamplesToAverage(7);
    rightEncoder.setDistancePerPulse(distancePerPulse);
  }

  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainDriveWithJoystick());
  }

  public static Drivetrain getInstance() {
   return instance;
  }
  
  public void setShiftState(ShiftState newState) {
  shiftingSolenoid.set(newState.state);
  shiftState = newState;
  }
  
  public void GTADrive(double leftTrigger, double rightTrigger, double turn) {
    
    if (-0.2 <= turn && turn <= 0.2) {
      turn = 0.0;
    }

    turn = turn * turn * Math.signum(turn);

    double left = rightTrigger - leftTrigger +  turn;
    double right = rightTrigger - leftTrigger - turn;
    left = Math.min(1.0, Math.max(-1.0, left));
    right = Math.max(-1.0, Math.min(1.0, right));
    
    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX) {
      tRight1.set(right);
      tLeft1.set(left);
    } else if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX) {
      sRight1.set(right);
      sLeft1.set(left);
    } else {
      System.out.println("************* this shouldn't happen but it did --- in Drivetrain, drivetrainMotorContollers is illdefined *************");
    }
  }

  public double getAngle() {
    return gyro.getAngle();
  }
 
  public void reset() {
   leftEncoder.reset();
   rightEncoder.reset();
   gyro.reset();
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightDistance() {
    return rightEncoder.getDistance();
  }

  public void setLeftRightSpeeds(double leftSpeed, double rightSpeed) {
    sLeft1.set(leftSpeed); 
    sRight1.set(rightSpeed);
  }
}