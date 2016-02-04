package kube;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Kube implements Iterable<KubeSide>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4490716437994679006L;
	
	private ArrayList<KubeSide> kube;
	public static final int NBSIDE = 6;
	
	public Kube() {
		this.kube = new ArrayList<KubeSide>();
		for(int i=0; i<NBSIDE; i++)
			kube.add(new KubeSide());
	}
	
	public void rotateSide(KubeSide kubeSide) {
		kubeSide.rotateSide();
	}
	
	public void lighten(float light) {
		for(KubeSide kubeSide: this.kube)
			kubeSide.lighten(light);
	}
	
	public void sature(float saturation) {
		for(KubeSide kubeSide: this.kube)
			kubeSide.lighten(saturation);
	}
	
	public KubeSide getSide(int i) {
		return this.kube.get(i);
	}

	@Override
	public Iterator<KubeSide> iterator() {
		return this.kube.iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(KubeSide kubeSide: kube) {
			s.append(kubeSide);
			s.append("\n");
		}
		return s.toString();
	}	
	
	public void serialize() throws IOException {
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new FileOutputStream("myobject.data");

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

		// Write object out to disk
		obj_out.writeObject(this);
	}
	
	public static Kube restore() throws Exception {
		// Read from disk using FileInputStream
		FileInputStream f_in = new FileInputStream("myobject.data");

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = new ObjectInputStream (f_in);

		// Read an object
		Object obj = obj_in.readObject();

		obj_in.close();
		
		if (obj instanceof Kube)
			return (Kube) obj;
		else
			throw new Exception("Objet inconnu");
	}

	
	
}
