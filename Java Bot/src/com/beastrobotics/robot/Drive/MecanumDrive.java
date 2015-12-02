package com.beastrobotics.robot.Drive;

import com.beastrobotics.robot.IControl;
import com.beastrobotics.robot.controllers.JoystickClipper;

public class MecanumDrive extends Drive implements IControl {

	private JoystickClipper stickTwist;
	
	private double x, y, twist;
	
	public MecanumDrive() {
		super();
		x = 0.0;
		y = 0.0;
		twist = 0.0;
		
		stickTwist = new JoystickClipper();
	}
	 
	public void robotInit() {}
 
	public void autonomousInit() {}
	
	public void autonomousPeriodic() {}

	public void teleopInit() {
		robot.setSafetyEnabled(false);
	}
 
	public void teleopPeriodic() {
		stickX.update(xbox.getAxisLeftX(),xbox.getAxisLeftX());
		stickY.update(xbox.getAxisLeftY(),xbox.getAxisLeftY());
		stickTwist.update(xbox.getAxisRightX(),xbox.getAxisRightX());
		
		x = stickX.x();
		y = stickY.y();
		twist = stickTwist.x();
		
		robot.mecanumDrive_Cartesian(x, y, twist, 0);
		dashboard(x, y, twist);
	}
}
