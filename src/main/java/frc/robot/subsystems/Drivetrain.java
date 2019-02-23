/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
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

  private CANSparkMax sLeft1, sLeft2, sLeft3, sRight1, sRight2, sRight3;

  private Encoder leftEncoder, rightEncoder;
  private ADXRS450_Gyro gyro;
  
  private static Solenoid shiftingSolenoid;

  public ShiftState shiftState;

  private static Drivetrain instance = new Drivetrain();
  
  private Drivetrain() {

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

  public void setShiftState(ShiftState newState) {
    shiftingSolenoid.set(newState.state);
    shiftState = newState;
  }

  public void setDrive(double left, double right) {
    sLeft1.set(left);
    sRight1.set(right);
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

    sRight1.set(right);
    sLeft1.set(left);
  }

// Vision lineup

  public void TurnUp() {
    // TODO tune these numbers
    double Kp = -0.1;
    double min_command = 0.05;

    double horizontalOffset = (Limelight.getHorizontalOffset());// TODO is this okay?
    double targetValue = (Limelight.getTargetValue());
    double adjust = 0.0;

    if (targetValue == 0.0) {
      // safe speed to seek target TODO
      adjust = 0.3;
    } else {

      if(horizontalOffset > 1.0) {
        adjust = Kp * horizontalOffset - min_command;
      } else if(horizontalOffset < 1.0) {
        adjust = Kp * horizontalOffset + min_command;
      }
    }

    sLeft1.set(adjust);// TODO test polarity
    sRight1.set(-adjust);
  }

  public void ThrottleUp() {
    // TODO tune these numbers
    double Kp = -0.1;
    double min_command = 0.05;

    double verticalOffset = (Limelight.getVerticalOffset());// TODO is this okay?
    double adjust = 0.0;
    
    if(verticalOffset > 1.0) {
      adjust = Kp * verticalOffset - min_command;
    } else if(verticalOffset < 1.0) {
      adjust = Kp * verticalOffset + min_command;
    }

    sLeft1.set(adjust);// TODO test polarity
    sRight1.set(-adjust);
  }
}