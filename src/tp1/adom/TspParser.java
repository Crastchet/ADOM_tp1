package tp1.adom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;


public class TspParser {
	
	private File file;
	
	
	public TspParser(File f) {
		this.file = f;
	}
	
	
	public Ville[] genererVilles() {
		BufferedReader br = null;
		FileReader fr = null;
		LinkedList<Ville> villes = new LinkedList<>();
		int nbVilles = 0;
		try {
			fr = new FileReader(this.file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				if (!line.equals("EOF")) {
					String tmp[] = line.split(" ");
					villes.add( new Ville(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2])) );
					nbVilles++;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Ville[] tab = new Ville[nbVilles];
		villes.toArray(tab);
		return tab;
	}
	
	public static String cheminToString(Ville[] chemin) {
		String p = chemin[0] + "";
		for(int i=1; i<chemin.length; i++)
			p += "-" + chemin[i];
		return p;
	}
	
	public static void changeSystemOutToFile(String file) {
		try {
			System.setOut(new PrintStream(new File(file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changeSystemOutToConsole(PrintStream output) {
		System.setOut(output);
	}

}
