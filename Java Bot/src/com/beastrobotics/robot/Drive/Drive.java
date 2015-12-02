package com.beastrobotics.robot.Drive;

import com.beastrobotics.robot.controllers.JoystickClipper;
import com.beastrobotics.robot.controllers.XboxController;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {

	protected XboxController xbox;
	protected RobotDrive robot;
	protected Talon talonFR, talonRR, talonFL, talonRL;
	protected JoystickClipper stickX, stickY;
	
	protected final static int frontRightChannel = 1; // For TIM
	protected final static int rearRightChannel = 2;
	protected final static int frontLeftChannel = 3;
	protected final static int rearLeftChannel = 4;
	
	
	Drive() {
		talonFR = new Talon(frontRightChannel);
		talonRR = new Talon(rearRightChannel);
		talonFL = new Talon(frontLeftChannel);
		talonRL = new Talon(rearLeftChannel);

		stickX = new JoystickClipper();
		stickY = new JoystickClipper();
		
		robot = new RobotDrive(talonFL, talonRL, talonFR, talonRR);
		robot.setExpiration(0.1);
		xbox = XboxController.getInstance(1);
	}
	
	private void motorDashboard() {
		SmartDashboard.putNumber("Front Left", talonFL.get());
		SmartDashboard.putNumber("Front Right", talonFR.get());
		SmartDashboard.putNumber("Rear Left", talonRR.get());
		SmartDashboard.putNumber("Rear Right", talonRL.get());	
	}
	
	protected void dashboard(double x, double y) { // Arcade
		SmartDashboard.putNumber("Joystick X", x);
		SmartDashboard.putNumber("joystick Y", y);	
		motorDashboard();
	}
	
	protected void dashboard(double x, double y, double twist) { // Mecanum
		SmartDashboard.putNumber("Joystick X", x);
		SmartDashboard.putNumber("joystick Y", y);	
		SmartDashboard.putNumber("Joystick Twist", twist);
		motorDashboard();
	}
}
