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
    public static class FROM_CENTER {
    // to Center
        public static final FollowPath OFF_PLATFORM = new FollowPath(
            PathUtil.createStraightPath(100.0), x -> {
                if (x < 0.5) return 0.5;
                else return 0.75;
            }
        );

        public static final FollowPath CENTER_LEFT_HATCH = new FollowPath(
            new Path(t -> 
            /* {"start":{"x":80,"y":162},"mid1":{"x":150,"y":162},"mid2":{"x":150,"y":149},"end":{"x":219,"y":152}} */
            (87 * Math.pow(t, 2) + -78 * t + 0) / (417 * Math.pow(t, 2) + -420 * t + 210),
            139.635), x -> {
                if (x < 0.15) return 0.5;
                else if (x < 0.75) return 0.8;
                else return 0.25;
            }
        );

        public static final FollowPath CENTER_RIGHT_HATCH = new FollowPath(
            new Path(t -> 
            /* {"start":{"x":80,"y":162},"mid1":{"x":150,"y":162},"mid2":{"x":150,"y":175},"end":{"x":219,"y":175}} */
            (-78 * Math.pow(t, 2) + 78 * t + 0) / (417 * Math.pow(t, 2) + -420 * t + 210),
            139.861), x -> {
                if (x < 0.15) return 0.5;
                else if (x < 0.75) return 0.8;
                else return 0.25;
            } 
        );
    
    // to Left

        public static final FollowPath CENTER_LEFT_HATCH_TO_LEFT_FEEDER_STATION = new FollowPath(
            new Path(t -> 
            /* {"start":{"x":219,"y":152},"mid1":{"x":103,"y":152},"mid2":{"x":150,"y":28},"end":{"x":30,"y":28}} */
            (744 * Math.pow(t, 2) + -744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
            241.039), x-> {
                if (x < 0.15) return 0.75;
                else if (x < 0.75) return 0.8;
                else return 0.25;
            }
        );


    // to Right

        public static final FollowPath CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION = new FollowPath(
            new Path(t -> 
            /* {"start":{"x":219,"y":174},"mid1":{"x":103,"y":174},"mid2":{"x":150,"y":298},"end":{"x":30,"y":298}} */
            (-744 * Math.pow(t, 2) + 744 * t + 0) / (-990 * Math.pow(t, 2) + 978 * t + -348),
            241.039), x -> {
                if (x < 0.15) return 0.75;
                else if (x < 0.75) return 0.8;
                else return 0.25;
            }
        );
            
    }

// Left
    public static class FROM_LEFT {
    // to Center
    public static final FollowPath CENTER_LEFT_HATCH = new FollowPath(
        , speedFunction)
    // to Left
    // to Right
    }

// Right
    public static class FROM_RIGHT {
    // to Center
    // to Left
    // to Right
    }
}
