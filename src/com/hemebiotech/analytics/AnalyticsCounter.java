package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalyticsCounter {
	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int dialatedPupilCount = 0;
	
	/**
	 * Trend analysis program, read a symptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. This is the Alex's
	 * version code correction. + manage file Exception.
	 * 
	 * @param args not used
	 * @throws FileNotFoundException the symptom file does not exist or cannot be
	 *                               found
	 * @throws EOFException          end of file reached
	 * @throws IOException           other i/o file operation errors
	 */
	public static void main(String args[]) throws Exception {
		// open data source file
		BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
		String line = reader.readLine();
		// create file for results
		FileWriter writer = new FileWriter("results.out");

		try {
			while (line != null) {
				// traits only three symptoms...
				System.out.println("symptom from file: " + line);
				if (line.equals("headache")) {
					headacheCount++;
					System.out.println("number of headaches: " + headacheCount);
				} else if (line.equals("rash")) {
					rashCount++;
				} else if (line.equals("dialated pupils")) {
					dialatedPupilCount++;
				}

				line = reader.readLine(); // get next symptom
			}

			// record count result
			writer.write("headache: " + headacheCount + "\n");
			writer.write("rash: " + rashCount + "\n");
			writer.write("dialated pupils: " + dialatedPupilCount + "\n");

		} catch (FileNotFoundException e) {
			System.out.println("Attention le fichier des symptômes n'existe pas ou est introuvable!");
		} catch (EOFException e) {
			System.out.println("Fin de fichier atteinte");
		} catch (IOException e) {
			System.out.println("Problème de lecture/écriture de fichier" + e.getMessage());
		} finally {
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		}
	}
}
