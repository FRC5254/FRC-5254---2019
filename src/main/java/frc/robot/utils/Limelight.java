/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import java.nio.channels.Pipe;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight {

    private static NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    private static NetworkTableEntry getEntry(String entryName) {
        return getTable().getEntry(entryName);
    }

    private static double getValue(String entryName) {
        return getEntry(entryName).getDouble(0);
    }

    private static void setValue(String entryName, double value) {
        getEntry(entryName).setNumber(value);
    }

// --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---

    /**
    * @return true if the Limelight has any valid targets
    */
    public static boolean hasValidTargets() {
        return getValue("tv") == 1;
    }

    /**
    * @return horizontal offset from crosshair to target (-27 degrees to 27 degrees)
    */
    public static double getHorizontalOffset() {
        return getValue("tx");
    }

    /**
    * @return vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees)
    */
    public static double getVerticalOffset() {
        return getValue("ty");
    }

    /**
    * @return target area (0% of image to 100% of image)
    */
    public static double targetArea() {
        return getValue("ta");
    }

    /**
    * @return skew or rotation (-90 degrees to 0 degrees)
    */
    public static double getSkewOrRotation() {
        return getValue("ts");
    }

    /**
    * @return pipeline's latency contribution (ms); add 11ms for image capture
    */
    public static double getLatency() {
        return getValue("tl");
    }

    /* Unused 
    * "tshort" - Sidelength of shortest side of the fitted bounding box (pixels)
    * "tlong" - Sidelength of longest side of the fitted bounding box (pixels)
    * "thor" - Horizontal sidelength of the rough bounding box (0 - 320 pixels)
    * "tvert" - Vertical sidelength of the rough bounding box (0 - 320 pixels)
    * "getpipe" - True active pipeline index of the camera (0 .. 9)
    */

// --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---

    public enum LedMode {
        PIPELINE(0), FORCE_OFF(1), FORCE_BLINK(2), FORCE_ON(3), UNKNOWN(-1);
        public final double value;

        LedMode(double value) { //TODO double/int?
            this.value = value;
        }
    }

    /**
    * @return The current LED mode set on the Limelight
    */
    public static LedMode getLedMode() {
        double mode = getValue("ledMode");
        if (mode == 0) {
            return LedMode.PIPELINE;
        } else if (mode == 1) {
            return LedMode.FORCE_OFF;
        } else if (mode == 2) {
            return LedMode.FORCE_BLINK;
        } else if (mode == 3) {
            return LedMode.FORCE_ON;
        } else {
            System.out.println("[Limelight] UNKNOWN LED MODE -- " + mode);
            return LedMode.UNKNOWN;
        }
    }

    /**
    * @param mode The LED Mode to set on the Limelight
    */
    public static void setLedMode(LedMode mode) {
        if (mode != LedMode.UNKNOWN) {
            setValue("ledMode", mode.value);
        }
    }

// --- --- --- --- --- --- --- --- --- ---

    public enum CamMode {
        VISION_CAM(0), DRIVER_CAM(1), UNKNOWN(-1);
        public final double value;

        CamMode(double value) { //TODO double/int?
            this.value = value;
        }
    }

    /**
    * @return The current LED mode set on the Limelight
    */
    public static CamMode getCamMode() {
        double mode = getValue("camMode");
        if (mode == 0) {
            return CamMode.VISION_CAM;
        } else if (mode == 1) {
            return CamMode.DRIVER_CAM;
        } else {
            System.out.println("[Limelight] UNKNOWN CAMERA MODE -- " + mode);
            return CamMode.UNKNOWN;
        }
    }

    /**
    * @param mode The LED Mode to set on the Limelight
    */
    public static void setCamMode(CamMode mode) {
        if (mode != CamMode.UNKNOWN) {
            setValue("camMode", mode.value);
        }
    }

// --- --- --- --- --- --- --- --- --- ---

    public enum Pipeline {
        PIPELINE0(0), PIPELINE1(1), PIPELINE2(2), PIPELINE3(3), PIPELINE4(4), PIPELINE5(5), 
        PIPELINE6(6), PIPELINE7(7), PIPELINE8(8), PIPELINE9(9), UNKNOWN(-1);
        
        public final double value;
        Pipeline(double value) { //TODO double/int?
            this.value = value;
        }
    }

    /**
    * @return The current LED mode set on the Limelight
    */
    public static Pipeline getCurrentPipeline() {
        double mode = getValue("pipeline");
        if (mode == 0) {
            return Pipeline.PIPELINE0;
        } else if (mode == 1) {
            return Pipeline.PIPELINE1;
        } else if (mode == 2) {
            return Pipeline.PIPELINE2;
        } else if (mode == 3) {
            return Pipeline.PIPELINE3;
        } else if (mode == 4) {
            return Pipeline.PIPELINE4;
        } else if (mode == 5) {
            return Pipeline.PIPELINE5;
        } else if (mode == 6) {
            return Pipeline.PIPELINE6;
        } else if (mode == 7) {
            return Pipeline.PIPELINE7;
        } else if (mode == 8) {
            return Pipeline.PIPELINE8;
        } else if (mode == 9) {
            return Pipeline.PIPELINE9;
        } else {
            System.out.println("[Limelight] UNKNOWN Pipeline -- " + mode);
            return Pipeline.UNKNOWN;
        }
    }

    /**
    * @param mode The LED Mode to set on the Limelight
    */
    public static void setPipeline(Pipeline mode) {
        if (mode != Pipeline.UNKNOWN) {
            setValue("pipeline", mode.value);
        }
    }

    /* Unused 
    * "stream" - Sets limelight’s streaming mode
    * 0 = Standard - Side-by-side streams if a webcam is attached to Limelight
    * 1 = PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
    * 2 = PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
    *
    * "snapshot" - Allows users to take snapshots during a match
    * 0 = Stop taking snapshots
    * 1 = Take two snapshots per second
    */
}
