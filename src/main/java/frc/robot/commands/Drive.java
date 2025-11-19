package frc.robot.commands;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.ctre.phoenix.motorcontrol.ControlMode;



import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Components;
import frc.robot.Constants;
import frc.robot.IO;


public class Drive extends Command {
	DatagramPacket dataPacket;
	DatagramSocket dataSocket;
	int counter = 0;
	boolean reverse = false;
			double speedAdderL; //added to move command.
			double speedAdderR;
			boolean debounceee = false;
	double speedOn = 0;
	int counterChecker;
	boolean aPressedEver = false;
	int instructionStep;
	 dataType[] arrayInstructions = new String[4];
     dataType[] arrayInstructions = {"forward", "backward", "CCW", "CW"};
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
		


		if (aPressed) {
			aPressedEver = true;
		}

		if (aPressedEver) {
			 mode = arrayInstructions[instructionStep];

				if (isFinished2) {
					instructionStep += 1;
				}

			counter += 0.02;
		}




		

		if (bPressed) {
			speedAdder = 0;
			counter = 100000;
			speedOn = 0;
		}

		public boolean instructions(int instructionCounter, string mode) {
			counterChecker = counter+instructionCounter;
			if (counter <= instructionCounter) {
			if (mode == "forward") {
				speedAdderL = 0.15;
				speedAdderR = 0.15;
			}
			if (mode == "CW") {
				speedAdderL = 0.25;
				speedAdderR = -0.25;
			} 
			if (mode == "CCW") {
				speedAdderL = -0.25;
				speedAdderR = 0.25;
			}
			if (mode == "back") {
				speedAdderL = -0.15;
				speedAdderR = -0.15;			
			}
			} else {
			boolean isFinished2 = true;
			return isFinished2;
			}
		}
		/*
		if(button x is pressed == true ){
		 varible = x;
		}
		else{
		varible = y
		}

		*/
		
		Components.motorR1.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedAdderR+speedOn);
		Components.motorR2.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedAdderR+speedOn);
		Components.motorR3.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower+speedAdderR+speedOn);
		Components.motorL1.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedAdderL+speedOn);
		Components.motorL2.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedAdderL+speedOn);
		Components.motorL3.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower-speedAdderL+speedOn);


		
		
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
}
