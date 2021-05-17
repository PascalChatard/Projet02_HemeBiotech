package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Create a result file, record each symptom with number of occurrences
 * 
 * @author Pascal Chatard
 * @param filePathName a full or partial path name to file
 */
public class WriteResultsDataToFile {
	private String filePathName;

	/**
	 * 
	 * @param filepath a full or partial path to file for results write
	 */

	public WriteResultsDataToFile(String filepath) {
		this.filePathName = filepath;
	}

	/**
	 * Record each symptom with number of occurrence in the file filePathName
	 * 
	 * @param symptomOccurence a ordered symptom/occurrences list
	 * @throws Exception
	 */
	public void recordData(Map<String, Integer> symptomOccurence) throws IOException {

		if (filePathName != null) {

			// create file for results write
			FileWriter writer = new FileWriter(filePathName);
			try {
				// write count result
				for (String symptom : symptomOccurence.keySet()) {
					writer.write(symptom + " " + symptomOccurence.get(symptom) + "\n");
				}

			} catch (IOException e) {
				System.out.println("Problème sur le fichier des résultats " + e.getMessage());
			} finally {
				if (writer != null)
					writer.close();
			}
		}

	}

}
