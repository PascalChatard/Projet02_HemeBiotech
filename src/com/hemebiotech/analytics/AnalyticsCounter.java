package com.hemebiotech.analytics;

import java.util.Map;

public class AnalyticsCounter {
	
	/**
	 * Trend analysis program, read a symptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. This is a POO version,
	 * with Interface, Classe and NIO2 library.
	 * 
	 * @param args not used
	 */
	public static void main(String args[]) {

			// reading symptom's file and return symptom list with duplicate elements
			ISymptomReader objSymptomFile = new ReadSymptomDataFromFile("symptoms.txt");
			Map<String, Integer> symptomOccurrenceMap = objSymptomFile.getSymptoms();

			// create file for results
			ISymptomOccurrenceWriter objSymptomOccurrenceFile = new WriteResultsDataToFile("results.out");
			objSymptomOccurrenceFile.recordData(symptomOccurrenceMap);

			System.out.println("\n***********  Analyse du fichier des symptomes terminée!  ***********");

	}

}
