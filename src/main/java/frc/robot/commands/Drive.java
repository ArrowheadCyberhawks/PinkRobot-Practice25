package frc.robot.commands;  

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.ctre.phoenix.motorcontrol.ControlMode;// this is my comment (made by cole)

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

	public void execute(){
		

		double leftPower;
		double rightPower;
		double xboxRY = Math.abs(IO.xboxDrive.getLeftY()) * IO.xboxDrive.getLeftY(); //does switching the L and R change ts controller setting or does it just drive an error
		double xboxLY = -Math.abs(IO.xboxDrive.getRightY()) * IO.xboxDrive.getRightY();//wait can sam see this can sam and cole see this message 


		// leftPower = (xboxY - xboxX);
		// rightPower = (xboxY + xboxX);
		leftPower = xboxRY; 
		rightPower = xboxLY; //slow modifier

		boolean BButton;
			BButton = IO.xboxDrive.getBButton();
			if (BButton) { // .
			leftPower = 0.75; //75 percent 
			rightPower = 0.45;// 45 percent
			}
			else {
			leftPower = xboxRY; 
			rightPower = xboxLY;
			}
			

		//System.out.println(leftPower + "   " + rightPower);
		System.out.println(Components.motorR3.getSelectedSensorVelocity());
		Components.motorR1.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower);
		Components.motorR2.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower);
		Components.motorR3.set(ControlMode.PercentOutput, Constants.Drive.slowModifier*rightPower);
		Components.motorL1.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower);
		Components.motorL2.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower);
		Components.motorL3.set(ControlMode.PercentOutput, -Constants.Drive.slowModifier*leftPower);
		

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