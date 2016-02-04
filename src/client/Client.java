package client;

import java.awt.Dimension;

import javax.swing.JFrame;

import client.view.ViewKube;
import kube.Kube;

public class Client extends JFrame{
	
	public Client() {
		super("Rumik's kube");
		this.setPreferredSize(new Dimension(1000, 700)) ;
		
		Kube kube = null;
		try {
			kube = Kube.restore();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Ca a marché");
		System.out.println(kube);
		
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        kube.lighten((float)0.40);
        kube.sature((float)1);
        
         Categorize categorize = new Categorize(kube);
         categorize.execute();

        
        ViewKube viewKube= new ViewKube(kube);
        this.add(viewKube);

        pack();
        setVisible(true);    
	}
	

	public static void main(String[] args) {
		new Client();
	}

}
