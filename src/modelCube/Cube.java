package modelCube;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Cube implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3523000172171699926L;



	public static final int Front = 0,
							Down = 1,
							Back = 2,
							Up = 3,
							Left = 4,
							Right = 5;
	
	

	public static final int nbFaces = 6;
	public Face[] faces;
	
	
	public Cube(){		
		faces = new Face[6];
	}

	
	public void setFace(int i, Face f){
		faces[i] = f;
	}

	@Override
	public Cube clone() {
		Cube r = new Cube();
		for(int i = 0 ; i < nbFaces ; i++){
			r.faces[i] = faces[i].clone();
		}
		return r;
	}
	
	public boolean isSolved(){
		for(int i = 0 ;  i < 6 ; i++){
			if( ! faces[i].isComplete()){
				return false;
			}
		}
		return true;
	}
	
	

	public void lighten(float light) {
		for(int i = 0 ; i < nbFaces ; i++)
			faces[i].lighten(light);
	}
	
	public void sature(float saturation) {
		for(int i = 0 ; i < nbFaces ; i++)
			faces[i].sature(saturation);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(int i = 0 ; i < nbFaces ; i++){
			s.append(faces[i] + "\n");
		}
		return s.toString();
	}	
	
	
	private static String serPath = "cube.data";
	
	
	public void serialize() throws IOException {
		FileOutputStream f_out = new FileOutputStream(serPath);
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
		obj_out.writeObject(this);
		obj_out.close();
	}
	
	public static Cube restore() throws Exception {
		FileInputStream f_in = new FileInputStream(serPath);
		ObjectInputStream obj_in = new ObjectInputStream (f_in);
		Object obj = obj_in.readObject();
		obj_in.close();
		if (obj instanceof Cube)
			return (Cube) obj;
		else
			throw new Exception("Objet inconnu");
	}
	
	
}
