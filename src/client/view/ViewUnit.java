package client.view;

import java.awt.Color;
import java.awt.FontMetrics;
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
    
    private void dessinerChaineAuCentre(int w, int h) {
    	String  s = Integer.toString(kubeUnit.getCateg());
    	
        // Find the size of string s in the font of the Graphics context 
        FontMetrics fm = this.carre.getFontMetrics();
        int xC = (ViewUnit.WIDTH - fm.stringWidth(s)) / 2;
        int yC = (fm.getAscent() + (ViewUnit.WIDTH - (fm.getAscent() + fm.getDescent())) / 2);

        // Center text horizontally and vertically within provided rectangular bounds
        this.carre.drawString(s, xC + w, yC + h);
    } 
    
   
    
    /**
     * Dessin de l'élément graphique (l'élément graphique est redessiné, car sa couleur et son contenu peuvent 
     * changer au cours de l'animation
     * @param t temps courant
     */
    private void dessinerCase() {
        carre.setPaint(this.kubeUnit.getColor()) ;
        carre.setPaint(Color.BLACK) ;
   
        carre.fillRect(0, 0, WIDTH, WIDTH) ;
        
        carre.setColor(couleur) ;
        carre.drawRect(0, 0, WIDTH, WIDTH); 
        
        this.dessinerChaineAuCentre(0, 0);
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
