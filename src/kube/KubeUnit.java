package kube;

import java.awt.Color;
import java.io.Serializable;

public class KubeUnit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4364920031018059185L;
	
	private Color color;
	
	public KubeUnit() {
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void lighten(float light) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), hsb);
		hsb[2] = light;
		this.color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	public void sature(float saturation) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), hsb);
		hsb[2] = saturation;
		this.color = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	@Override
	public String toString() {
		return color.toString();
	}
}
