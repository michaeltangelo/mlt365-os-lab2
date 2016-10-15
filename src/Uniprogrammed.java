import java.util.*;
import java.io.*;

public class Uniprogrammed extends Scheduler {
	public Uniprogrammed(boolean verbose, ArrayList<Process> processes) {
		super(verbose, processes);
		this.name = "Uniprogrammed";
	}

	protected void maintainReadyQueue() {
		// dPrint();
		// Only allow a process that is not yet terminated into the ready queue
		// Get the first process not yet terminated
		if (numProcesses == terminated.size()) return;

		Process p = new Process(0,0,0,0);
		for (Process temp : processes) {
			if (terminated.contains(temp)) continue;
			else {
				p = temp;
				break;
			}
		}

		// With that process, add to readyQueue if it is not already in it
		if (!readyQueue.contains(p) && !blockedList.contains(p) && p.getState() != 2) readyQueue.addFirst(p);
		// dPrint();
	}

	protected ArrayList<Process> sort(ArrayList<Process> processes) {
		// ArrayList<Process> sortedProcesses = new ArrayList<Process>();
		Collections.sort(processes, new Comparator<Process>() {
			public int compare(Process a, Process b) {
				int arrivalA = a.getA();
				int arrivalB = b.getA();
				if (arrivalA > arrivalB) return 1;
				else if (arrivalA < arrivalB) return -1;
				else return 0;
			}
		});

		return processes;
		// return sortedProcesses;
	}

	private void dPrint() {
		System.out.println("Ready queue: ");
		for (Process p : readyQueue) {
			System.out.print(p.getId() + " ");
		}
		System.out.println();
	}
}