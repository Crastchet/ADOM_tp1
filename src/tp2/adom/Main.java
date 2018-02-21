package tp2.adom;
import java.io.File;
import java.io.PrintStream;

public class Main {
	public static final PrintStream SYSTEMOUT = System.out;

	public static void main(String[] args) {
		TspParser parser = new TspParser(new File("kroA100.tsp"));
		Ville[] villes = parser.genererVilles();
		Matrice matrice = new Matrice(villes);
		
		/**
		 * Génération de 100 chemins aléatoires, récupération du meilleur
		 */
		TspParser.changeSystemOutToFile("chemins_aleatoires.txt");
		Ville[] cheminAleatoire = matrice.fonction_solutionAleatoireGlobale();
		TspParser.changeSystemOutToConsole(SYSTEMOUT);
		
		/**
		 * Calcul du meilleur chemin possible avec la fonction heuristique constructive
		 */
		TspParser.changeSystemOutToFile("heuristique_globale.txt");
		Ville[] cheminHeuristique = matrice.fonction_heuristiqueGlobale();
		TspParser.changeSystemOutToConsole(SYSTEMOUT);
	}
	
}
