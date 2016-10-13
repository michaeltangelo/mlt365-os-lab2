package mtoslab2;

import java.io.*;
import java.util.*;

public class Main {
	public static void parseInput(String input) {
		try {
			File in = new File(input);
			Scanner sc = new Scanner(in);
			String data = sc.useDelimiter("\\Z").next();
			// filter data to tokenize input
			data = data.replaceAll("[()]", "");
			System.out.println(data);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		// Initializing program -- getting input
		boolean verbose = false;
		String input;
		if (args.length == 2) {
			verbose = (args[0].equals("--verbose") ? true : false);
			input = args[1];
		}
		else input = args[0];

		parseInput(input);
		System.out.println("Got here.");
	}
}
