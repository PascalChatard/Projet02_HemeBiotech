package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * @return a raw listing of all Symptoms obtained from a data source, duplicates
	 *         are possible/probable
	 */

	@Override
	public List<String> getSymptoms() throws IOException {

		// symptom's list
		List<String> symptomList = new ArrayList<String>();
		
		if (filepath != null) {

			// open symptom's file
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line = reader.readLine();

			try {
				
				while (line != null) {
					symptomList.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				System.out.println("Attention le fichier des symptômes est introuvable!");
			} catch (EOFException e) {
				System.out.println("Fin de fichier atteinte!");
			} catch (IOException e) {
				System.out.println("Problème sur le fichier des symptômes " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (reader != null)
					reader.close();
			}
		}
		
		return symptomList;
	}


}
