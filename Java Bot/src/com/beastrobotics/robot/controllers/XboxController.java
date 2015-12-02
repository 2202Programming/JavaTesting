package com.beastrobotics.robot.controllers;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Joystick.AxisType;

public class XboxController {

	private final int DEBOUNCE_COUNT_LIMIT = 15;
	private final double REAL_TIME_BETWEEN_UPDATES = 0.05;
	private final int BUTTON_A = 1;
	private final int BUTTON_B = 2;
	private final int BUTTON_X = 3;
	private final int BUTTON_Y = 4;
	private final int BUTTON_LB = 5;
	private final int BUTTON_RB = 6;
	private final int BUTTON_BACK = 7;
	private final int BUTTON_START = 8;
	private final int BUTTON_L3 = 9; 
	private final int BUTTON_R3 = 10;
	
	private final int AXIS_LEFT_X = 0;
	private final int AXIS_LEFT_Y = 1;
	private final int AXIS_RIGHT_X = 4;
	private final int AXIS_RIGHT_Y = 5;
	private final int AXIS_TRIGGER_LEFT = 2;
	private final int AXIS_TRIGGER_RIGHT = 3;
	private final int JOG_DEBOUNCE = 10;
	
	private static XboxController Xbox;
	private static XboxController Xbox2;

	private Joystick rStick;
	private Joystick lStick;

	private Timer timer;

	private double lastTime, lostTimeBank;

	private int xDebounceCounter, yDebounceCounter, aDebounceCounter, bDebounceCounter, startDebounceCounter,
			backDebounceCounter;
	private int leftBumperDebounceCounter, rightBumperDebounceCounter, r3DebounceCounter, l3DebounceCounter;
	private int leftTriggerDebounceCounter, rightTriggerDebounceCounter;

	private boolean xLast, xNow;
	private boolean yLast, yNow;
	private boolean aLast, aNow;
	private boolean bLast, bNow;
	private boolean startLast, startNow;
	private boolean backLast, backNow;
	private boolean leftBumperNow, leftBumperLast;
	private boolean rightBumperNow, rightBumperLast;
	private boolean l3Last, l3Now;
	private boolean r3Last, r3Now;
	private boolean leftTriggerLast, leftTriggerNow;
	private boolean rightTriggerLast, rightTriggerNow;

	private XboxController(int port) {
		rStick = new Joystick(port);
		lStick = new Joystick(port);

		rStick.setAxisChannel(AxisType.kX, 4);
		rStick.setAxisChannel(AxisType.kY, 5);

		timer = new Timer();
		timer.start();

		lostTimeBank = 0.0;
		lastTime = timer.get();

		xLast = false;
		xNow = false;
		yLast = false;
		yNow = false;
		aLast = false;
		aNow = false;
		bLast = false;
		bNow = false;
		startLast = false;
		startNow = false;
		backLast = false;
		backNow = false;
		leftBumperLast = false;
		leftBumperNow = false;
		rightBumperLast = false;
		rightBumperNow = false;
		l3Last = false;
		l3Now = false;
		r3Last = false;
		r3Now = false;
		leftTriggerLast = false;
		leftTriggerNow = false;
		rightTriggerLast = false;
		rightTriggerNow = false;

		xDebounceCounter = 0;
		yDebounceCounter = 0;
		aDebounceCounter = 0;
		bDebounceCounter = 0;
		startDebounceCounter = 0;
		backDebounceCounter = 0;
		leftBumperDebounceCounter = 0;
		rightBumperDebounceCounter = 0;
		l3DebounceCounter = 0;
		r3DebounceCounter = 0;
		leftTriggerDebounceCounter = 0;
		rightTriggerDebounceCounter = 0;
	}

	public static XboxController getInstance(int port) {
		if (port == 0) {
			if (Xbox == null)
				Xbox = new XboxController(0); // this is the first time
												// getInstance is called
			return Xbox;
		} else {
			if (Xbox2 == null)
				Xbox2 = new XboxController(port); // this is the first time
													// getInstance is called for
													// this port
			return Xbox2;
		}
	}

	public void update() {
		xLast = xNow;
		yLast = yNow;
		aLast = aNow;
		bLast = bNow;
		startLast = startNow;
		backLast = backNow;
		leftBumperLast = leftBumperNow;
		rightBumperLast = rightBumperNow;
		l3Last = l3Now;
		r3Last = r3Now;
		leftTriggerLast = leftTriggerNow;
		rightTriggerLast = rightTriggerNow;

		accountForLostTime();

		xNow = isButtonHeld(xDebounceCounter, rStick.getRawButton(BUTTON_X));
		yNow = isButtonHeld(yDebounceCounter, rStick.getRawButton(BUTTON_Y));
		aNow = isButtonHeld(aDebounceCounter, rStick.getRawButton(BUTTON_A));
		bNow = isButtonHeld(bDebounceCounter, rStick.getRawButton(BUTTON_B));
		startNow = isButtonHeld(startDebounceCounter, rStick.getRawButton(BUTTON_START));
		backNow = isButtonHeld(backDebounceCounter, rStick.getRawButton(BUTTON_BACK));
		leftBumperNow = isButtonHeld(leftBumperDebounceCounter, rStick.getRawButton(BUTTON_LB));
		rightBumperNow = isButtonHeld(rightBumperDebounceCounter, rStick.getRawButton(BUTTON_RB));
		l3Now = isButtonHeld(l3DebounceCounter, rStick.getRawButton(BUTTON_L3));
		r3Now = isButtonHeld(r3DebounceCounter, rStick.getRawButton(BUTTON_R3));
		leftTriggerNow = isButtonHeld(leftTriggerDebounceCounter, getAxisTriggerLeft() > 0.8);
		rightTriggerNow = isButtonHeld(rightTriggerDebounceCounter, getAxisTriggerRight() > 0.8);
	}

