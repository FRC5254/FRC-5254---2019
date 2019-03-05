/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainDriveWithJoystick;
import frc.robot.utils.Limelight;

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

    sLeft1.setOpenLoopRampRate(0.0);
    sRight1.setOpenLoopRampRate(0.0);
    // sLeft1.setRampRate(0.2);
    // sRight1.setRampRate(0.2);

    sLeft1.setSmartCurrentLimit(30);
    sRight1.setSmartCurrentLimit(30);
      
    shiftingSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID);

    gyro = new ADXRS450_Gyro();
    leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_1, RobotMap.LEFT_ENCODER_2, true, Encoder.EncodingType.k4X); // TODO need last two variables?
    rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_1, RobotMap.RIGHT_ENCODER_2, false, Encoder.EncodingType.k4X);

    double distancePerPulse = Math.PI * RobotMap.WHEEL_DIAMETER / RobotMap.PULSE_PER_REV / RobotMap.GEAR_RATIO;

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

    sRight1.set(right);
    sLeft1.set(left);
  }

// Vision lineup

  public double EstimateDistance() {
    double h1 = 31.25;
    double h2 = 28.5;
    double a1 = 41.0;
    double a2 = 0.0;

    double distance = (h1-h2)/ Math.tan(a1+a2);
    return distance - 4;
  }

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
  
  /*
   * Instead of actually calculating the distance, 
   * you can use the limelight cross-hair. 
   * Just position your robot at your idea distance from the target and calibrate 
   * the y-position of the cross-hair. 
   * Now your y-angle will report as 0.0 when your robot is at the corect distance. 
   * Using this trick, you don’t ever have to actually calculate the actual distance.
  */

  public void ThrottleUp() {
    // TODO tune these numbers
    double KpDistance = -0.1;
    
    double distance_error = Limelight.getVerticalOffset();

    double adjust = KpDistance * distance_error;

    sLeft1.set(adjust);// TODO test polarity
    sRight1.set(adjust);
  }

  public void LineUp() {
    // TODO tune these numbers
    double KpAim = 0.2 / 27; // 0.3 / 27; for comp
    double KpDistance = -0.0;
    double min_aim_command = 0.00;
    double zoom = 0.15; // 0.225 for comp

    // .075 +- 0.1 / 27
    
    double horoff = Limelight.getHorizontalOffset();
    double veroff = Limelight.getVerticalOffset();
    
    double heading_error = horoff;
    double distance_error = veroff;
    double steering_adjust = 0.0;
    
    SmartDashboard.putNumber("LineUp headingError", heading_error);

    if (horoff > 1.0) {
      steering_adjust = KpAim*heading_error - min_aim_command;
    } else if (horoff < 1.0) {
      steering_adjust = KpAim*heading_error + min_aim_command;
    }
    
    SmartDashboard.putNumber("LineUp distanceError", distance_error);
    double distance_adjust = KpDistance * distance_error;
    
    double adjust = steering_adjust + distance_adjust;

    SmartDashboard.putNumber("LineUp Left", zoom + adjust);
    SmartDashboard.putNumber("LineUp Right", zoom - adjust);

    sLeft1.set(zoom + adjust);
    sRight1.set(zoom - adjust);
  }
  
  public boolean LineUpIsFinished() {
    double veroff = Limelight.getVerticalOffset();
    return veroff < 8; // 9?
  }
  public double getAngle() {
    return gyro.getAngle();
  }
 
  public void setAutoDrive() {
    sLeft1.setIdleMode(IdleMode.kBrake);
    sLeft2.setIdleMode(IdleMode.kBrake);
    sLeft3.setIdleMode(IdleMode.kBrake);
    sRight1.setIdleMode(IdleMode.kBrake);
    sRight2.setIdleMode(IdleMode.kBrake);
    sRight3.setIdleMode(IdleMode.kBrake);
  }

  public void setTeleDrive() {
    sLeft1.setIdleMode(IdleMode.kBrake);
    sLeft2.setIdleMode(IdleMode.kCoast);
    sLeft3.setIdleMode(IdleMode.kCoast);
    sRight1.setIdleMode(IdleMode.kBrake);
    sRight2.setIdleMode(IdleMode.kCoast);
    sRight3.setIdleMode(IdleMode.kCoast);
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