import java.util.*;
import java.io.*;

class Lab2 {
	private static boolean verbose;
	private static int numProcs;
	private static List<String> processList;
	private static String originalInput;

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
			System.out.println(data);
			processList = new LinkedList<String>(Arrays.asList(data.split("\\s+")));

			numProcs = Integer.parseInt(processList.get(0));
			System.out.println("numProces: " + numProcs);
			processList.remove(0);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public static ArrayList<Process> createProcesses() {
		ArrayList<Process> allProcesses = new ArrayList<Process>();
		for (int i = 0; i < numProcs; i++) {
			int A = Integer.parseInt(processList.get(0 + (i*4)));
			int B = Integer.parseInt(processList.get(1 + (i*4)));
			int C = Integer.parseInt(processList.get(2 + (i*4)));
			int M = Integer.parseInt(processList.get(3 + (i*4)));
			Process newProcess = new Process(A, B, C, M);
			newProcess.setId(i);
			allProcesses.add(newProcess);
		}
		return allProcesses;
	}

	public static void runScheduler(Scheduler s) throws SchedulingException {
		/*
		for (int i = 0; i < 10; i++) {
			s.cycle();
		}
		*/

		System.out.println("The original input was: " + s.getOriginalProcesses());
		System.out.println("THe (sorted) input was: " + s.getSortedProcesses());
		System.out.println();
		while (s.notAllTerminated()) {
			s.cycle();
		}
		s.printResults();
	}
	public static void main(String[] args) throws SchedulingException {
		if (!init(args)) {
			System.out.println("Input in init function. Ending program.");
			return;
		}

		ArrayList<Process> allProcesses = createProcesses();
		// Do FCFS
		FCFS s1 = new FCFS(verbose, allProcesses);
		runScheduler(s1);
	}
}