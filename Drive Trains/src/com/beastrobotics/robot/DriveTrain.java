package com.beastrobotics.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

public class DriveTrain {

	private XboxController xbox;
	private RobotDrive robot;
	private Talon talonFR, talonRR, talonFL, talonRL;
	
	private final static int frontRightChannel = 1; // For TIM
	private final static int rearRightChannel = 2;
	private final static int frontLeftChannel = 3;
	private final static int rearLeftChannel = 4;
	
	JoystickClipper stickX, stickY;
	
	private double x;
	private double y;
	
	public DriveTrain() {
		talonFR = new Talon(frontRightChannel);
		talonRR = new Talon(rearRightChannel);
		talonFL = new Talon(frontLeftChannel);
		talonRL = new Talon(rearLeftChannel);

		stickX = new JoystickClipper();
		stickY = new JoystickClipper();
				
		x = 0.0;
		y = 0.0;
		
		robot = new RobotDrive(talonFL, talonRL, talonFR, talonRR);
		robot.setExpiration(0.1);
		xbox = XboxController.getInstance(1);
		robot.setInvertedMotor(talonFL, true); 
		robot.setInvertedMotor(talonRL, true);
	}
	
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

