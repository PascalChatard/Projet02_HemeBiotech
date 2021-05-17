package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Anything that will read symptom data from a source The important part is, the
 * return value from the operation, which is a list of strings/values, that may
 * not contain duplicate element
 * 
 * The implementation need to order the list
 * 
 */
public interface ISymptomReader {
	/**
	 * If no data is available, return an empty List
	 * 
	 * @return a listing of all Symptoms/Occurrence obtained from a data source, no
	 *         duplicates are possible
	 */
	Map<String, Integer> getSymptoms();
}
