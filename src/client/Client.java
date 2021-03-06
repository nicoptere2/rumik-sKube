package client;

import java.awt.Dimension;

import javax.swing.JFrame;

import client.view.ViewKube;
import client.view.Cube.ViewCube;
import kube.Kube;
import modelCube.Cube;
import solver.Solver;

public class Client extends JFrame{
	
	public Client() {
		super("Rubik's cube");
		this.setPreferredSize(new Dimension(1000, 700)) ;

		/*Kube cube = null;
		try {
			cube = Kube.restore();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Ca a marché");
		System.out.println(cube);
		
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*    
        kube.lighten((float)0.40);
        kube.sature((float)1);
        
         Categorize categorize = new Categorize(kube);
         categorize.execute();
         

        cube.lighten((float)1);
        cube.sature((float)1);


        
        
        ViewKube viewKube= new ViewKube(cube);
        this.add(viewKube);
		*/
		
		try{
			Cube cube = Cube.restore();
			System.out.println(cube);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	        //cube.lighten((float)1);
	        //cube.sature((float)1);
	        //Solver.sort(cube);
	        ViewCube viewKube1= new ViewCube(cube);
	        this.add(viewKube1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
        pack();
        setVisible(true);    
	}
	

	public static void main(String[] args) {
		new Client();
	}

}
