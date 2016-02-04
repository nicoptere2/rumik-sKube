package modelCube;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Cube implements Serializable {
	
	public static final int Front = 0,
							Right = 1,
							Back = 2,
							Left = 3,
							Down = 4,
							Up = 5;
	
	
	public Face front, back, left, right, up, down;

	public Cube(){		
	}

	public void setFace(int i, Face f){
		switch(i){
		case Front:
			front = f;
			break;
		case Back:
			back = f;
			break;
		case Left:
			left = f;
			break;
		case Right:
			right = f;
			break;
		case Up:
			up = f;
			break;
		case Down:
			down = f;
			break;
		default:
			System.out.println("erreur");
		}
	}

	@Override
	protected Cube clone() throws CloneNotSupportedException {
		Cube r = new Cube();
		r.front = front.clone();
		r.back = back.clone();
		r.left = left.clone();
		r.right = left.clone();
		r.up = up.clone();
		r.down = down.clone();
		return r;
	}
	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(front);
		s.append("\n");
		s.append(back);
		s.append("\n");
		s.append(left);
		s.append("\n");
		s.append(right);
		s.append("\n");
		s.append(up);
		s.append("\n");
		s.append(down);
		s.append("\n");
		return s.toString();
	}	
	
	
	private static String serPath = "cube.data";
	
	
	public void serialize() throws IOException {
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new FileOutputStream(serPath);

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

		// Write object out to disk
		obj_out.writeObject(this);
	}
	
	public static Cube restore() throws Exception {
		// Read from disk using FileInputStream
		FileInputStream f_in = new FileInputStream(serPath);

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = new ObjectInputStream (f_in);

		// Read an object
		Object obj = obj_in.readObject();

		obj_in.close();
		
		if (obj instanceof Cube)
			return (Cube) obj;
		else
			throw new Exception("Objet inconnu");
	}
	
	
}
