package mtoslab2;

import java.util.*;

// Class for a process
public class Process {
	// A - Arrival time
	// B - The inclusive limit of a random cpu burst
	// C - Total CPU time needed
	// M - The multiplier for calculating I/O burst (CPU burst * M)
	int A, B, C, M;

	// 0 - unstarted
	// 1 - ready
	// 2 - running
	// 3 - blocked
	// 4 - terminated
	int state;

	// Keep track of total IO Time (time in Blocked state)
	int ioTime;

	// Keep track of total wait time (time in Ready state)
	int waitTime;

	// This is equal to C + ioTime + waitTime
	int finishTime;

	// This is equal to finishTime - A
	int turnaroundTime;

	// Variables to track the progress of a process
	// How much longer before the process is complete
	int timeLeft;

	// If in block state, how long until process is ready
	int blockLeft;

	// The total time of the CPU burst (used to calculate IO block time)
	int burstTotal;

	// Tracks how much longer process has in cpuBurst
	int burstLeft;

	public Process(int A, int B, int C, int M) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.M = M;
		this.timeLeft = C;
		this.ioTime = 0;
		this.waitTime = 0;
		this.finishTime = 0;
		this.blockLeft = 0;
		this.turnaroundTime = 0;
		this.burstTotal = 0;
		this.burstLeft = 0;
		// Set state to unstarted
		this.state = 0;
	}

	// Sets the process to ready
	public void setReady() {
		if (this.state != 0 || this.state != 3) throw new SchedulingException("Attempted to set process to ready from an incompatible state.");
		this.state = 1;
	}

	// Sets the process to run
	public void setToRun(int cpuBurst) {
		if (this.state != 1) throw new SchedulingException("Attempted to run non-ready process.");
		this.state = 2;

		// Sets the burst time for the process
		this.burstTotal = cpuBurst;
		this.burstLeft = cpuBurst;
	}

	public void setTerminate() {
		if (this.timeLeft > 0) throw new SchedulingException("Process terminated before completion.");
		this.state = 4;
	}

	public void setBlocked() {
		if (this.state != 2) throw new SchedulingException("Attempted to block non-running process.");
		this.state = 3;
		this.blockLeft = this.burstTotal * this.M;
	}

	public void tick() {
		switch (state) {
			// Unstarted State - Do nothing
			case 0: break;

			// Ready State
			case 1: handleReadyTick();
					break;

			// Running State
			case 2: handleRunTick();
					break; 

			// Blocked State
			case 3: handleBlockTick();
					break;

			// Terminated State
			case 4: handleTerminationTick();
					break;

			default: throw new SchedulingException("State unknown.");
					break;
		}
	}

	private void handleReadyTick() {
		if (state!=1) throw new SchedulingException("handleReadyTick() invoked when not in ready state.");
		waitTime++;
	}

	private void handleRunTick() {
		if (state!=2) throw new SchedulingException("handleRunTick() invoked when not in ready state.");

		if (burstLeft >= 0) {
			timeLeft--;
			burstLeft--;
		}

		// Set state to terminate if process has finished all necessary CPU ticks
		if (timeLeft <= 0) {
			setTerminate();
			return;
		}
		// Set state to blocked if process has finished its burst cycle
		else if (burstLeft <= 0) {
			setBlocked();
			return;
		}
	}

	private void handleBlockTick() {
		if (state!=3) throw new SchedulingException("handleBlockTick() invoked when not in blocked state.");

		if (blockLeft >= 0) {
			ioTime++;
			blockLeft--;
		}

		// Set state to ready if process has finished I/O
		if (blockLeft <= 0) {
			setReady();
			return;
		}
	}

	// Essentially does nothing
	private void handleTerminationTick() {
		return;
	}
}