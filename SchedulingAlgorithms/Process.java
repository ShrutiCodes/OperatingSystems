public class Process {
	String processName;
	int processNumber,burstTime,arrivalTime;
	int waitingTime;
	int turnAroundTime;
	int completionTime;
	int priority;
	boolean executed;
	
	public Process(){
		processName="";
		burstTime=0;
		processNumber = 0;
		waitingTime=arrivalTime=0;
		turnAroundTime=0;
		completionTime=0;
		priority=0;
		executed=true;
	}
	
	public Process(int processNumber, int burstTime){
		this.burstTime=burstTime;
		this.processNumber = processNumber;
		this.processName = "Process" + processNumber;
	}
	
	public Process(int processNumber, int burstTime, int arrivalTime){
		this.burstTime=burstTime;
		this.processNumber = processNumber;
		this.arrivalTime = arrivalTime;
		this.processName = "Process" + processNumber;
	}
	
	public Process(int processNumber, int burstTime, int arrivalTime, int priority){
		this.burstTime=burstTime;
		this.processNumber = processNumber;
		this.arrivalTime = arrivalTime;
		this.processName = "Process" + processNumber;
		this.priority=priority;
		this.executed=true;
	}
}