import java.util.*;
import java.io.*;

public class SJF extends Scheduler {
	public SJF(boolean verbose, ArrayList<Process> processes, int quantum) {
		super(verbose, processes);
		this.name = "Shortest Job First";
		this.quantum = quantum;
		setQuantum(processes);
	}

	private static void setQuantum(ArrayList<Process> list) {
		for (Process p : list) {
			p.setQuantumMax(1000);
		}
	}

	protected void maintainReadyQueue() {
		// dPrint();
		// Only allow one process at a time in the ready queue

		// Begin by choosing the shortest ready process
		Process p = new Process(0,0,0,0);
		p.setTimeLeft(10000);
		boolean shouldStop = true;
		for (Process temp : ready) {
			if (terminated.contains(temp)) continue;
			if (temp.getBurstLeft() > 0) {
				return;
			}
			// System.out.printf("Process %d timeLeft: %d", temp.getId(), temp.timeLeft);
			if (temp.timeLeft < p.timeLeft) {
				p = temp;
				shouldStop = false;
			}
		}
		if (shouldStop) return;

		// Now that we have the shortest ready process, set it to the ready queue
		// Only if it is not yet terminated, not blocked, and not running
		if (!readyQueue.contains(p) && !blockedList.contains(p) && p.getState() != 2) readyQueue.addFirst(p);
		// dPrint();

		ListIterator<Process> i = ready.listIterator();
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
		System.out.print("Ready queue: ");
		for (Process p : readyQueue) {
			System.out.print(p.getId() + " ");
		}
		System.out.println();
	}
}