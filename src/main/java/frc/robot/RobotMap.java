
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  // Solemoids
  public static final int SHIFTER_SOLENOID = 5;
  public static final int FINGERS_SOLENOID = 6;
  public static final int HALO_SOLENOID = 7;
  public static final int SLIDER_SOLENOID = 0;
  public static final int CLIMBER_SOLENOID = 1;

  // DIO
  public static final int LEFT_ENCODER_1 = 0;
  public static final int LEFT_ENCODER_2 = 1;
  public static final int RIGHT_ENCODER_1 = 2;
  public static final int RIGHT_ENCODER_2 = 3;
  public static final int CLIMBER_BUTTON = 9; //not on bot yet
  public static final int BALL_LIMIT = 4;

  // CAN
  public static final int CARGO_INTAKE_MOTOR = 8; //spx is 14
  public static final int CARGO_PIVOT_MOTOR = 11;//2
  public static final int CARGO_PIVOT_MOTOR_2 = 12; //10

  public static final int T_DRIVETRAIN_LEFT = 2;
  public static final int T_DRIVETRAIN_LEFT_2 = 10;
  public static final int T_DRIVETRAIN_RIGHT = 3;
  public static final int T_DRIVETRAIN_RIGHT_2 = 6;
  
  public static final int S_DRIVETRAIN_LEFT = 19; // null
  public static final int S_DRIVETRAIN_LEFT_2 = 24; //null
  public static final int S_DRIVETRAIN_LEFT_3 = 20; // 23
  public static final int S_DRIVETRAIN_RIGHT = 16; // 21
  public static final int S_DRIVETRAIN_RIGHT_2 = 18; // 15
  public static final int S_DRIVETRAIN_RIGHT_3 = 17; //22

  public static final int CLIMB_MOTOR_1 = 9; //7
  public static final int CLIMB_MOTOR_2 = 4; //6
  //Limelight
  public static final double TURN_K = 0.01;
  public static final double THROTTLE_K = 0.10;
  public static final double DESIRED_TARGET_AREA = 10;
  public static final double MAX_TRHOTTLE = 0.5;

  // Misc
  public static final int WHEEL_DIAMETER = 6;
  public static final double GEAR_RATIO = 7.98; // TODO
  public static final int PULSE_PER_REV = 256;

  public static final double CARGO_BACKSHOT_SETPOINT = 90.0;
  
  public static final double CARGO_AUTO_INTAKE_SPEED = 0.65;
  public static final double CARGO_AUTO_OUTTAKE_SPEED = -0.75;
  public static final double CARGO_AUTO_WEIRD_OUTTAKE_SPEED = 0.75;
}
