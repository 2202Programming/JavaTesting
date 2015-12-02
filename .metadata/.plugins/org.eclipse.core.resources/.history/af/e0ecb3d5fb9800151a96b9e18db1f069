package com.beastrobotics.robot;

public class JoystickClipper {
	private double xValue;
	private double yValue;
	private double deadband;
	private double maxLength;
	private double minLength;
	
	public JoystickClipper() {
		deadband = 0.09;
		maxLength = 1.0;
		minLength = 0.15;
		xValue = 0.0;
		yValue = 0.0;
	}

	public double x() {
		return xValue;
	}

	public double y() {
		return yValue;
	}

	public double getDeadband() {
		return deadband;
	}

	public void setDeadband(double value) {
		deadband = value;
	}

	public double getMaxValue() {
		return maxLength;
	}

	public void setMaxValue(double value) {
		maxLength = value;
	}

	public double getMinValue() {
		return minLength;
	}

	void setMinValue(double value) {
		minLength = value;
	}

	void update(double x, double y) {
		double vectorLength = Math.sqrt(x * x + y * y);
		if (vectorLength <= deadband) {
			xValue = 0;
			yValue = 0;
		} else {
			xValue = clipAxis(x);
			yValue = clipAxis(y);
		}
	}


	private double clipAxis(double value){
		if (Math.abs(value) <= deadband){
			return 0.0;
		}
		double percent = (Math.abs(value)- deadband) /( 1-deadband);
		if (value>0){
			return minLength + percent*(maxLength-minLength);
		}
		return -minLength - percent*(maxLength-minLength);
	}
}

