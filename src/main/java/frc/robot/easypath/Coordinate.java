/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.easypath;

public class Coordinate {

    private double x, y;
  
    public Coordinate(double x, double y) {
      this.x = x;
      this.y = y;
    }
  
    public double getX() {
      return x;
    }
  
    public double getY() {
      return y;
    }
  }