package com.beastrobotics.robot.Drive;

import com.beastrobotics.robot.IControl;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArcadeDrive extends Drive implements IControl {
	
	private double x, y;
	private double speedFactor;
	
	public ArcadeDrive() {
		super();
		x = 0.0;
		y = 0.0;
		
		speedFactor = 0;
	}
	
	public void robotInit() {}

	public void autonomousInit() {}

	public void autonomousPeriodic() {}
	
	public void teleopInit() {
		robot.setSafetyEnabled(false);
		
		SmartDashboard.putNumber("Front Left", 0);
		SmartDashboard.putNumber("Front Right", 0);
		SmartDashboard.putNumber("Rear Left", 0);
		SmartDashboard.putNumber("Rear Right", 0);
		
		SmartDashboard.putNumber("Speed Factor", 0);
	}
	
	public void teleopPeriodic() {
		stickX.update(xbox.getAxisLeftX(),xbox.getAxisLeftX());
		stickY.update(xbox.getAxisLeftY(),xbox.getAxisLeftY());
		
		speedFactor = SmartDashboard.getNumber("Speed Factor", 0);
		
		x = stickX.x();
		y = stickY.y();
		
		robot.arcadeDrive(x * speedFactor, y * speedFactor);
		dashboard(x, y);
	}
} 

