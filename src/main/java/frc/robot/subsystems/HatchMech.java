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
import frc.robot.commands.HatchMechHoldPanel;

/**
 * Add your docs here.
 */
public class HatchMech extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public enum FinState {
    CLAMPED(true), UNCLAMPED(false);

    private boolean state;
    FinState(boolean state) {
      this.state = state;
    }
  }

  public enum KickerState {
    OUT(Value.kForward), IN(Value.kReverse);

    private Value state;
    KickerState(Value state) {
      this.state = state;
    }
  }

  public enum SliderState {
    OUT(Value.kForward), IN(Value.kReverse);

    private Value state;
    SliderState(Value state) {
      this.state = state;
    }
  }

  private static DoubleSolenoid kickerSolenoid;
  private static Solenoid finSolenoid;
  private static DoubleSolenoid sliderSolenoid;

  public FinState finState;
  public KickerState kickerState;
  public SliderState sliderState;

  private final FinState defaultFinState = FinState.CLAMPED;
  private final KickerState defaultKickerState = KickerState.IN;
  private final SliderState defaultSliderState = SliderState.IN;


  public HatchMech() {

    kickerSolenoid = new DoubleSolenoid(RobotMap.HALO_SOLENOID_OUT, RobotMap.HALO_SOLENOID_IN);
    finSolenoid = new Solenoid(RobotMap.FINGERS_SOLENOID);
    sliderSolenoid = new DoubleSolenoid(RobotMap.ACTUATE_SOLENOID_OUT, RobotMap.ACTUATE_SOLENOID_IN);

    finState = defaultFinState;
    kickerState = defaultKickerState;
    sliderState = defaultSliderState;

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new HatchMechHoldPanel());
  }

  public void setKickerState(KickerState newState) {
    kickerSolenoid.set(newState.state);
    kickerState = newState;
  }

  public void setFinState(FinState newState) {
    if(kickerState == KickerState.OUT && newState == FinState.CLAMPED) {
      kickerState = KickerState.IN; //TODO Does this work?
    }

    finSolenoid.set(newState.state);
    finState = newState;
  }

  public void setSliderState(SliderState newState) {
    sliderSolenoid.set(newState.state);
    sliderState = newState;
  }
}
