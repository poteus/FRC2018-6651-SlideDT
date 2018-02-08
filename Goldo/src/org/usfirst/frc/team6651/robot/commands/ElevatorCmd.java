package org.usfirst.frc.team6651.robot.commands;

import org.usfirst.frc.team6651.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class ElevatorCmd extends Command {
    
	private int direction;
    
	public ElevatorCmd(int dir) {
    		super("ElevatorLift");
    		requires(Robot.elevator);
    		direction = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		direction = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		if (direction == 1){
    			Robot.elevator.elevator_up();
    		} 
    		else if (direction == -1) {
    			Robot.elevator.elevator_down();
    		}
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
    		Robot.elevator.stop();
    		direction = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		direction = 0;
    }
}