	private void accountForLostTime() {
		double currentTime = timer.get();
		lostTimeBank += currentTime - lastTime - REAL_TIME_BETWEEN_UPDATES;

		while (lostTimeBank >= REAL_TIME_BETWEEN_UPDATES) {
			updateAllCounters();
			lostTimeBank -= REAL_TIME_BETWEEN_UPDATES;
		}

		lastTime = currentTime;
	}
	
	private void updateAllCounters() {
		xDebounceCounter++;
		yDebounceCounter++;
		aDebounceCounter++;
		bDebounceCounter++;
		startDebounceCounter++;
		backDebounceCounter++;
		leftBumperDebounceCounter++;
		rightBumperDebounceCounter++;
		l3DebounceCounter++;
		r3DebounceCounter++;
		leftTriggerDebounceCounter++;
		rightTriggerDebounceCounter++;
	}
	
	private boolean isButtonHeld(int debounceCounter, boolean rawValue) {
		if (rawValue) {
			debounceCounter++;
			if (debounceCounter > DEBOUNCE_COUNT_LIMIT) return true;
		}
		else {
			debounceCounter=0;
		}
		return false;
	}
	
	public double getAxisRightX() {
		return (-1.0)*rStick.getRawAxis(AXIS_RIGHT_X);
	}

	public double getAxisRightY() {
		return (-1.0)*rStick.getRawAxis(AXIS_RIGHT_Y);
	}

	public double getAxisLeftX() {
		return (-1.0)*lStick.getRawAxis(AXIS_LEFT_X);
	}

	public double getAxisLeftY() {
		return (-1.0)*lStick.getRawAxis(AXIS_LEFT_Y);
	}

	public boolean getXPressed() {
		return (!xLast)&&(xNow);
	}

	public boolean getYPressed() {
		return (!yLast)&&(yNow);
	}

	public boolean getAPressed() {
		return (!aLast)&&(aNow);
	}

	public boolean getBPressed() {
		return (!bLast)&&(bNow);
	}

	public boolean getStartPressed() {
		return (!startLast)&&(startNow);
	}

	public boolean getBackPressed() {
		return (!backLast)&(backNow);
	}

	public boolean getLeftBumperPressed() {
		return (!leftBumperLast)&&(leftBumperNow);
	}

	public boolean getRightBumperPressed() {
		return (!rightBumperLast)&&(rightBumperNow);
	}

	public boolean getL3Pressed() {
		return (!l3Last)&&(l3Now);
	}

	public boolean getR3Pressed() {
		return (!r3Last)&&(r3Now);
	}

	public boolean getLeftTriggerPressed() {
		return (!leftTriggerLast)&&(leftTriggerNow);
	}

	public boolean getRightTriggerPressed() {
		return (!rightTriggerLast)&&(rightTriggerNow);
	}

	public boolean getXHeld() {
		return xLast&&xNow;
	}

	public boolean getYHeld() {
		return yLast&&yNow;
	}

	public boolean getAHeld() {
		return aLast&&aNow;
	}

	public boolean getBHeld() {
		return bLast&&bNow;
	}

	public boolean getStartHeld() {
		return startLast&&startNow;
	}

	public boolean getBackHeld() {
		return backLast&&backNow;
	}

	public boolean getLeftBumperHeld() {
		return leftBumperLast&&leftBumperNow;
	}

	public boolean getRightBumperHeld() {
		return rightBumperLast&&rightBumperNow;
	}

	public boolean getL3Held() {
		return l3Last&&l3Now;
	}

	public boolean getR3Held() {
		return r3Last&&r3Now;
	}

	public boolean getLeftTriggerHeld() {
		return leftTriggerLast&&leftTriggerNow;
	}

	public boolean getRightTriggerHeld() {
		return rightTriggerLast&&rightTriggerNow;
	}

	public double getAxisTriggerRight() {
		return rStick.getRawAxis(AXIS_TRIGGER_RIGHT);
	}

	public double getAxisTriggerLeft() {
		return rStick.getRawAxis(AXIS_TRIGGER_LEFT);
	}
}
