package org.usfirst.frc.team6651.robot.commands.drive;

import org.usfirst.frc.team6651.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int x_axis=0, y_axis=1, z_rotate=2, slider=3
    	// To drive normal arcade: 
    	//  -  Joystick forward moves robot forward
    	//  -  Joyrstick sideways rotates robot
    	// Robot.driveTrain.arcadeDrive(Robot.oi.getLeftJoystick(), Robot.oi.getRightJoystick());
    	
    	// To slide drive: 
    	//  -  Joystick forward moves robot forward
    	//  -  Joystick sideways slides robot
    	//  -  Joystick rotation around z axis rotates robot
    	//  CLASS DEFINITION:
    	//  void RobotDrive::ArcadeDrive	(	GenericHID * 	moveStick,
    //                                 	unsigned 	moveAxis,
    	//									GenericHID * 	rotateStick,
    	//									unsigned 	rotateAxis,
    	//									GenericHID * 	spinStick = NULL,
    	//									unsigned 	spinButton = 0	 )	
    	Robot.driveTrain.arcadeDrive(Robot.oi.joystick.getRawAxis(x_axis), Robot.oi.joystick.getRawAxis(z_rotate)); // Check for 2nd argument: 2?
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setPowerZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
