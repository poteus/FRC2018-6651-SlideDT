package org.usfirst.frc.team6651.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team6651.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */


public class OI 
{

	public Joystick DriverJoystick = new Joystick(RobotMap.DRIVER_USB_PORT_0);
	public Joystick ActuatorJoystick = new Joystick(RobotMap.ACTUATOR_USB_PORT_1);
		
	public Button Trigger_Left_2 = new JoystickButton(ActuatorJoystick, 7);
	public Button Trigger_Right_2 = new JoystickButton(ActuatorJoystick, 8);
	public Button POV_Up = new JoystickButton(ActuatorJoystick, 4);
	public Button POV_Down = new JoystickButton(ActuatorJoystick, 2);

	public OI()
	{
		POV_Up.whileHeld(new ElevatorCmd(RobotMap.UP));
		POV_Down.whileHeld(new ElevatorCmd(RobotMap.DOWN));
		Trigger_Left_2.whileHeld(new GrabberCmd(RobotMap.CUBE_OUT));
		Trigger_Right_2.whileHeld(new GrabberCmd(RobotMap.CUBE_IN));
	}
}
