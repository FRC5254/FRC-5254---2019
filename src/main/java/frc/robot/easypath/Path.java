/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.easypath;

import java.util.function.Function;

public class Path {

  private Function<Double, Double> derivative;
  private double length;

  /**
   * An object that represents a path for the robot to follow. Consists of the equation's derivative
   * and the total length to drive.
   *
   * @param derivative the derivative of the path to drive along
   * @param length the total length to drive
   */
  public Path(Function<Double, Double> derivative, double length) {
    this.derivative = derivative;
    this.length = length;
  }

  /**
   * @return the derivative of the equation
   */
  public Function<Double, Double> getDerivative() {
    return derivative;
  }

  /**
   * @return the total length of the path to drive
   */
  public double getLength() {
    return length;
  }
}
