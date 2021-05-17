package com.hemebiotech.analytics;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	
	/**
	 * Trend analysis program, read a symptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. This is the start of the
	 * OOP version of the code.
	 * 
	 * @param args not used
	 * @throws FileNotFoundException the symptom file does not exist or cannot be
	 *                               found
	 * @throws EOFException          end of file reached
	 * @throws IOException           other i/o file operation errors
	 */
	public static void main(String args[]) throws Exception {

		// reading symptom's file and return symptom list with duplicate elements
		ReadSymptomDataFromFile objSymptomFile = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptomList = objSymptomFile.getSymptoms();

		// create file for results
		FileWriter writer = new FileWriter("results.out");

		// get symptom/number of occurrences pairs from symptom list
		Map<String, Integer> sMap = getKeyValueList(symptomList);

		try {

			// record count result
			for (String symptom : sMap.keySet()) {
				writer.write(symptom + " " + sMap.get(symptom) + "\n");
			}


		} catch (FileNotFoundException e) {
			System.out.println("Attention le fichier des symptômes n'existe pas ou est introuvable!");
		} catch (EOFException e) {
			System.out.println("Fin de fichier atteinte");
		} catch (IOException e) {
			System.out.println("Problème de lecture/écriture de fichier" + e.getMessage());
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * Read the symptom list, that may contain many duplications, and generate a
	 * ordered key/value list without duplicate element.
	 * 
	 * @param symptomList a full list of symptom with duplicate element
	 * @return a key/value pairs list, symptom/number off occurrences
	 */
	private static Map<String, Integer> getKeyValueList(List<String> symptomList) {
		Map<String, Integer> tmap = new TreeMap<>();

		// treats all symptoms, removing of duplicates and counting of occurrences
		for (String symptom : symptomList) {
			Integer valeur = tmap.get(symptom);
			if (valeur == null) // symptom absent de tmap
				tmap.put(symptom, 1);
			else {
				valeur++;
				tmap.replace(symptom, valeur);
			}
		}
		return tmap;
	}

}
