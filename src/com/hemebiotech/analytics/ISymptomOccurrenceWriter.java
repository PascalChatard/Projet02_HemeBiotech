package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.Map;

/**
 * Anything that will write symptom/occurrence data to a file The important part
 * is, the operation record a list of strings/values, that may not contain
 * duplicate element
 * 
 * The implementation record a ordered list Strings/Values in a file
 * 
 */

public interface ISymptomOccurrenceWriter {
	void recordData(Map<String, Integer> symptomOccurence) throws IOException;

}
