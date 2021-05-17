package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class AnalyticsCounter {
	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int dialatedPupilCount = 0;
	
	/**
	 * Trend analysis program, read asymptom's file, count the occurrences of three
	 * symptom's type and write the result in a text file. This is the Alex's
	 * version code correction.
	 * 
	 * @param args not used
	 * @throws Exception untreated
	 */
	public static void main(String args[]) throws Exception {
		// open data source file
		BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
		String line = reader.readLine();

		while (line != null) {
			System.out.println("symptom from file: " + line);
			if (line.equals("headache")) {
				headacheCount++;
				System.out.println("number of headaches: " + headacheCount);
			}
			else if (line.equals("rash")) {
				rashCount++;
			}
			else if (line.equals("dialated pupils")) {
				dialatedPupilCount++;
			}

			line = reader.readLine(); // get next symptom
		}

		// close input file text
		reader.close();
		
		// generate output result file
		FileWriter writer = new FileWriter("results.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + dialatedPupilCount + "\n");
		writer.close();
	}
}
