package org.usfirst.frc.team6651.robot.vision.android.vision;

/**
 * A basic interface for classes that get VisionUpdates. Classes that implement
 * this interface specify what to do when VisionUpdates are received.
 * 
 * @see VisionUpdate.java
 */
public interface VisionUpdateReceiver {
    void gotUpdate(VisionUpdate update);
}
