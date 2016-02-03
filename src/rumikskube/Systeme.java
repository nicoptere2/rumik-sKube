package rumikskube;
import java.awt.Color;

import kube.Kube;
import kube.KubeSide;
import kube.KubeUnit;
import rumikskube.motors.ArmMotor;
import rumikskube.motors.ColorMotor;
import rumikskube.motors.CubeMotor;
import rumikskube.sensor.OpticSensor;


public class Systeme {

	private ArmMotor armMotor;
	private CubeMotor cubeMotor;
	private ColorMotor colorMotor;
	private OpticSensor opticSensor;
	
	private Kube kube;
	
	public Systeme(Kube kube) {
		armMotor = new ArmMotor();
		cubeMotor = new CubeMotor();
		colorMotor= new ColorMotor();
		opticSensor = new OpticSensor();
		
		this.kube = kube;
	}
	
	public void rotateSide() {
		armMotor.lockCube();
		cubeMotor.rotateSide();
		armMotor.unlockCube();
	}
	
	public void rotateCubeY() {
		armMotor.rotateCube();
	}
	
	public void rotateCubeZ() {
		cubeMotor.rotate(90);
	}
	
	public void scan() {
		int i=0;
		for(KubeSide kubeSide : this.kube) {
			if(i<4){ 
				this.scanSide(kubeSide);
				armMotor.rotateCube();
			} else if(i==4) {
				cubeMotor.rotate(90);
				armMotor.rotateCube();
				this.scanSide(kubeSide);
			} else if (i==5) {
				armMotor.rotateCube();
				armMotor.rotateCube();
				this.scanSide(kubeSide);
			}
			i++;
		}
	}
	
	public void scanSide(KubeSide kubeSide) {
		
		int[] sample = new int[3];
		opticSensor.initSensor();
		colorMotor.goNominal();
		colorMotor.goCenter();
		sample = opticSensor.readColor();
		kubeSide.getKubeUnit(0).setColor(new Color(sample[0], sample[1], sample[2]));
		System.out.println("Couleur : " + sampleToString(sample));
		
		colorMotor.goOuterCase();
		sample = opticSensor.readColor();
		kubeSide.getKubeUnit(1).setColor(new Color(sample[0], sample[1], sample[2]));
		System.out.println("Couleur : " + sampleToString(sample));
		
		for(int i=2; i<9; i++){
			cubeMotor.rotate(45);
			if(i%2==0)
				colorMotor.goCorner();
			else
				colorMotor.goOuterFromCorner();
			sample = opticSensor.readColor();
			kubeSide.getKubeUnit(i).setColor(new Color(sample[0], sample[1], sample[2]));
			System.out.println("Couleur : " + sampleToString(sample));
			try {
			    Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
		cubeMotor.rotate(45);
		colorMotor.goNominal();
		this.opticSensor.shutdown();
		
		
		/*
		int[] sample = new int[3];
		opticSensor.initSensor();
		colorMotor.goNominal();
		colorMotor.goCenter();
		
		int i=0;
		for(KubeUnit kubeUnit: kubeSide) {
			if(i==1) {
				colorMotor.goOuterCase();
			} else if (i < 7) {
				cubeMotor.rotate(45);
				if(i%2==0)
					colorMotor.goCorner();
				else
					colorMotor.goOuterFromCorner();
			}
			sample = opticSensor.readColor();
			System.out.println("Couleur : " + sampleToString(sample));
			kubeUnit.setColor(new Color(sample[0], sample[1], sample[2]));
			i++;
		}
		
		cubeMotor.rotate(45);
		colorMotor.goNominal();
		this.opticSensor.shutdown();
		*/
	}
	
	public String sampleToString(int[] sample) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		s.append(sample[0] + ", ");
		s.append(sample[1] + ", ");
		s.append(sample[2] + "]");
		return s.toString();
	}
}
