package mtoslab2;

import java.util.*;
import java.io.*;

// Class for a scheduler
public class Scheduler {
	ArrayList<Processes> processes = new ArrayList<Processes>;
	Scanner random;

	public Scheduler(Processes p) {
		this.processes = p;
		initRandomStream();
	}
	private void printCycle(boolean verbose) {
	}

	private void initRandomStream() {
		random = new Scanner(new File("random-numbers"));
	}

	private int randomOS(int B) {
		return 1 + (Integer.parseInt(random.nextLine()) % B);
	}

	public void run() {
		Process p1 = new Process(0,1,100,2);
		while (true) {
			p1.tick(randomOS(p1.B));
		}

		System.out.println("I AM RUNNING.");
	}
}