package client.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import kube.Kube;
import kube.KubeSide;

@SuppressWarnings("serial")
public class ViewKube extends JPanel{

	protected Kube kube;
	
	protected ArrayList<ViewSide> viewSides;
	
	public ViewKube(Kube kube) {
		super();
		this.setSize(1000, 1000);
		this.kube = kube;
		
		viewSides = new ArrayList<ViewSide>();
		
		int x=0, y=500;
		int i=0;
		for(KubeSide kubeSide: this.kube){
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
			viewSides.add(new ViewSide(kubeSide, y, x));
			i++;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D gp = (Graphics2D) g;
		for(ViewSide viewSide: viewSides)
			viewSide.draw(gp);
	}

}
