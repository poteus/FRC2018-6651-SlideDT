package org.usfirst.frc.team6651.robot.commands.auton;

import org.usfirst.frc.team6651.robot.Robot;
import org.usfirst.frc.team6651.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootInPlace extends Command {
	Timer timer = new Timer();
	private int targetRPM = 0;
    public ShootInPlace(int targetRPM) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	this.targetRPM = targetRPM;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(-Shooter.shooterFrontEnc.getRate()<targetRPM) { //was *1.2
    		Shooter.shooterFront.set(-.7);
    		Shooter.shooterBack.set(.7);
    	}
    	else {
    		Shooter.shooterFront.set(0);
    		Shooter.shooterBack.set(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get()>2);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Shooter.shooterBack.set(0);
    	Shooter.shooterFront.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
