package rumikskube;
import java.awt.Color;

import kube.Kube;
import kube.KubeSide;
import modelCube.Case;
import modelCube.Cube;
import modelCube.Face;
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
	
	public Cube scanBis(){
		Cube cube = new Cube();
		for(int i = Cube.Front ; i < 6 ; i++){
			
			if( i < Cube.Up ){//on fait toutes les faces horizontales
				cube.setFace(i, scanFace());
				cubeMotor.rotate(90);
			} 
			else {
				if(i == Cube.Down) {
					armMotor.rotateCube();
				}			
				else {
					armMotor.rotateCube();
					armMotor.rotateCube();
				}
				cube.setFace(i, scanFace());
			}
			
		}
		armMotor.rotateCube();//on remet front en face
		return cube;
	}
	
	public Case sampleToCase(int[] sample){
		return new Case(new Color(sample[0], sample[1], sample[2]));
	}
	
	public Face scanFace(){
		int[] sample = new int[3];
		Face f = new Face();
		opticSensor.initSensor();
		colorMotor.goNominal();
		colorMotor.goCenter();
		
		sample = opticSensor.readColor();
		f.cases[0][0] = sampleToCase(sample);
		System.out.println("Couleur : " + sampleToString(sample));
		
	
		for(int i = 1 ; i < 9 ; i++){
			if( i == 1){
				colorMotor.goOuterCase();
				sample = opticSensor.readColor();
				f.setMiddle(0,  sampleToCase(sample));	
				cubeMotor.rotate(45);			
			}
			if( i%2 == 0){
				colorMotor.goCorner();
				sample = opticSensor.readColor();
				f.setCorner((i-1)/2, sampleToCase(sample));
			
			}
			else{
				colorMotor.goOuterFromCorner();
				sample = opticSensor.readColor();
				f.setMiddle((i-1)/2, sampleToCase(sample));
			}
			
			System.out.println("Couleur : " + sampleToString(sample));
			
			try {
			    Thread.sleep(100);                 //1000 milliseconds is one second. (bravo)
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
		cubeMotor.rotate(45);
		colorMotor.goNominal();
		this.opticSensor.shutdown();
		return f;
		
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
