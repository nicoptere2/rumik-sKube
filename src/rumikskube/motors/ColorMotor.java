package rumikskube.motors;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class ColorMotor {

	public final NXTRegulatedMotor motor = Motor.B;
	
	public ColorMotor() {
		super();
		System.out.println("coucou");
		motor.setStallThreshold(20, 200);
		motor.rotate(1000);
		if(motor.isStalled()){
			motor.stop();
		}
	}
	
	public void goCenter() {
		motor.rotate(-810);
		motor.resetTachoCount();
	}
	
	public void goOuterCase() {
		motor.rotate(190);
	}
	
	public void goCorner() {
		motor.rotate(50);
	}
	
	public void goOuterFromCorner() {
	 	motor.rotate(-50);
	}
	
	public void goNominal() {
		motor.rotate(1000);
		if(motor.isStalled()) {
			motor.stop();
			System.out.println("STOP");
		}
		motor.resetTachoCount();
	}

}
