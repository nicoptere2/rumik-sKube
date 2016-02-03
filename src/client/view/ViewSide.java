package client.view;

import java.awt.Graphics2D;
import java.util.ArrayList;

import kube.KubeSide;
import kube.KubeUnit;

public class ViewSide {
	
	protected KubeSide kubeSide;
	
	protected ArrayList<ViewUnit> viewUnits;
	
	protected int x;
	protected int y;
	
	public static final int WIDTH = ViewUnit.WIDTH*3;
	
	public ViewSide(KubeSide kubeSide, int x, int y) {
		this.kubeSide = kubeSide;
		this.x = x;
		this.y = y;
		
		viewUnits = new ArrayList<ViewUnit>();
		
		int i = 0;
		for(KubeUnit kubeUnit: this.kubeSide) {
			switch (i) {
			case 0: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH, y+ViewUnit.WIDTH));
					break;
			case 1: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH*2, y+ViewUnit.WIDTH));
					break;
			case 2: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH*2, y+ViewUnit.WIDTH*2));
					break;
			case 3: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH, y+ViewUnit.WIDTH*2));
					break;
			case 4: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH*2, y));
					break;
			case 5: viewUnits.add(new ViewUnit(kubeUnit, x+ViewUnit.WIDTH, y));
					break;
			case 6: viewUnits.add(new ViewUnit(kubeUnit, x, y));
					break;
			case 7: viewUnits.add(new ViewUnit(kubeUnit, x, y+ViewUnit.WIDTH));
					break;
			case 8: viewUnits.add(new ViewUnit(kubeUnit, x, y+ViewUnit.WIDTH*2));
					break;
			default : System.out.println("il y a trop de KubeUnit ici");
			}

			i++;
		}
			
	}
	
	public void draw(Graphics2D gp) {
		for(ViewUnit viewUnit: this.viewUnits)
			viewUnit.draw(gp);
	}
}
