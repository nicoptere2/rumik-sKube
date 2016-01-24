package rumikskube;
import lejos.hardware.sensor.EV3ColorSensor;
import rumikskube.motors.ArmMotor;
import rumikskube.motors.ColorMotor;
import rumikskube.motors.CubeMotor;
import rumikskube.sensor.OpticSensor;


public class Systeme {

	private ArmMotor armMotor;
	private CubeMotor cubeMotor;
	private ColorMotor colorMotor;
	private OpticSensor opticSensor;
	
	public Systeme() {
		armMotor = new ArmMotor();
		cubeMotor = new CubeMotor();
		colorMotor= new ColorMotor();
		opticSensor = new OpticSensor();
	}
	
	public void rotateSide() {
		armMotor.lockCube();
		cubeMotor.rotateSide();
		armMotor.unlockCube();
	}
	
	public void rotateCube() {
		armMotor.rotateCube();
	}
	
	public void scan() {
		for(int i=0; i<4; i++) {
			this.scanSide();
			armMotor.rotateCube();
		}
		cubeMotor.rotate(90);
		armMotor.rotateCube();
		this.scanSide();
		
		armMotor.rotateCube();
		armMotor.rotateCube();
		this.scanSide();
			
	}
	
	public void scanSide() {
		float[] sample = new float[3];
		colorMotor.goNominal();
		colorMotor.goCenter();
		sample = opticSensor.readColor();
		System.out.println("Couleur : " + sampleToString(sample));
		colorMotor.goOuterCase();
		sample = opticSensor.readColor();
		System.out.println("Couleur : " + sampleToString(sample));
		for(int i=0; i<7; i++){
			cubeMotor.rotate(45);
			if(i%2==0)
				colorMotor.goCorner();
			else
				colorMotor.goOuterFromCorner();
			sample = opticSensor.readColor();
			System.out.println("Couleur : " + sampleToString(sample));
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		cubeMotor.rotate(45);
		colorMotor.goNominal();
	}
	
	public String sampleToString(float[] sample) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		s.append(sample[0] + ", ");
		s.append(sample[1] + ", ");
		s.append(sample[2] + ", ");
		s.append(sample[3] + ", ");
		s.append(sample[4] + ", ");
		s.append(sample[5] + "]");
		return s.toString();
	}
}
