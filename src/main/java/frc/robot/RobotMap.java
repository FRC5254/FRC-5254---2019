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
  public static final int CLIMBER_BUTTON = 9; //not on bot yet

  // CAN
  public static final int CARGO_MOTOR = 14; //null
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

  public static final int CLIMB_MOTOR_1 = 9; //not on bot yet
  public static final int CLIMB_MOTOR_2 = 4; //not on bot yet
}
