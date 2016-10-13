import java.util.*;
import java.io.*;

// Class for a scheduler
public class Scheduler {
	private ArrayList<Process> processes;
	private Scanner random;

	public Scheduler() {
		processes = new ArrayList<Process>();
		initRandomStream();
	}

	public void printProcesses() {
		System.out.println("Printing all processes for this scheduler:");
		System.out.println("----------------------");
		for (int i = 0; i < processes.size(); i++) {
			System.out.println("Process " + i + ": " + processes.get(i).toString());
		}
	}

	public void addProcess(Process p) {
		processes.add(p);
	}

	public ArrayList<Process> getProcesses() {
		return processes;
	}

	private void printCycle(boolean verbose) {
	}

	private void initRandomStream() {
		try {
			File file = new File("input/random-numbers");
			if (file.exists()) random = new Scanner(file);
			else System.out.println("Could not find random-numbers");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public int randomOS(int B) {
		return 1 + (Integer.parseInt(random.nextLine()) % B);
	}

	public void run() {
		Process p1 = new Process(0,1,100,2);
		while (true) {
			break;
		}

		System.out.println("I AM RUNNING.");
	}
}