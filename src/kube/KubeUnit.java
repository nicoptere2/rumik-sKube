package kube;

import java.awt.Color;
import java.io.Serializable;

public class KubeUnit implements Serializable, Comparable<KubeUnit>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4364920031018059185L;
	
	private Color color;
	
	private int categ = 0;
	
	public KubeUnit() {
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public float getHue(){
		float[] hsb = new float[3];
		Color.RGBtoHSB(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), hsb);
		return hsb[0];
	}
	
	public float dist(KubeUnit ku) {
		if((this.getHue() - ku.getHue()) > 0)
			return this.getHue() - ku.getHue();
		return ku.getHue() - this.getHue();
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

	@Override
	public int compareTo(KubeUnit o) {
		KubeUnit kubeUnit = (KubeUnit) o;
		
		if(this.getHue() < kubeUnit.getHue())
			return -1;
		else if(this.getHue() > kubeUnit.getHue())
			return 1;
		
		return 0;
	}

	public int setCateg(int categ) {
		if(this.categ != 0){
			this.categ = categ;
			return this.categ;
		}
		this.categ = categ;
		return 0;
	}

	public int getCateg() {
		return this.categ;
	}
}
