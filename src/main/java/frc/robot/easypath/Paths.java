/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.easypath;

/**
 * Class to pull created paths from
 */
public class Paths {

    public static final Path LEVEL_1_CROSS_HABLINE = PathUtil.createStraightPath(50.0);
    public static final Path LEVEL_2_CROSS_HABLINE = PathUtil.createStraightPath(100.0);

    public static final Path CENTER_HATCH_DRIVE = PathUtil.createStraightPath(50.0);

    public static final Path CENTER_BACK_ROCKET = new Path(t -> 
	/* {"start":{"x":49,"y":163},"mid1":{"x":213,"y":163},"mid2":{"x":222,"y":315},"end":{"x":299,"y":280}} */
	(-1017 * Math.pow(t, 2) + 912 * t + 0) / (669 * Math.pow(t, 2) + -930 * t + 492),
	290.678);

    // -------- To Left Feeder station --------
    public static final Path CENTER_LEFT_HATCH_BACKUP = new Path(t -> 
    /* {"start":{"x":216,"y":173},"mid1":{"x":190,"y":173},"mid2":{"x":190,"y":173},"end":{"x":200,"y":200}} */
    (81 * Math.pow(t, 2) + 0 * t + 0) / (-48 * Math.pow(t, 2) + 156 * t + -78),
    47.121);

    public static final Path CENTER_LEFT_HATCH_TO_LEFT_FEEDER_STATION = new Path(t -> 
    /* {"start":{"x":219,"y":152},"mid1":{"x":103,"y":152},"mid2":{"x":150,"y":28},"end":{"x":30,"y":28}} */
    (744 * Math.pow(t, 2) + -744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
    241.039);

    // -------- To Right Feeder Station --------
    public static final Path CENTER_RIGHT_HATCH_BACKUP = new Path(t -> 
    /* {"start":{"x":216,"y":173},"mid1":{"x":190,"y":173},"mid2":{"x":190,"y":173},"end":{"x":200,"y":146}} */
    (-81 * Math.pow(t, 2) + 0 * t + 0) / (-48 * Math.pow(t, 2) + 156 * t + -78),
    47.121);

    public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION = new Path(t -> 
    /* {"start":{"x":219,"y":174},"mid1":{"x":103,"y":174},"mid2":{"x":150,"y":298},"end":{"x":30,"y":298}} */
    (-744 * Math.pow(t, 2) + 744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
    241.039);

    // -------- To Left Cargo Depo --------
    public static final Path CENTER_LEFT_HATCH_TO_LEFT_CARGO_DEPO = new Path(t -> 
    /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":145,"y":17},"end":{"x":67,"y":85}} */
    (1140 * Math.pow(t, 2) + -936 * t + 0) / (-816 * Math.pow(t, 2) + 918 * t + -336),
    206.058);

    // -------- To Right Cargo Depo --------
    public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_CARGO_DEPO = new Path(t -> 
    /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":145,"y":329},"end":{"x":67,"y":261}} */
    (-1140 * Math.pow(t, 2) + 936 * t + 0) / (-816 * Math.pow(t, 2) + 918 * t + -336),
    206.058);

    public static final Path RIGHT_CARGO_DEPO_TO_CLOSE_CARGOSHIP = new Path(t -> 
    /* {"start":{"x":50,"y":263},"mid1":{"x":50,"y":341},"mid2":{"x":165,"y":374},"end":{"x":239,"y":432}} */
    (210 * Math.pow(t, 2) + -270 * t + 234) / (-468 * Math.pow(t, 2) + 690 * t + 0),
    263.275);

    public static final Path RIGHT_CURVE_TO_CARGOSHIP = new Path(t -> 
    /* {"start":{"x":280,"y":264},"mid1":{"x":249,"y":268},"mid2":{"x":256,"y":250},"end":{"x":242,"y":216}} */
    (18 * Math.pow(t, 2) + -132 * t + 12) / (-177 * Math.pow(t, 2) + 228 * t + -93),
    69.937);

    

}
