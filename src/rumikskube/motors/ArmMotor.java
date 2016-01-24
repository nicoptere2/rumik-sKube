package rumikskube.motors;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class ArmMotor {
	
	public final NXTRegulatedMotor motor = Motor.D;
	public final int angle = 150;
	
	
	
	public void rotateCube() { 
		
		float pos1 = motor.getPosition();
		System.out.println("position 1: "+ String.valueOf(pos1));
		
		motor.setSpeed(170);
		float speed = motor.getSpeed();
		System.out.println("speed = " + String.valueOf(speed));
		
		float pos2;
		motor.rotate(-angle);
		pos2 = motor.getPosition();
		System.out.println("position 2: "+ String.valueOf(pos2));
		
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		motor.setSpeed(400);
		motor.rotate(angle);
		pos2 = motor.getPosition();
		System.out.println("position 2: "+ String.valueOf(pos2));
		
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public void lockCube() {
		float pos2;
		motor.rotate(-angle);
		pos2 = motor.getPosition();
		System.out.println("position 2: "+ String.valueOf(pos2));
	}
	
	public void unlockCube() {
			float pos2;
			motor.setSpeed(400);
			motor.rotate(angle);
			pos2 = motor.getPosition();
			System.out.println("position 2: "+ String.valueOf(pos2));
	}
}
