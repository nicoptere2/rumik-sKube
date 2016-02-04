package modelCube;

public class Face {

	public Case[][] cases;
	Face[] neighboors;
	
	
	public Face(){
		cases = new Case[3][3];
		neighboors = new Face[4];
	}
	
	public Case getCenter(){
		return cases[1][1];
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("=================\n");
		for(int i = 0 ; i < 3 ; i++){
			for(int j = 0 ; j < 3 ; j++){
				s.append(cases[i][j].colour + " ");
			}
			s.append("\n");
			
		}
		return s.toString();
	}

	/**
	 * 
	 * @param i de 0 � 3 inclus
	 * @return le coin num�ro i clockwise
	 */
	public Case getCorner(int i){
		switch(i){
		case 0:
			return cases[0][0];
		case 1:
			return cases[2][0];
		case 2:
			return cases[0][2];
		case 3:
			return cases[2][2];
		default:
			System.out.println("valeur de 1 � 4 bordel");
			return getCenter();

		}
	}
	
	
	@Override
	protected Face clone() throws CloneNotSupportedException {
		Face f = new Face();
		for(int i = 0 ; i < 3 ; i++){
			f.setColumn(i, getColumn(i));
		}
		return f;
	}
	/**
	 * 
	 * @param i de 0 � 3 inclus
	 * @return la case du milieu num�ro i clockwise
	 */
	public Case getMiddleCase(int i){
		switch(i){
		case 0:
			return cases[0][1];
		case 1:
			return cases[1][0];
		case 2:
			return cases[1][2];
		case 3:
			return cases[2][1];
		default:
			System.out.println("valeur de 1 � 4 bordel");
			return getCenter();

		}
	}


	
	/**
	 * 
	 * @param i le num�ro du coin dans le sens de lecture !!!!!
	 */
	public void setMiddle(int i, Case c ){
		switch(i){
		case 0:
			cases[2][1] = c;
			break;
		case 1:
			cases[1][2] = c;
			break;
		case 2:
			cases[0][1] = c;
			break;
		case 3:
			cases[1][0] = c;
			break;
		}
	}
	
	/**
	 * 
	 * @param i le num�ro du coin dans le sens de lecture !!!!!
	 */
	public void setCorner(int i, Case c ){
		switch(i){
		case 0:
			cases[2][2] = c;
			break;
		case 1:
			cases[0][2] = c;
			break;
		case 2:
			cases[0][0] = c;
			break;
		case 3:
			cases[2][0] = c;
			break;
		}
	}
	
	/**
	 * 
	 * @param i de 0 � 2
	 * @return la ligne num�ro i
	 */
	public Case[] getLine(int i){
		Case[] ret = new Case[3];
		for(int j = 0 ; j < 3 ; j++){
			ret[j] = cases[i][j];
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @param i de 0 � 2
	 * @return la colonne num�ro i
	 */
	public Case[] getColumn(int i){
		Case[] ret = new Case[3];
		for(int j = 0 ; j < 3 ; j++){
			ret[j] = cases[j][i];
		}
		return ret;
	}
	
	
	public void setColumn(int i, Case[] col){
		for(int j = 0 ; j < 3 ; j++){
			cases[j][i] = col[j];
		}
	}
	
	
	public void rotation(boolean clockwise){
		
		Case[] 	line1 = getColumn(0), 
				line2 = getColumn(1), 
				line3 = getColumn(2);//bon ce serait plus simple avec un tableau de lignes, mais osef
		if(clockwise){
			setColumn(0, line3);
			setColumn(1, line2);
			setColumn(2, line1);
			
			Case[] next = neighboors[neighboors.length-1].getColumn(2),
					tmp;
			for(int i = 0 ; i < neighboors.length ; i++){
				tmp = neighboors[i].getColumn(2);
				neighboors[i].setColumn(2, next);
				next = tmp;
			}
		}
		else{
			reverseLine(line1);
			reverseLine(line2);
			reverseLine(line3);
			setColumn(0, line1);
			setColumn(1, line2);
			setColumn(2, line3);	

			Case[] next = neighboors[0].getColumn(2),
					tmp;
			for(int i = neighboors.length-1 ; i >= 0; i--){
				tmp = neighboors[i].getColumn(2);
				neighboors[i].setColumn(2, next);
				next = tmp;
			}
		}
	}
	
	private void reverseLine(Case[] l){
		Case tmp = l[0];
		l[0] = l[2];
		l[2] = tmp;		
	}
	
}
