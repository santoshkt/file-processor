package biz.neustar.fp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

import biz.neustar.fp.Data.Category;

public class FileProcessor {

	private static void printCategoryCount(int categoryCount[]) {
		// EFFECTS: Prints the category and its count to console.

		System.out.println("CATEGORY COUNT");
		for (Category c : Category.values()) {
			System.out.println(c + " " + categoryCount[c.ordinal()]);
		}
	}

	private static void printDataList(LinkedHashSet<Data> hs) {
		// EFFECTS: Prints the string representation of data objects
		// in the Linked HashSet hs.

		for (Data d : hs) {
			System.out.println(d.toString());
		}
	}

	public void processFile(Path filePath) {
		// REQUIRES: filePath is a valid data file.
		// EFFECTS: Reads line by line in filePath, processes the pair
		// (category, sub-category) once, ignores duplicates and invalid
		// categories. Prints count of each category and prints data in the
		// order of the first occurrence of each pair

		if (filePath == null) {
			return;
		}

		BufferedReader reader = null;
		// Linked HashSet to store data objects.
		LinkedHashSet<Data> hs = new LinkedHashSet<Data>();
		// Array to store the number of times each category occurs.
		// The index in the array is the ordinal of the category in Enum.
		int[] categoryCount = new int[Category.values().length];

		try {
			reader = Files
					.newBufferedReader(filePath, Charset.defaultCharset());
			String line = null;
			while ((line = reader.readLine()) != null) {

				// Avoid leading and trailing white spaces.
				String str = line.trim();
				String category = str.substring(0, str.indexOf(' '));
				String subCategory = str.substring(str.indexOf(' ') + 1);

				try {

					// If the category is valid and there is no duplicate of
					// category and sub-category, increment the count.

					if (Category.valueOf(category) != null
							&& hs.add(new Data(category, subCategory))) {
						categoryCount[Category.valueOf(category).ordinal()]++;
					} else {
						// Category already exists.
						// ignore the line
					}
				} catch (IllegalArgumentException iae) {
					// Category doesn't exists.
					// ignore the line
				}

			}

		} catch (IOException ioe) {
			System.out
					.println("Error: Could not read file " + ioe.getMessage());
			return;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// Ignore
			}
		}

		printCategoryCount(categoryCount);
		printDataList(hs);
	}

	public static void main(String args[]) {

		// Verify arguments
		if (args == null || args.length == 0) {
			System.out
					.println("Usage: java biz.neustar.fp.FileProcessor <input_file_path>");
			return;
		}

		Path filePath = Paths.get(args[0]);

		// Check if the file exists
		if (!Files.exists(filePath)) {
			System.out
					.println("Error: Input file does not exist or the path is incorrect.");
			return;
		}

		FileProcessor fp = new FileProcessor();
		fp.processFile(filePath);
	}

}
