package client;

import java.util.ArrayList;
import java.util.List;

import kube.Kube;
import kube.KubeSide;
import kube.KubeUnit;

public class Categorize {
	
	protected Kube kube;
	
	protected List<KubeUnit>[] categ;

	
	@SuppressWarnings("unchecked")
	public Categorize(Kube kube) {
		this.kube = kube;
		categ = new ArrayList[6];
		for(int iCateg=0; iCateg<6; iCateg++) {
			categ[iCateg] = new ArrayList<KubeUnit>();
			categ[iCateg].add(kube.getSide(iCateg).getKubeUnit(0));
		}		
	}
	
	public void execute() {
		for(int iSide=0; iSide<6; iSide++) {
			for(int iKubeUnit=1; iKubeUnit<9;iKubeUnit++) {
				if(iKubeUnit%2==1) {
					for(int iCateg=0; iCateg<6; iCateg++) {System.out.println("fdsn,ksd");
						categ[iCateg].add(kube.getSide(iSide).getKubeUnit(iKubeUnit));
					}
				}
			}
		}
 
		for(int iCateg=0; iCateg<6; iCateg++)
			this.sort(categ[iCateg], 1);

		for(int m=0; m<6;m++)
			this.clean(categ[m], 5);

		for(int j=0; j<6; j++) {
			for(int i=1; i<9;i++) {
				if((i%2)==0) {
					for(int m=0; m<6; m++) {
						categ[m].add(kube.getSide(j).getKubeUnit(i));
					}
				}
			}
		}
		
		for(int m=0; m<6; m++)
			this.sort(categ[m], 5);

		
		setCateg();
		
	}
	
	private void clean(List<KubeUnit> list, int i) {
		while(true){
			try {
				list.remove(5);
			} catch (Exception e) {
				break;
			}
		}
			
	}

	private void setCateg() {
		System.out.println(categ[1]);
		int nbCateg;
		int iRedond;
		float value1;
		float value2;
		
		boolean redond = false;
		
		for(int i=0; i<9 && !redond; i++){
			for(int m=0; m<6 && !redond; m++){
				if((nbCateg = categ[m].get(i).setCateg(m+1)) != 0){
					nbCateg--;
					iRedond = categ[nbCateg].indexOf(categ[m].get(i).setCateg(m+1));
					if(iRedond <=0) continue;
					value1 = categ[m].get(i).dist(categ[m].get(0));
					System.out.println("nbcateg" + iRedond);
					value2 = categ[nbCateg].get(iRedond).dist(categ[nbCateg].get(0));
					
					if(value1 < value2)
						categ[nbCateg].remove(iRedond);
					else
						categ[m].remove(i);
					setCateg();
					redond = true;
				}
			}
		}
		if(!redond)
			fuckof();
		
	}
	
	private void fuckof() {
		ArrayList<KubeUnit> lonely = new ArrayList<KubeUnit>();
		for(KubeSide ks: kube) {
			for(KubeUnit ku: ks) {
				for(int iCateg=0; iCateg<6; iCateg++) {
					if(!categ[iCateg].contains(ku))
						lonely.add(ku);
				}
			}
		}
		System.out.println("coucou" +lonely);
		
		int iMin=0;
		float value1;
		float value2;
		for(KubeUnit ku: lonely) {
			for(int iCateg=0; iCateg<6; iCateg++){
				value1= categ[iCateg].get(0).dist(ku);
				value2= categ[iMin].get(0).dist(ku);
				if(value1 < value2)
					iMin = iCateg;
			}
			ku.setCateg(iMin+1);
		}
		
	}


	private void sort(List<KubeUnit> list, int lo) {
		KubeUnit ref = list.get(0);
		boolean sorted = false;
		int iCurrent;
		float value1;
		float value2;
		KubeUnit tmp;
		int size= list.size() -1;
		
		while(!sorted) {
			sorted = true;
			for(iCurrent=lo; iCurrent < size; iCurrent++) {
				value1 = ref.dist(list.get(iCurrent));
				value2 = ref.dist(list.get(iCurrent+1));
				if(value1 > value2) {
					tmp = list.get(iCurrent);
					list.set(iCurrent, list.get(iCurrent+1));
					list.set(iCurrent+1, tmp);
					sorted = false;
				}
				
				
			}
			size--;
		}
		
			for(int iKubeUnit=0; iKubeUnit<9; iKubeUnit++)
				System.out.print(String.valueOf(list.get(iKubeUnit).dist(list.get(0))) + " | ");
			System.out.println();
		
/*
		int value1 = this.getDist(ref, list.get(lo));
		int value2 = this.getDist(ref, list.get(hi));
		int p;
		if(value1 < value2) {
			p = part(ref, list, lo, hi);
			this.sort(ref, list, lo, p-1);
			this.sort(ref, list, p+1, hi);
		}
		*/
	}

	private int part(KubeUnit ref, List<KubeUnit> list, int lo, int hi) {
		KubeUnit pivot = list.get(lo);
		int i = lo;
		float value1;
		float value2;
		
		KubeUnit tmp;
		
		for(int j=lo; j<hi-1; j++) {
			value1 = ref.dist(list.get(j));
			value2 = ref.dist(pivot);
			if(value1 <= value2) {
				tmp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, tmp);
				i++;
			}
				
		}
		tmp = list.get(i);
		list.set(i, list.get(hi));
		list.set(hi, tmp);
		
		return 0;
	}
}
