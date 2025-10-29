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
	
	boolean reverse = false;

	public Drive() {
	}

	public void execute() {
		//Raw inputs: DOWN and RIGHT are positive
		double xboxLY = -Math.abs(IO.xboxDrive.getLeftY()) * IO.xboxDrive.getLeftY(); //UP on stick = xboxLY positive
		double xboxRX = Math.abs(IO.xboxDrive.getRightX()) * IO.xboxDrive.getRightX(); //RIGHT on stick = xboxRX positive
		System.out.println("XboxRX" + xboxRX);

		double forwardPower = xboxLY * Constants.Drive.slowModifierStraight; 
		double turnPower = xboxRX * Constants.Drive.slowModifierTurn; 

		//Setting motors to a positive value should move the robot forwards
		Components.motorR1.set(ControlMode.PercentOutput, (forwardPower - turnPower));
		Components.motorR2.set(ControlMode.PercentOutput, (forwardPower - turnPower));
		Components.motorR3.set(ControlMode.PercentOutput, (forwardPower - turnPower));
		Components.motorL1.set(ControlMode.PercentOutput, (forwardPower + turnPower));
		Components.motorL2.set(ControlMode.PercentOutput, (forwardPower + turnPower));
		Components.motorL3.set(ControlMode.PercentOutput, (forwardPower + turnPower));
		
		//System.out.println(IO.xboxDrive.getLeftY() + "foward");
		//System.out.println(IO.xboxDrive.getRightX() + "turn");
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
}
