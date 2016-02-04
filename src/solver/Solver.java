package solver;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import modelCube.Cube;
import modelCube.Face;

public class Solver {

	
	static final String[] permutations = { 	
			"Front" , "Back" , "Right" , "Left" , "Up"  , "Down",
			"FrontP" , "BackP" , "RightP" , "LeftP" , "UpP"  , "DownP" ,
			"Front2" , "Back2" , "Right2" , "Left2" , "Up2"  , "Down2" };
	
	static final int nbPermutations = 18;
	
	
	public static void solve(Cube cube, System robot){
		
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
	
	
	private static void apply(List<String> res, System robot){
		//TODO
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
			f = cube.front;
		}
		else if(p.startsWith("Back")){
			f = cube.back;
		}
		else if(p.startsWith("Up")){
			f = cube.up;
		}
		else if(p.startsWith("Down")){
			f = cube.down;
		}
		else if(p.startsWith("Left")){
			f = cube.left;
		}
		else if(p.startsWith("Right")){
			f = cube.right;
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
