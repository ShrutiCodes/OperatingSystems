/* 
 * PROBLEM STATEMENT:
 * Write a Java program (using OOP features) to implement the following scheduling
 * algorithms: FCFS, SJF(Preemptive), Priority(Non-Preemptive),  Round-Robin (Preemptive).
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
	Scanner sc = new Scanner(System.in);
	Process p[] = new Process[5];
	int processCount=0;
	int quantum=1;
	
	public void inputProcess() throws IOException{
		FileReader reader;
		BufferedReader br = null;
		String line, tokens[];
		
		try{
			reader = new FileReader("toSchedule.txt");
			br = new BufferedReader(reader);
		}catch(Exception e){
			System.out.println(e);
		}
		
		while ((line = br.readLine()) != null){
			tokens = line.split("\t");
			Process tempProcess = new Process(processCount,Integer.parseInt(tokens[2]),Integer.parseInt(tokens[4]), Integer.parseInt(tokens[3]));
			p[processCount] = tempProcess;
			processCount++;
		}
		
	}
	
	public Process[] getProcess(){
		return p;
	}
	
	public int getProcessCount(){
		return processCount;
	}
	
	public int getQuantum(){
		System.out.print("Enter Quantum : ");
		quantum= sc.nextInt();
		return quantum;
	}
	
	public static void main(String args[]) throws IOException{
		Main ob = new Main();
		ob.inputProcess();
				
		System.out.println("-------------------------------FCFS-------------------------------");
		FCFS fcfs = new FCFS(ob.getProcess(),ob.getProcessCount());
		fcfs.compute();
		fcfs.show();
		
		System.out.println("-------------------------------------Priority-------------------------------------");
		Priority p = new Priority(ob.getProcess(),ob.getProcessCount());
		p.compute();
		p.show();
		
		System.out.println("------------------------Shortest Job First------------------------");
		SJF sjf = new SJF(ob.getProcess(),ob.getProcessCount());
		sjf.compute();
		sjf.show();	
		
		System.out.println("-----------------------------------Round Robin-----------------------------------");
		RoundRobin rb = new RoundRobin(ob.getProcess(),ob.getProcessCount(),ob.getQuantum());
		rb.compute();
		rb.show();
		
	}
}