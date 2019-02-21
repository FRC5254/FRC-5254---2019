/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public enum FinState {
    CLAMPED(false), UNCLAMPED(true);

    private boolean state;
    FinState(boolean state) {
      this.state = state;
    }
  }

  public enum KickerState {
    OUT(true), IN(false);

    private boolean state;
    KickerState(boolean state) {
      this.state = state;
    }
  }

  public enum SliderState {
    OUT(true), IN(false);

    private boolean state;
    SliderState(boolean state) {
      this.state = state;
    }
  }

  private static Solenoid finSolenoid;
  private static Solenoid kickerSolenoid;
  private static Solenoid sliderSolenoid;

  public FinState finState;
  public KickerState kickerState;
  public SliderState sliderState;

  private final FinState defaultFinState = FinState.CLAMPED;
  private final KickerState defaultKickerState = KickerState.IN;
  private final SliderState defaultSliderState = SliderState.IN;

  private static HatchMech instance = new HatchMech();


  private HatchMech() {

    finSolenoid = new Solenoid(RobotMap.FINGERS_SOLENOID);
    kickerSolenoid = new Solenoid(RobotMap.HALO_SOLENOID);
    sliderSolenoid = new Solenoid(RobotMap.SLIDER_SOLENOID);

    finState = defaultFinState;
    kickerState = defaultKickerState;
    sliderState = defaultSliderState;

  }

  public static HatchMech getInstance() {
    return instance;
  }
  
  @Override
  public void initDefaultCommand() {
    // Dont put commands here
  }

  public void setFinState(FinState newState) {
    if(kickerState == KickerState.OUT && newState == FinState.CLAMPED) {
      setKickerState(KickerState.IN);
    }

    finSolenoid.set(newState.state);
    finState = newState;
  }

  public void setKickerState(KickerState newState) {
    if(finState == FinState.CLAMPED && newState == KickerState.OUT){
      setFinState(FinState.UNCLAMPED);
    }
    kickerSolenoid.set(newState.state);
    kickerState = newState;
  }

  public void setSliderState(SliderState newState) {
    sliderSolenoid.set(newState.state);
    sliderState = newState;
  }
}
