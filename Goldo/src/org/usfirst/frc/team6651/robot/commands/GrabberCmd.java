package org.usfirst.frc.team6651.robot.commands;

import org.usfirst.frc.team6651.robot.Robot;
import org.usfirst.frc.team6651.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class GrabberCmd extends Command {
    
	private int direction;
    
	public GrabberCmd(int dir) {
    		super("Grabber");
    		requires(Robot.elevator);
    		direction = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		direction = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double angle = Robot.oi.DriverJoystick.getPOV();
    		if(angle==-1) /* POV not pressed */
    			if (direction == 1)
    				Robot.grabber.grabber_move(RobotMap.CUBE_IN);
    			else if (direction == -1) 
    				Robot.grabber.grabber_move(RobotMap.CUBE_OUT);  			
    		else  		  /* POV Pressed */
    			if(angle==135 || angle==225 || angle == 180)
    				Robot.grabber.grabber_move(RobotMap.CUBE_IN);
    			else if(angle==315 || angle==45 || angle == 0)
    				Robot.grabber.grabber_move(RobotMap.CUBE_OUT);
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		/*if (direction == 1){
    			return Robot.elevator.isUp();
    		} 
    		else if (direction == -1){
    			return elevator.isDown();
    		} 
    		else 
    		{
    			return true;
    		}*/
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.grabber.grabber_stop();
    		direction = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		direction = 0;
    }
}