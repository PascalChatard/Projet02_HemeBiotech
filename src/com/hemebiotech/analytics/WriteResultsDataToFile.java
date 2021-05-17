package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Create a result file, record each symptom with number of occurrences
 * 
 * @author Pascal Chatard
 * @param filePathName a full or partial path name to file
 */
public class WriteResultsDataToFile implements ISymptomOccurrenceWriter {
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
	public void recordData(Map<String, Integer> symptomOccurence) {

		// create file for results write
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePathName), StandardCharsets.UTF_8)) {
			// write count result
			for (String symptom : symptomOccurence.keySet()) {
				writer.write(symptom + " " + symptomOccurence.get(symptom) + "\n");
			}
		} catch (IOException e) {
			System.out.println("Problème sur le fichier des résultats...!\n " + e.getMessage());
			e.printStackTrace();

		}

	}

}
