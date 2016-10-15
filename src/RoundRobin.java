import java.util.*;
import java.io.*;

public class RoundRobin extends Scheduler {
	public RoundRobin(boolean verbose, ArrayList<Process> processes, int quantum) {
		super(verbose, processes);
		this.name = "Round Robin";
		this.quantum = quantum;
		setQuantum(processes);
	}

	private static void setQuantum(ArrayList<Process> list) {
		for (Process p : list) {
			p.setQuantumMax(2);
		}
	}

	protected void maintainReadyQueue() {
		ListIterator<Process> i = ready.listIterator();
		// LinkedList<Process> newReadyList = (LinkedList<Process>) ready.clone();
		while (i.hasNext()) {
			Process p = i.next();
			if (!readyQueue.contains(p)) readyQueue.addFirst(p);
		}
		i = readyQueue.listIterator();
		while (i.hasNext()) {
			Process p = i.next();
			if (ready.contains(p)) ready.remove(p);
		}
		/*
		for (Process p : ready) {
			readyQueue.addFirst(p);
		}
		*/
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
}