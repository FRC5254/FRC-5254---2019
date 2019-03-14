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
    // this is another way we can spin around
    // public static final Path CENTER_RIGHT_HATCH_BACKUP = new Path(t -> 
    // /* {"start":{"x":216,"y":173},"mid1":{"x":190,"y":173},"mid2":{"x":190,"y":173},"end":{"x":200,"y":146}} */
    // (-81 * Math.pow(t, 2) + 0 * t + 0) / (-48 * Math.pow(t, 2) + 156 * t + -78),
    // 47.121);

    public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION = new Path(t -> 
    /* {"start":{"x":216,"y":173},"mid1":{"x":109,"y":173},"mid2":{"x":132,"y":296},"end":{"x":178,"y":296}} */
    (-738 * Math.pow(t, 2) + 738 * t + 0) / (-321 * Math.pow(t, 2) + 780 * t + -321),
    189.604);

    public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_FEEDER_STATION_2 = PathUtil.createStraightPath(160);

    public static final Path RIGHT_FEEDER_STATION_TO_CARGOSHIP = new Path(t -> 
    /* {"start":{"x":8,"y":297},"mid1":{"x":155,"y":297},"mid2":{"x":157,"y":261},"end":{"x":280,"y":261}} */
    (216 * Math.pow(t, 2) + -216 * t + 0) / (798 * Math.pow(t, 2) + -870 * t + 441),
    275.347);

    public static final Path RIGHT_FEEDER_STATION_TO_CARGOSHIP_2 = new Path(t -> 
    /* {"start":{"x":280,"y":261},"mid1":{"x":259,"y":263},"mid2":{"x":259,"y":262},"end":{"x":259,"y":220}} */
    (-114 * Math.pow(t, 2) + -18 * t + 6) / (-63 * Math.pow(t, 2) + 126 * t + -63),
    57.406);

    // -------- To Left Cargo Depo --------
    // public static final Path CENTER_LEFT_HATCH_TO_LEFT_CARGO_DEPO = new Path(t -> 
    // /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":145,"y":17},"end":{"x":67,"y":85}} */
    // (1140 * Math.pow(t, 2) + -936 * t + 0) / (-816 * Math.pow(t, 2) + 918 * t + -336),
    // 206.058);

    public static final Path CENTER_LEFT_HATCH_TO_LEFT_CARGO_DEPO = 
    // to try (end angle is 45 deg)
    // new Path(t -> 
	// /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":142,"y":50},"end":{"x":50,"y":114}} */
	// (930 * Math.pow(t, 2) + -738 * t + 0) / (-840 * Math.pow(t, 2) + 900 * t + -336),
    // 200.455);
    // new Path(t -> 
    // /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":127,"y":49},"end":{"x":66,"y":110}} */
    // (927 * Math.pow(t, 2) + -744 * t + 0) / (-657 * Math.pow(t, 2) + 810 * t + -336),
    // 187.031);
    new Path(t -> 
		/* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":124,"y":47},"end":{"x":64,"y":107}} */
		(936 * Math.pow(t, 2) + -756 * t + 0) / (-636 * Math.pow(t, 2) + 792 * t + -336),
		189.636);
        
    public static final Path LEFT_CARGO_DEPO_TO_CLOSE_BACKSHOT = 
   // Drives panel first to rocket
    // new Path(t -> 
	// 	/* {"start":{"x":40,"y":79},"mid1":{"x":40,"y":14},"mid2":{"x":175,"y":-97},"end":{"x":228,"y":-44}} */
	// 	(630 * Math.pow(t, 2) + -276 * t + -195) / (-651 * Math.pow(t, 2) + 810 * t + 0),
    // 	257.804);

    // Drives too far
    new Path(t -> 
		/* {"start":{"x":40,"y":79},"mid1":{"x":86,"y":33},"mid2":{"x":260,"y":50},"end":{"x":260,"y":125}} */
		(-15 * Math.pow(t, 2) + 378 * t + -138) / (-906 * Math.pow(t, 2) + 768 * t + 138),
        257.927);



    public static final Path LEFT_CARGO_DEPO_TO_CLOSE_CARGOSHIP =
    // to try (end angle is 45 deg)
    // new Path(t -> 
    // /* {"start":{"x":250,"y":263},"mid1":{"x":172,"y":263},"mid2":{"x":141,"y":392},"end":{"x":81,"y":452}} */
    // (-594 * Math.pow(t, 2) + 774 * t + 0) / (-228 * Math.pow(t, 2) + 282 * t + -234),
    // 262.371);

    // new Path(t -> 
	// /* {"start":{"x":250,"y":263},"mid1":{"x":172,"y":263},"mid2":{"x":177,"y":377},"end":{"x":95,"y":458}} */
	// (-441 * Math.pow(t, 2) + 684 * t + 0) / (-510 * Math.pow(t, 2) + 498 * t + -234),
    // 259.182);
    new Path(t -> 
		/* {"start":{"x":250,"y":263},"mid1":{"x":172,"y":263},"mid2":{"x":141,"y":371},"end":{"x":140,"y":459}} */
		(-384 * Math.pow(t, 2) + 648 * t + 0) / (-51 * Math.pow(t, 2) + 282 * t + -234),
		247.049);
    
    

    public static final Path LEFT_CURVE_TO_CARGOSHIP = 
    new Path(t -> 
	/* {"start":{"x":280,"y":264},"mid1":{"x":249,"y":260},"mid2":{"x":256,"y":278},"end":{"x":256,"y":308}} */
	(-30 * Math.pow(t, 2) + 132 * t + -12) / (-135 * Math.pow(t, 2) + 228 * t + -93),
	63.01);

    

    // -------- To Right Cargo Depo --------
    public static final Path CENTER_RIGHT_HATCH_TO_RIGHT_CARGO_DEPO = 
    // new Path(t -> 
    // /* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":135,"y":310},"end":{"x":72,"y":253}} */
    // (-993 * Math.pow(t, 2) + 822 * t + 0) / (-711 * Math.pow(t, 2) + 858 * t + -336),
    // 191.059);
  
    // to try (end angle is 45 deg)
    new Path(t -> 
	/* {"start":{"x":216,"y":173},"mid1":{"x":104,"y":173},"mid2":{"x":135,"y":297},"end":{"x":72,"y":253}} */
	(-876 * Math.pow(t, 2) + 744 * t + 0) / (-711 * Math.pow(t, 2) + 858 * t + -336),
	185.65);

    public static final Path RIGHT_CARGO_DEPO_TO_CLOSE_CARGOSHIP = 
    // new Path(t -> 
    // /* {"start":{"x":50,"y":263},"mid1":{"x":50,"y":341},"mid2":{"x":165,"y":374},"end":{"x":239,"y":432}} */
    // (210 * Math.pow(t, 2) + -270 * t + 234) / (-468 * Math.pow(t, 2) + 690 * t + 0),
    // 263.275);

    // to try (end angle is 45 deg)
    new Path(t -> 
	/* {"start":{"x":50,"y":263},"mid1":{"x":50,"y":341},"mid2":{"x":179,"y":372},"end":{"x":239,"y":432}} */
	(228 * Math.pow(t, 2) + -282 * t + 234) / (-594 * Math.pow(t, 2) + 774 * t + 0),
	262.371);

    public static final Path RIGHT_CURVE_TO_CARGOSHIP = 
    // new Path(t -> 
    // /* {"start":{"x":280,"y":264},"mid1":{"x":249,"y":268},"mid2":{"x":256,"y":250},"end":{"x":242,"y":216}} */
    // (18 * Math.pow(t, 2) + -132 * t + 12) / (-177 * Math.pow(t, 2) + 228 * t + -93),
    // 69.937);

    // to try (end anlge is 90)
    new Path(t -> 
	/* {"start":{"x":280,"y":264},"mid1":{"x":249,"y":268},"mid2":{"x":256,"y":250},"end":{"x":256,"y":220}} */
	(30 * Math.pow(t, 2) + -132 * t + 12) / (-135 * Math.pow(t, 2) + 228 * t + -93),
	63.01);

}
