package org.usfirst.frc.team6651.robot.subsystems;

import org.usfirst.frc.team6651.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	WPI_TalonSRX elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_CIM);
	double defaultSpeed=0.5;
	double HoldSpeed=0.2; /* to be done with encoders or break system */
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new Elevator());
    }
	
    public void go_speed(double speed) {
    		elevatorTalon.set(speed);
    }
    
    public void stop() {
		elevatorTalon.set(0);
    }
    
    public void hold_position() {
		elevatorTalon.set(HoldSpeed);
    }
    
    public void elevator_up() {
		elevatorTalon.set(defaultSpeed);
    }
    
    public void elevator_down() {
		elevatorTalon.set(-defaultSpeed);
    }

}
