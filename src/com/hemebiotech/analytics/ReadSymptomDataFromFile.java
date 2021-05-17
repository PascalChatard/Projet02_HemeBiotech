package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it,
	 *                 one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}
	
	/**
	 * If no data is available, return an empty List
	 * 
	 * @return a ordered listing of all Symptoms/occurrences obtained from a data
	 *         source, without duplicates element
	 * @throws FileNotFoundException the symptom file does not exist or cannot be
	 *                               found
	 * @throws IOException           other i/o file operation errors
	 */
	@Override
	public Map<String, Integer> getSymptoms() {

		// symptom/occurrences's list
		Map<String, Integer> symptomList = new TreeMap<>();
		
		if (filepath != null) {

			// open symptom's file
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath), StandardCharsets.UTF_8)) {
				
				String symptomLine = reader.readLine();
				while (symptomLine != null) {
					Integer valeur = symptomList.get(symptomLine);
					if (valeur == null) // it's new symptom
						symptomList.put(symptomLine, 1);
					else {
						valeur++;
						symptomList.replace(symptomLine, valeur);
					}
					symptomLine = reader.readLine(); // get next symptom
				}

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
		return symptomList;
	}
}
