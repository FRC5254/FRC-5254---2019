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
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainArcadeDriveWithJoystick;
import frc.robot.commands.DrivetrainCustomDriveWithJoystick;

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

  public enum DriverControls {
    ARCADE, SHAKER_DRIVE;
  }

  public enum DrivetrainMotorControllers {
    TALON_SRX, SPARK_MAX
  }

  public enum ManipulationMode {
    CARGO, PANEL;
  }

  private WPI_TalonSRX tLeft1, tLeft2, tRight1, tRight2;
  private CANSparkMax sLeft1, sLeft2, sRight1, sRight2;

  private DifferentialDrive drive;
  private Encoder leftEncoder, rightEncoder;
  private ADXRS450_Gyro gyro;
  
  private static Solenoid shiftingSolenoid;

  public ShiftState shiftState;
  public DrivetrainMotorControllers drivetrainMotorContollers; // TODO Make private?
  public DriverControls driverControls;
  public ManipulationMode manipulationMode;
  
  public Drivetrain() {
    super("Drivetrain", 0.0, 0.0, 0.0);

    drivetrainMotorContollers = DrivetrainMotorControllers.TALON_SRX;
    driverControls = DriverControls.ARCADE;// TODO do these go here or earlier?
    manipulationMode = ManipulationMode.CARGO;

    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX) { 
      tLeft1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT);
      tLeft2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_2);
      tRight1 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT);
      tRight2 = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_2);
      
      tLeft2.follow(tLeft1);
      tRight2.follow(tRight1);

      tLeft1.setInverted(true);
      tLeft2.setInverted(true);
    }

    if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX){
      sLeft1 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT, MotorType.kBrushless);
      sLeft2 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT, MotorType.kBrushless);
      sRight1 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT, MotorType.kBrushless);
      sRight2 = new CANSparkMax(RobotMap.DRIVETRAIN_LEFT, MotorType.kBrushless);

      sLeft2.follow(sLeft1);
      sRight2.follow(sRight1);

      sLeft1.setInverted(true);
      sLeft2.setInverted(true);
    }
    
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
   	drive.arcadeDrive(throttle, turn);
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
    
    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX && manipulationMode == ManipulationMode.CARGO) {
      tRight1.set(right);
      tLeft1.set(left);
    } else if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX && manipulationMode == ManipulationMode.PANEL) {
      tRight1.set(-right); 
      tLeft1.set(-left);
    } else if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX && manipulationMode == ManipulationMode.CARGO) {
      sRight1.set(right);
      sLeft1.set(left);
    } else if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX && manipulationMode == ManipulationMode.PANEL) {
      sRight1.set(-right);
      sLeft1.set(-left);
    }
  }
  
  protected double returnPIDInput() {
    return gyro.getAngle();
  }
  
  public void initDefaultCommand() {
    if(driverControls == DriverControls.SHAKER_DRIVE){
     setDefaultCommand(new DrivetrainCustomDriveWithJoystick());
    }

    if (driverControls == DriverControls.ARCADE) {
      setDefaultCommand(new DrivetrainArcadeDriveWithJoystick());
    }
  
  }

  @Override
  protected void usePIDOutput(double output) {
  // left1.set(output);
  // right1.set(-output);
  }
}