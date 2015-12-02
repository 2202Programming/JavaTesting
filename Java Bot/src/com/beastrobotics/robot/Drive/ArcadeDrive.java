package com.beastrobotics.robot.Drive;

import com.beastrobotics.robot.IControl;

public class ArcadeDrive extends Drive implements IControl {
	
	private double x, y;
	
	public ArcadeDrive() {
		super();
		x = 0.0;
		y = 0.0;
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
		
		x = stickX.x();
		y = stickY.y();
		
		robot.arcadeDrive(x, y);
	}

} 

