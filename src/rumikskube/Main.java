package rumikskube;
import java.io.IOException;
import java.util.Random;

import kube.Kube;

public class Main {
	
	

	public Main() {
		super();
		int i,j;
		Random rand = new Random();
		
		Kube kube = new Kube();
		Systeme robot = new Systeme(kube);
		
		//ColorMotor cm= new ColorMotor(); 
		
		robot.scanBis();
		/*
		for(i=0; i<10; i++){
			for(j=0; j<rand.nextInt()%4; j++)
				robot.rotateCubeY();
			robot.rotateSide();
			for(j=0; j<rand.nextInt()%4; j++)
				robot.rotateCubeZ();
		}
		*/
		try {
			kube.serialize();
		} catch (IOException e) {
			System.out.println("Ca n'a pas marché");
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		new Main();
	}
	
	
	
	
	

}
