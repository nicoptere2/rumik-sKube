package client.view.Cube;

import java.awt.Graphics2D;
import java.util.ArrayList;

import client.view.ViewUnit;
import modelCube.Case;
import modelCube.Face;

public class ViewFace {

	protected Face face;
	
	protected ArrayList<ViewCase> viewUnits;
	
	protected int x;
	protected int y;
	
	public static final int WIDTH = ViewUnit.WIDTH*3;
	
	public ViewFace(Face face, int x, int y) {
		this.face = face;
		this.x = x;
		this.y = y;
		
		viewUnits = new ArrayList<ViewCase>();
		
		Case c;
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 3 ; j++){
				c = this.face.cases[i][j];
				switch (i*3 + j) {
				case 0: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH, y+ViewUnit.WIDTH));
						break;
				case 1: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH*2, y+ViewUnit.WIDTH));
						break;
				case 2: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH*2, y+ViewUnit.WIDTH*2));
						break;
				case 3: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH, y+ViewUnit.WIDTH*2));
						break;
				case 4: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH*2, y));
						break;
				case 5: viewUnits.add(new ViewCase(c, x+ViewUnit.WIDTH, y));
						break;
				case 6: viewUnits.add(new ViewCase(c, x, y));
						break;
				case 7: viewUnits.add(new ViewCase(c, x, y+ViewUnit.WIDTH));
						break;
				case 8: viewUnits.add(new ViewCase(c, x, y+ViewUnit.WIDTH*2));
						break;
				default : System.out.println("il y a trop de KubeUnit ici");
				}
				
				
			}
		}
			
	}
	
	public void draw(Graphics2D gp) {
		for(ViewCase viewUnit: this.viewUnits)
			viewUnit.draw(gp);
	}
	
	
}
