package solver;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import kube.Kube;
import modelCube.Cube;

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

	static Kube tmp;
	static List<String> tmpL;
	private static List<String> iterate( Pair<Cube, List<String>>[] memory){

		for(int i = 0 ; i < memory.length ; i++){
			for(String p : permutations){
				tmp = permutation(memory[i].getKey(), p);
				tmpL = memory[i].getValue();
				tmpL.add(p);
				memory[i] = new Pair(tmp, tmpL);
				if(isSolved(tmp)){
					return tmpL;
				}
			}
		}
		return null;
	}
	
	
	private static Cube permutation(Cube c, String p){
		Cube cube = c.clone();
		
		//on doit mettre la face à tourne ? je ne sais pas où :(
		if(p.startsWith("Front")){
			
		}
		else if(p.startsWith("Back")){
			
		}
		else if(p.startsWith("Up")){
			
		}
		else if(p.startsWith("Down")){
			
		}
		else if(p.startsWith("Left")){
			
		}
		else if(p.startsWith("Right")){
			
		}
		else{
			System.out.println("Houston on a un problème: la permutation " + p + " n'est pas gérée");
		}
	
		if(p.endsWith("P")){
			//rotation à 90 dans le sens inverse des aiguilles d'une montre
			//cube.rotateSide();
		}
		else if(p.endsWith("2")){
			//rotation à 180
			
		}
		else{
			//rotation à 90
			
		}
		
		
		
	}
	
	private static boolean isSolved(Kube c){//TODO: à déplacer dans "Kube" (putain mais quel nom débile bordel), mais pour l'instant il reste là parce que je sens poindre les conflits git :)
		return false;//TODOTODOTDODTOTODTODTODOTDOTDOTDTD
	}
	
	
}
