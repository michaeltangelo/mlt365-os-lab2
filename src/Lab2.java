import java.util.*;
import java.io.*;

class Lab2 {
	private static boolean verbose;
	private static int numProcs;
	private static List<String> processList;

	// Sets verbose boolean and reads in input from args
	private static boolean init(String[] args) {
		// Initializing program -- getting input
	 	verbose = false;
		String input;
		if (args.length == 2) {
			verbose = (args[0].equals("--verbose") ? true : false);
			input = args[1];
		}
		else if (args.length == 1) {
			input = args[0];
		}
		else {
			System.out.println("Incorrect number of arguments.");
			return false;
		}

		try {
			File in = new File(input);
			Scanner sc = new Scanner(in);
			String data = sc.useDelimiter("\\Z").next();
			sc.close();
			// filter data to tokenize input
			data = data.replaceAll("[()]", "");
			processList = new LinkedList<String>(Arrays.asList(data.split(" ")));

			numProcs = Integer.parseInt(processList.get(0));
			processList.remove(0);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public static boolean createProcesses(Scheduler s, int numProcs, List<String> processes) {
		for (int i = 0; i < numProcs; i++) {
			int A = Integer.parseInt(processes.get(i+0));
			int B = Integer.parseInt(processes.get(i+1));
			int C = Integer.parseInt(processes.get(i+2));
			int M = Integer.parseInt(processes.get(i+3));
			Process newProcess = new Process(A, B, C, M);
			s.addProcess(newProcess);
		}
		return true;
	}

	public static void main(String[] args) {
		if (!init(args)) {
			System.out.println("Input in init function. Ending program.");
			return;
		}

		Scheduler s = new Scheduler();
		if (!createProcesses(s, numProcs, processList)) {
			System.out.println("Failed to create processes. Ending.");
			return;
		}

		s.printProcesses();


	}
}