package client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import kube.KubeUnit;

public class ViewUnit extends BufferedImage{

	protected KubeUnit kubeUnit;
	
    protected Graphics2D carre ;
    public static final int WIDTH = 50 ;
    protected Color couleur ;
    
    protected int x;
    protected int y;

    public ViewUnit(KubeUnit kubeUnit, int x, int y) {
        super(150, 150, BufferedImage.TYPE_INT_ARGB);
        
        this.kubeUnit = kubeUnit;
        
        this.x = x;
        this.y = y;
        
        this.carre = createGraphics();
        this.couleur = kubeUnit.getColor() ;
        
        dessinerCase() ;
    }
    
   
    
    /**
     * Dessin de l'élément graphique (l'élément graphique est redessiné, car sa couleur et son contenu peuvent 
     * changer au cours de l'animation
     * @param t temps courant
     */
    private void dessinerCase() {
        carre.setPaint(this.kubeUnit.getColor()) ;
        carre.fillRect(0, 0, WIDTH, WIDTH) ;
        
        carre.setColor(couleur) ;
        carre.drawRect(0, 0, WIDTH, WIDTH); 
    }

    
    /**
     * Dessin de l'élément graphique et positionnement dans la fenêtre graphique
     * @param g fenêtre graphique dans laquelle on dessine
     * @param init 
     */
    public void draw(Graphics gp) {   
    	
        dessinerCase() ;


    	gp.drawImage(this, this.x, this.y, null);

    } 

}
