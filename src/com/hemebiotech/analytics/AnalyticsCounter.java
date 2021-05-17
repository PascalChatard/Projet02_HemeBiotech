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
	 * symptom's type and write the result in a text file. POO code version
	 * continued .
	 * 
	 * @param args not used
	 * @throws FileNotFoundException the symptom file does not exist or cannot be
	 *                               found
	 * @throws EOFException          end of file reached
	 * @throws IOException           other i/o file operation errors
	 */
	public static void main(String args[]) {

		try {
			// reading symptom's file and return symptom list with duplicate elements
			ReadSymptomDataFromFile objSymptomFile = new ReadSymptomDataFromFile("symptoms.txt");
			List<String> symptomList = objSymptomFile.getSymptoms();

			// get symptom/number of occurrences pairs from symptom list
			Map<String, Integer> symptomOccurrenceMap = getKeyValueList(symptomList);

			// create file for results
			writeResultsDataToFile(symptomOccurrenceMap);

		} catch (FileNotFoundException e) {
			System.out.println("Attention le fichier des symptômes est introuvable!");
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("Fin de fichier atteinte!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Problème sur le fichier des symptômes " + e.getMessage());
			e.printStackTrace();
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

	/**
	 * Create a result data file, record each symptom with number of occurrences
	 * 
	 * @param symptomOccurence symptom/occurrences ordered list
	 * @throws IOException i/o file operation errors
	 */
	private static void writeResultsDataToFile(Map<String, Integer> symptomOccurence) throws IOException {

		// create file for results
		FileWriter writer = new FileWriter("results.out");
		try {

			// write count result
			for (String symptom : symptomOccurence.keySet()) {
				writer.write(symptom + " " + symptomOccurence.get(symptom) + "\n");
			}

		} catch (IOException e) {
			System.out.println("Problème sur le fichier des des résultats :" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
