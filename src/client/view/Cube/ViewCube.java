package client.view.Cube;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import client.view.ViewSide;
import modelCube.Cube;
import modelCube.Face;

public class ViewCube extends JPanel{

	
	protected Cube cube;
	
	protected ArrayList<ViewFace> viewFaces;
	
	public ViewCube(Cube cube) {
		super();
		this.setSize(1000, 1000);
		this.cube = cube;
		
		viewFaces = new ArrayList<ViewFace>();
		
		int x=0, y=500;
		int i=0;
		for(Face face: this.cube.faces){
			if(i<4)
				x = i*ViewSide.WIDTH + 20*i;
			else if(i==4){
				y = 500 - ViewSide.WIDTH - 20;
				x = ViewSide.WIDTH + 20;
			}
			else if(i==5){
				y = 500 + ViewSide.WIDTH + 20;
				x = ViewSide.WIDTH + 20;
			}
			
			System.out.println("x : "+String.valueOf(x)+ " , y: " + String.valueOf(y));
			viewFaces.add(new ViewFace(face, y, x));
			i++;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D gp = (Graphics2D) g;
		for(ViewFace viewSide: viewFaces)
			viewSide.draw(gp);
	}

	
}
