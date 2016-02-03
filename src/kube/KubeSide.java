package kube;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class KubeSide implements Iterable<KubeUnit>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3247964709264781773L;
	
	private ArrayList<KubeUnit> kubeUnits;
	public static final int NBUNITS = 9;
	
	public KubeSide() {
		this.kubeUnits = new ArrayList<KubeUnit>();
		for(int i=0; i<NBUNITS; i++)
			this.kubeUnits.add(new KubeUnit());
	}
	
	public void rotateSide() {
		KubeUnit second = kubeUnits.get(1);
		KubeUnit third = kubeUnits.get(2);
		
		for(int i=1; i<Kube.NBSIDE-2; i++)
			kubeUnits.set(i, kubeUnits.get(i+2));
		
		kubeUnits.set(Kube.NBSIDE-2, second);
		kubeUnits.set(Kube.NBSIDE-1, third);
	}
	
	public KubeUnit getKubeUnit(int index) {
		return this.kubeUnits.get(index);
	}
	
	public void lighten(float light) {
		for(KubeUnit kubeUnit: this.kubeUnits)
			kubeUnit.lighten(light);
	}
	
	public void sature(float saturation) {
		for(KubeUnit kubeUnit: this.kubeUnits)
			kubeUnit.lighten(saturation);
	}

	@Override
	public Iterator<KubeUnit> iterator() {
		return this.kubeUnits.iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(KubeUnit kubeUnit: this.kubeUnits) {
			s.append("\t").append(kubeUnit).append("\n");
		}
		return s.toString();
	}
}
