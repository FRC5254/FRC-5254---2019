/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchFloorIntake extends Subsystem {

  public enum PivotState {
    UP(true), DOWN(false);

    private boolean state;
    PivotState(boolean state){
      this.state = state;
    }
  }

  private static Victor intakeMotor;
  private static Solenoid intakeSolenoid;

  public PivotState pivotState;

  private final PivotState defaultPivotState = PivotState.UP;

  private static HatchFloorIntake instance = new HatchFloorIntake();

  private HatchFloorIntake() {
    intakeMotor = new Victor(RobotMap.INTAKE_MOTOR);
    //TODO add pisont init here

    pivotState = defaultPivotState;
    intakeMotor.set(0.0);
  }

  public static HatchFloorIntake getInstance() {
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    //Dont add in a command here
  }

  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  public void setPivotState(PivotState newState) {
    intakeSolenoid.set(newState.state);
    pivotState = newState;
  }
}
