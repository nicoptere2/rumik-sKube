package modelCube;

import java.awt.Color;
import java.io.Serializable;

public class Case implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4797176804641749206L;
	public Color colour;
	enum Groups{ NotFoundYet, G1, G2, G3, G4, G5, G6};
	
	
	public Groups groupColour;
	public Case(Color c){
		colour = c;
		groupColour = Groups.NotFoundYet;
	}
	
	
	public void lighten(float light) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(this.colour.getRed(), this.colour.getGreen(), this.colour.getBlue(), hsb);
		hsb[2] = light;
		this.colour = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	public void sature(float saturation) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(this.colour.getRed(), this.colour.getGreen(), this.colour.getBlue(), hsb);
		hsb[2] = saturation;
		this.colour = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	@Override
	public String toString() {
		return colour.toString();
	}
}
