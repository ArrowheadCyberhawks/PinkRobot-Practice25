package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Components;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.ctre.phoenix.motorcontrol.ControlMode;


public class LimelightTrack extends Command {
	DatagramPacket dataPacket;
	DatagramSocket dataSocket;
	

	public LimelightTrack() {
	}

	public void execute(){
		double leftPower = 0;
		double rightPower = 0;
        double x = Limelight.tx.getDouble(0.0);
		double a = Limelight.ta.getDouble(0.0);
        if (x == 0) {
		} else if(Math.abs(x) < 0.1) {
            return;
        } else{
            rightPower = -x/100;
            leftPower = x/100;
        }

		if(a < 0.84 && a > 0) {
			leftPower += 0.3;
			rightPower += 0.3;
		}
		else if (a > 3 && a < 18) {
			leftPower -= 0.3;
			rightPower -= 0.3;
		}
 
		leftPower *= Constants.Drive.slowModifier;
		rightPower *= Constants.Drive.slowModifier; 
		System.out.println(leftPower + "   " + rightPower);
		
		Components.motorR1.set(ControlMode.PercentOutput, rightPower);
		Components.motorR2.set(ControlMode.PercentOutput, rightPower);
		Components.motorR3.set(ControlMode.PercentOutput, rightPower);
		Components.motorL1.set(ControlMode.PercentOutput, leftPower);
		Components.motorL2.set(ControlMode.PercentOutput, leftPower);
		Components.motorL3.set(ControlMode.PercentOutput, leftPower);
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
