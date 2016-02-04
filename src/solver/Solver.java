package solver;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import modelCube.Case.Groups;
import modelCube.Case;
import modelCube.Cube;
import modelCube.Face;
import rumikskube.Systeme;

public class Solver {

	
	static final String[] permutations = { 	
			"Front" , "Back" , "Right" , "Left" , "Up"  , "Down",
			"FrontP" , "BackP" , "RightP" , "LeftP" , "UpP"  , "DownP" ,
			"Front2" , "Back2" , "Right2" , "Left2" , "Up2"  , "Down2" };
	
	static final int nbPermutations = 18;
	
	
	
	
	public static void solve(Cube cube, Systeme robot){
		
		sort(cube);
		
		Pair<Cube, List<String> >[] memory = new Pair[nbPermutations];
		for(int i = 0 ; i < nbPermutations ; i++){
			memory[i] = new Pair(cube, new ArrayList<>());
		}
		List<String> res;
		
		while( (res = iterate(memory)) == null);
		
		System.out.println("wesh on a réussi !");
		
		for(String s : res){
			System.out.println(s);
		}
		
		apply(res, robot);
		
	}
	
	
	private static void apply(List<String> res, Systeme robot){
		//TODO
	}
	
	
	public void sort(Cube cube){
		Case tmp;
		for(Groups g : Case.groups){
			tmp = nextNotFoundCenter(cube);
			tmp.groupColour = g;
			for(int i = 0 ; i < 4 ; i++ ){
				tmp = nextNotFoundCorner(cube);
				tmp.groupColour = g;
			}
			for(int i = 0 ; i < 4 ; i++ ){
				tmp = nextNotFoundMiddle(cube);
				tmp.groupColour = g;
			}
		}
		System.out.println("finito");
	}

	private Case nextNotFoundCenter(Cube cube){
		Case tmp;
		for(Face f : cube.faces){
			tmp = f.getCenter();
			if(tmp.groupColour == Groups.NotFoundYet){
				return tmp;
			}
		}
		return null;
	}
	
	private Case nextNotFoundCorner(Cube cube){
		Case tmp;
		for(Face f : cube.faces){
			for(int i = 0 ; i < 4 ; i++){
				tmp = f.getCorner(i);
				if(tmp.groupColour == Groups.NotFoundYet){
					return tmp;
				}
			}
		}
		return null;
	}
	
	private Case nextNotFoundMiddle(Cube cube){
		Case tmp;
		for(Face f : cube.faces){
			for(int i = 0 ; i < 4 ; i++){
				tmp = f.getMiddleCase(i);
				if(tmp.groupColour == Groups.NotFoundYet){
					return tmp;
				}
			}
		}
		return null;
	}
	
	
	
	
	static Cube tmp;
	static List<String> tmpL;
	private static List<String> iterate( Pair<Cube, List<String>>[] memory){

		for(int i = 0 ; i < memory.length ; i++){
			for(String p : permutations){
				tmp = permutation(memory[i].getKey(), p);
				tmpL = memory[i].getValue();
				tmpL.add(p);
				memory[i] = new Pair(tmp, tmpL);
				if(tmp.isSolved()){
					return tmpL;
				}
			}
		}
		return null;
	}
	
	
	private static Cube permutation(Cube c, String p){
		Cube cube = c.clone();
		Face f = null;
		if(p.startsWith("Front")){
			f = cube.faces[Cube.Front];
		}
		else if(p.startsWith("Back")){
			f = cube.faces[Cube.Back];
		}
		else if(p.startsWith("Up")){
			f = cube.faces[Cube.Up];
		}
		else if(p.startsWith("Down")){
			f = cube.faces[Cube.Down];
		}
		else if(p.startsWith("Left")){
			f = cube.faces[Cube.Left];
		}
		else if(p.startsWith("Right")){
			f = cube.faces[Cube.Right];
		}
		else{
			System.out.println("Houston on a un problème: la permutation " + p + " n'est pas gérée");
		}
		
		
		if(f != null){
			if(p.endsWith("P")){
				f.rotation(false);
			}
			else if(p.endsWith("2")){
				f.rotation(true);
				f.rotation(true);
			}
			else{
				f.rotation(true);
			}
		}
		return cube;
		
		
	}
	

	
	
}
