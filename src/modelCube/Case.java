package modelCube;

import java.awt.Color;

public class Case {

	public Color colour;
	public enum Groups{ NotFoundYet , G1 , G2 , G3 , G4 , G5 , G6 };
	public static final Groups[] groups = { Groups.G1, Groups.G2, Groups.G3, Groups.G4, Groups.G5, Groups.G6};
	public enum Position {Center, Middle, Corner};
	public Groups groupColour;
	public Position position;
	
	public Case(Color c, Position p){
		colour = c;
		position = p;
		groupColour = Groups.NotFoundYet;
	}
	
	public float absoluteHue(){
		return Color.RGBtoHSB(colour.getRed(), colour.getGreen(), colour.getBlue(), null)[0];
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
