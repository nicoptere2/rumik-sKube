package rumikskube;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import rumikskube.motors.ArmMotor;
import rumikskube.motors.CubeMotor;

public class Main {
	
	

	public Main() {
		super();
		
		Systeme robot = new Systeme();
		robot.scan();
		
		
	}


	public static void main(String[] args) {
		new Main();
	}
	
	
	
	
	

}
