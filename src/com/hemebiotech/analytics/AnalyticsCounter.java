package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Default main class : read symptom's file and writes statistic file
 *
 * @author Pascal Chatard
 */
public class AnalyticsCounter {

	public final static String DEFAULT_INPUT_FILENAME = "symptoms.txt";
	public final static String DEFAULT_OUTPUT_FILENAME = "results.out";
	
	/**
	 * Trend analysis program, read a symptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. This is a POO version,
	 * with Interface, Classe and NIO2 library.
	 * 
	 * @param args not used
	 */
	public static void main(String args[]) {
		String inputFilename = DEFAULT_INPUT_FILENAME;
		String outputFilename = DEFAULT_OUTPUT_FILENAME;

		if (args != null && args.length > 0) {
			if (args.length == 1) {
				// check valid fileName
				if (isValidFileName(args[0])) {
					inputFilename = args[0];
				}
			} else {
				if (!isValidFileName(args[0])) {
					inputFilename = args[0];
				}
				if (!isValidFileName(args[1])) {
					outputFilename = args[1];
				}
			}

		}

		// reading symptom's file and return symptom/occurrences list without duplicate
		// elements
		ISymptomReader objSymptomFile = new ReadSymptomDataFromFile(inputFilename);
		Map<String, Integer> symptomOccurrenceMap = objSymptomFile.getSymptoms();

		// create file for results
		ISymptomOccurrenceWriter objSymptomOccurrenceFile = new WriteResultsDataToFile(outputFilename);
		objSymptomOccurrenceFile.recordData(symptomOccurrenceMap);

		System.out.println("\n***********  Analyse du fichier des symptomes terminée!  ***********");

	}

	/**
	 * Valid fileName.
	 * 
	 * @param fileName the string to check;
	 * @return true or false
	 */
	private static boolean isValidFileName(String fileName) {
		if (fileName.isEmpty())
			return false;
		// check contains no space
		if (fileName.trim() != fileName)
			return false;

		return fileName.matches("^[\\w,\\s-]+\\.[A-Za-z]{3}$");
	}

}
