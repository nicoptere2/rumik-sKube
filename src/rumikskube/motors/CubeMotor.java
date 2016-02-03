package rumikskube.motors;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class CubeMotor {
	
	final NXTRegulatedMotor motor = Motor.C;
	final int nominalRotation = 3;
	
	public void rotate(){
		float pos1 = motor.getPosition();
		System.out.println("position 1: "+ String.valueOf(pos1));
		
		motor.setSpeed(300);
		float speed = motor.getSpeed();
		System.out.println("speed = " + String.valueOf(speed));
		
		float pos2;
		motor.rotate(nominalRotation * 90 + 40);
		pos2 = motor.getPosition();
		System.out.println("position 2: "+ String.valueOf(pos2));
	}
	
	public void rotateSide(){
		this.rotate();
		this.setRotation(-40);
	}
	
	public void setRotation(int rotation){
		motor.rotate(rotation);
	}
	
	public void rotate(int angle) {
		motor.setSpeed(200);
		motor.rotate(nominalRotation * angle);
	}
}
