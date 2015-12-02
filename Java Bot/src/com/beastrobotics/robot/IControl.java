package com.beastrobotics.robot;

public interface IControl {

	public void robotInit();
	
	public void autonomousInit();
	
	public void autonomousPeriodic();
	
	public void teleopInit();
	
	public void teleopPeriodic();
}
