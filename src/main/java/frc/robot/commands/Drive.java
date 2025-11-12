package frc.robot.commands;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj.Timer; // new timer thing, probably won't work.

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Components;
import frc.robot.Constants;
import frc.robot.IO;


public class Drive extends Command {
	DatagramPacket dataPacket;
	DatagramSocket dataSocket;
	int counter = 0;
	boolean reverse = false;
			int speedMultiplier = 0; // not a multipliyer but added to move command.
			boolean debounceee = false;
	 static final public Timer oneSec = new Timer();
	public Drive() {
	}

	public void execute(){

		


		double leftPower;
		double rightPower;
		double xboxLY = Math.abs(IO.xboxDrive.getLeftY()) * IO.xboxDrive.getLeftY();
		double xboxRY = -Math.abs(IO.xboxDrive.getRightY()) * IO.xboxDrive.getRightY();
		

		// leftPower = (xboxY - xboxX);
		// rightPower = (xboxY + xboxX);
		leftPower = xboxLY; 
		rightPower = xboxRY; //slow modifier

		//System.out.println(leftPower + "   " + rightPower);
		System.out.println(Components.motorR3.getSelectedSensorVelocity());
		
		boolean aPressed = IO.xboxDrive.getAButtonPressed();
		boolean bPressed = IO.xboxDrive.getBButtonPressed();
		


		if (aPressed && debounceee == false) {
			debounceee = true;
			counter = 0;
			 speedMultiplier = 0.1;

		}

		if (counter >= 50 && debounceee == true) {
			speedMultiplier = 0;
			counter = 0;
		}
		if (debounceee == true) {
		counter += 1;
		}

		/*
		if(button x is pressed == true ){
		 varible = x;
		}
		else{
		varible = y
		}

		*/

		Components.motorR1.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedMultiplier);
		Components.motorR2.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedMultiplier);
		Components.motorR3.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedMultiplier);
		Components.motorL1.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedMultiplier);
		Components.motorL2.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedMultiplier);
		Components.motorL3.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedMultiplier);
		}
		
		}
	@Override
	public void initialize() {
	}
	@Override
	public boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

