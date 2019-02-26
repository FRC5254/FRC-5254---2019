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
// Center (lvl 1)

        public static final Path LEVEL_1_CROSS_HABLINE = PathUtil.createStraightPath(50.0);

        public static final Path CENTER_HATCH_DRIVE = PathUtil.createStraightPath(50.0);

    // Hatch
    // to Center
        public static final Path CENTER_LEFT_HATCH = new Path(t -> 
        /* {"start":{"x":80,"y":162},"mid1":{"x":150,"y":162},"mid2":{"x":150,"y":149},"end":{"x":219,"y":152}} */
        (87 * Math.pow(t, 2) + -78 * t + 0) / (417 * Math.pow(t, 2) + -420 * t + 210),
        139.635);

        public static final Path CENTER_RIGHT_HATCH = new Path(t -> 
        /* {"start":{"x":80,"y":162},"mid1":{"x":150,"y":162},"mid2":{"x":150,"y":175},"end":{"x":219,"y":175}} */
        (-78 * Math.pow(t, 2) + 78 * t + 0) / (417 * Math.pow(t, 2) + -420 * t + 210),
        139.861);
    
    // to Left
        public static final Path CENTER_LEFT_HATCH_TO_LEFT_FEEDER_STATION = new Path(t -> 
        /* {"start":{"x":219,"y":152},"mid1":{"x":103,"y":152},"mid2":{"x":150,"y":28},"end":{"x":30,"y":28}} */
        (744 * Math.pow(t, 2) + -744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
        241.039);

    // to Right
        public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION = new Path(t -> 
        /* {"start":{"x":219,"y":174},"mid1":{"x":103,"y":174},"mid2":{"x":150,"y":298},"end":{"x":30,"y":298}} */
        (-744 * Math.pow(t, 2) + 744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
        241.039);
    
    // Cargo
    // to Center
    // to Left
    // to Right
        public static final Path CENETER_RIGHT_HATCH_TO_RIGHT_CARGO_STATION = new Path(t -> 
        /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":145,"y":329},"end":{"x":67,"y":261}} */
        (-1140 * Math.pow(t, 2) + 936 * t + 0) / (-816 * Math.pow(t, 2) + 918 * t + -336),
        206.058);

// Left
        public static final Path LEVEL_2_CROSS_HABLINE = PathUtil.createStraightPath(100.0);
    // to Center
    // to Left
    // to Right
}
