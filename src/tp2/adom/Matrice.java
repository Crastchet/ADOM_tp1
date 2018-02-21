package tp2.adom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Matrice {

	private double[][] matrice;
	private final int NBVILLES;
	private Ville[] villes;

	public Matrice(Ville[] villes) {
		this.villes = villes;
		this.NBVILLES = villes.length;
		this.matrice = new double[NBVILLES + 1][NBVILLES + 1];
		for (int i = 1; i < NBVILLES+1; i++) {
			for (int j = i; j < NBVILLES+1; j++) {
				this.matrice[i][j] = villes[i-1].distance(villes[j-1]);
			}
		}
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (int i = 1; i < matrice.length; i++) {
			for (int j = 1; j < matrice.length; j++)
				toReturn += matrice[i][j] + " ,";
			toReturn += "\n";
		}
		return toReturn;
	}

	public int calculerCout(Ville[] chemin) {
		int res = 0;
		// on pourrait ajouter des if qui check si la taille du chemin est bien = à
		// NBVILLES, si ce sont les bonnes villes etc
		for (int i = 0; i < NBVILLES; i++)
			res += distance(chemin[i], chemin[(i + 1) % NBVILLES]);

		return res;
	}

	public Ville[] creerCheminAleatoire() {
		ArrayList<Ville> list = new ArrayList<>();
		for (Ville v : this.villes)
			list.add(v);

		Ville res[] = new Ville[NBVILLES];
		int cpt = 0;
		while (!list.isEmpty()) {
			int random = new Random().nextInt(list.size());
			res[cpt++] = list.get(random);
			list.remove(random);
		}
		return res;
	}
	
	public Ville[] fonction_solutionAleatoireGlobale() {
		System.out.println("GENERATION DE SOLUTIONS ALEATOIRES :");
		Ville[] bestChemin = this.creerCheminAleatoire();
		int bestCost = this.calculerCout(bestChemin);
		System.out.println("\nChemin : " + TspParser.cheminToString(bestChemin) + "\nCoût : " + bestCost);
		
		Ville[] chemin;
		int cost;
		for(int i=1; i<=100; i++) {
			chemin = this.creerCheminAleatoire();
			cost = this.calculerCout(chemin);
			System.out.println("\nChemin : " + TspParser.cheminToString(chemin) + "\nCoût : " + this.calculerCout(chemin));
			if(cost < bestCost) {
				bestChemin = chemin;
				bestCost = cost;
			}
		}
		System.out.println("MEILLEURE SOLUTION TROUVÉE :\nChemin : " + TspParser.cheminToString(bestChemin) + "\nCoût : " + bestCost);
		return bestChemin;
	}

	public Ville[] fonction_heuristiqueGlobale() {
		System.out.println("HEURISTIQUE CONSTRUCTIVE VOISIN LE PLUS PROCHE :");
		
		Ville[] bestChemin = this.fonction_heuristique(this.villes[0]);
		int bestCost = this.calculerCout(bestChemin);
		System.out.println("\nVille départ : " + this.villes[0] + "\nChemin : " + TspParser.cheminToString(bestChemin) + "\nCoût : " + bestCost);
		
		Ville[] chemin;
		int cost;
		for(int i=1; i<this.NBVILLES; i++) {
			chemin = this.fonction_heuristique(this.villes[i]);
			cost = this.calculerCout(chemin);
			System.out.println("\nVille départ : " + this.villes[i] + "\nChemin : " + TspParser.cheminToString(chemin) + "\nCoût : " + cost);
			if(cost < bestCost) {
				bestChemin = chemin;
				bestCost = cost;
			}
		}
		System.out.println("MEILLEURE SOLUTION TROUVÉE :\nVille départ : " + bestChemin[0] + "\nChemin : " + TspParser.cheminToString(bestChemin) + "\nCoût : " + bestCost);
		return bestChemin;
	}
	
	public Ville[] fonction_heuristique(Ville debut) {
		Ville[] chemin = new Ville[NBVILLES];
		List<Ville> restantes = new ArrayList<Ville>();
		for(Ville v : this.villes)
			restantes.add(v);
		int cpt = 0;
		Ville current = debut;
		chemin[cpt++] = current;
		restantes.remove(debut);
		
		while (!restantes.isEmpty()) {
			Ville tmp = findMin(current, restantes);
			chemin[cpt++] = tmp;
			current = tmp;
			restantes.remove(tmp);
		}
		return chemin;
	}

	public double distance(Ville v1, Ville v2) {
		if (v1.pos > v2.pos)
			return matrice[v2.pos][v1.pos];
		else
			return matrice[v1.pos][v2.pos];
	}

	public Ville findMin(Ville ville, List<Ville> restantes) {
		double min = Double.MAX_VALUE;
		Ville sommetpetit = null;

		double tmp;
		for (Ville v : restantes) {
			tmp = distance(ville, v);
			if (tmp < min && tmp != 0) {
				min = tmp;
				sommetpetit = v;
			}
		}
		return sommetpetit;
	}
}
