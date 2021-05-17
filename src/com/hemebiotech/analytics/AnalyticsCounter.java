package com.hemebiotech.analytics;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class AnalyticsCounter {
	
	/**
	 * Trend analysis program, read a symptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. POO code version.
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
			ISymptomReader objSymptomFile = new ReadSymptomDataFromFile("symptoms.txt");
			Map<String, Integer> symptomOccurrenceMap = objSymptomFile.getSymptoms();

			// create file for results
			WriteResultsDataToFile resultOutputFile = new WriteResultsDataToFile("results.out");
			resultOutputFile.recordData(symptomOccurrenceMap);

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

}
