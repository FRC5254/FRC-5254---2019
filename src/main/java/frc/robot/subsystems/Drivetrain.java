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
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveWithJoystick;
import frc.robot.utils.Limelight;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {

  public enum ShiftState {
    HIGH_GEAR(true), LOW_GEAR(false);

    private boolean state;
    ShiftState(boolean state){
      this.state = state;
    }
  }

  public enum DriverControls {
    ARCADE, GTA_DRIVE;
  }

  public enum DrivetrainMotorControllers {
    TALON_SRX, SPARK_MAX
  }

  public enum ManipulationMode {
    CARGO, PANEL;
  }

  private WPI_TalonSRX tLeft1, tLeft2, tRight1, tRight2;
  private CANSparkMax sLeft1, sLeft2, sLeft3, sRight1, sRight2, sRight3;

  private DifferentialDrive drive;
  private SpeedControllerGroup driveControllersLeft;
  private SpeedControllerGroup driveControllersRight;
  private Encoder leftEncoder, rightEncoder;
  private ADXRS450_Gyro gyro;
  
  private static Solenoid shiftingSolenoid;

  public ShiftState shiftState;
  public DrivetrainMotorControllers drivetrainMotorContollers; //TODO Make private?
  public DriverControls driverControls;
  public ManipulationMode manipulationMode;

  private static Drivetrain instance = new Drivetrain();
  
  private Drivetrain() {

    drivetrainMotorContollers = DrivetrainMotorControllers.TALON_SRX;
    driverControls = DriverControls.GTA_DRIVE; //TODO do these go here or earlier?
    manipulationMode = ManipulationMode.CARGO;

    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX) { 
      tLeft1 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_LEFT);
      tLeft2 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_LEFT_2);
      tRight1 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_RIGHT);
      tRight2 = new WPI_TalonSRX(RobotMap.T_DRIVETRAIN_RIGHT_2);
      
      tLeft2.follow(tLeft1);
      tRight2.follow(tRight1);

      tLeft1.setInverted(false);//TODO invert as necessary
      tLeft2.setInverted(false);//TODO invert as necessary

      drive = new DifferentialDrive(tLeft1, tRight1);
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

      sLeft1.setInverted(true);//TODO invert as necessary
      sLeft2.setInverted(true);//TODO invert as necessary
      sLeft3.setInverted(true);//TODO invert as necessary

      drive = new DifferentialDrive(sLeft1, sRight1);
    }
    
    shiftingSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID);

    // leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
    // rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
    // gyro = new ADXRS450_Gyro();
  }

  public void initDefaultCommand() {
    setDefaultCommand(new DrivetrainDriveWithJoystick());
  }

  public static Drivetrain getInstance() {
    return instance;
  }
  
  public void LineUp() {
    // TODO tune these numbers
    double Kp = -0.1f;
    double min_command = 0.05f;

    double horizontalOffset = (Limelight.getHorizontalOffset());// TODO is this okay?
    double adjust = 0.0f;
    if(horizontalOffset > 1.0) {
      adjust = Kp * horizontalOffset - min_command;
    } else if(horizontalOffset < 1.0) {
      adjust = Kp * horizontalOffset + min_command;
    }

    sLeft1.set(adjust);// TODO test polarity
    sRight1.set(-adjust);
  }

  public void setShiftState(ShiftState newState) {
  shiftingSolenoid.set(newState.state);
  shiftState = newState;
  }

  public void arcadeDrive(double throttle, double turn) {
   drive.arcadeDrive(throttle, turn);
  }
  
  public void GTADrive(double leftTrigger, double rightTrigger, double turn) {
    
    if (-0.1 <= turn && turn <= 0.1) {
      turn = 0.0;
    }

    turn = turn * turn * Math.signum(turn);

    double left = rightTrigger - leftTrigger +  turn;
    double right = rightTrigger - leftTrigger - turn;
    left = Math.min(1.0, Math.max(-1.0, left));
    right = Math.max(-1.0, Math.min(1.0, right));
    
    if(drivetrainMotorContollers == DrivetrainMotorControllers.TALON_SRX) {
      if(manipulationMode == ManipulationMode.CARGO){
        tRight1.set(-right);
        tLeft1.set(-left);
      } else if (manipulationMode == ManipulationMode.PANEL) {
        tRight1.set(right); 
        tLeft1.set(left);
      } else {
        System.out.println("************* this shouldn't happen but it did --- Drivetrain manipulationMode illdefined *************");
      }
    } else if(drivetrainMotorContollers == DrivetrainMotorControllers.SPARK_MAX) {
      if(manipulationMode == ManipulationMode.CARGO) {
        sRight1.set(-right);
        sLeft1.set(-left);
      } else if(manipulationMode == ManipulationMode.PANEL) {
        sRight1.set(right);
        sLeft1.set(left);
      } else {
        System.out.println("************* this shouldn't happen but it did --- Drivetrain manipulationMode illdefined *************");
      } 
    } else {
      System.out.println("************* this shouldn't happen but it did --- in Drivetrain, drivetrainMotorContollers is illdefined *************");
    }
  }

  public void setManipulationMode(ManipulationMode newMode) {
    manipulationMode = newMode;
  }
}