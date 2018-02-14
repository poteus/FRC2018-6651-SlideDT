package org.usfirst.frc.team6651.robot.commands;

import org.usfirst.frc.team6651.robot.Robot;
import org.usfirst.frc.team6651.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class AutoSwitch extends Command {
	
	// Encoders for distance
	Encoder RightEncoder;
	Encoder LeftEncoder;
	Encoder ElevatorEncoder;
	int countRight, countLeft;
	double ticks_per_inch=250;
	
	// Gyro to straight and turn
	ADXRS450_Gyro gyro;
	double angle;
	double kp=0.004;
	double rotationSpeed=.2;
	
	// Drive distances in feet
	double DistanceToSwitch = 5; 
	double DistanceToScale = 10;
	double DistanceToSwitchWall = 2;
	double DistanceToScaleWall = 1;
	double DistanceGoal1; // Distance for 1st trip
	double DistanceGoal2; // Distance for 2nd trip
	
	double AutoChoice; // Choices between Cross Line, Switch or Scale
	double AutoStage;  // Stage during the autonomous, ex: stage turning 90 degrees, etc. 
	
	double direction; // 1 or -1 for Right or Left
	String Side_Switch;
	String Side_Scale;
	String gameData = "";
	String OurSide = "L"; // Side we are set up
	
    public AutoSwitch() {
      requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		reset_encoders();
		// gyro.calibrate(); If Gyro does not reset
		gyro.reset();
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		Side_Switch = GameDataSwitchDirection();
		Side_Scale = GameDataScaleDirection();
		
		if(Side_Scale == OurSide) {
			DistanceGoal1 = DistanceToScale;
			DistanceGoal2 = DistanceToScaleWall;
			AutoChoice=2;
		}
		else if(Side_Switch == OurSide) {
			DistanceGoal1 = DistanceToSwitch;
			DistanceGoal2 = DistanceToSwitchWall;
			AutoChoice=1;
		}
		else {
			DistanceGoal1 = DistanceToScale;
			DistanceGoal2 = 0;
			AutoChoice=0;
		}
		
		direction = (OurSide.substring(0, 0)=="L") ? RobotMap.LEFT : RobotMap.RIGHT;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		AutoStage=detectStage();
		
		if (AutoStage == 0) drive_straight(.5);
		if (AutoStage == 1) Robot.driveTrain.drive(0, direction*rotationSpeed, 0);
		if (AutoStage == 2) drive_straight(.5);
		if (AutoStage == 3) Robot.driveTrain.drive(0, 0, 0);	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		if (AutoStage == 3) return true;
        return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.driveTrain.drive(0, 0, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.driveTrain.drive(0, 0, 0);
    }
    
    public void drive_straight(double speed) {
		angle = gyro.getAngle();
		Robot.driveTrain.drive(speed,-angle*kp,0);
    }
    
    public double distance(double count) {
    		return count/ticks_per_inch;
    }
    
    public void reset_encoders() {
    		RightEncoder.reset();
		LeftEncoder.reset();
    }
    
    public int detectStage() {
    		double DistanceTravelled;
    		
		countRight = RightEncoder.get();
		countLeft = LeftEncoder.get();
		angle = gyro.getAngle();
		
		DistanceTravelled = distance((countRight+countLeft)/2);
    		if (DistanceTravelled<DistanceGoal1) {
    			reset_encoders();
    			return 0;
    		}
    		else if(angle<90 && AutoChoice != 0) return 1;
    		else if(DistanceTravelled<DistanceGoal2 && AutoChoice != 0) return 2;
    		return 3;
    }
    
    public String GameDataSwitchDirection() {
    		return gameData.substring(0, 0);
    }
    
    public String GameDataScaleDirection() {
		return gameData.substring(1, 1);
    }
}
