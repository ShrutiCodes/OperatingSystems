public class FCFS {
	Process p[];
	int n;
	
	public FCFS(Process[] p, int n){
		this.p = p;
		this.n = n;
	}
	
	public void calculateWaitTime(){
		for(int i=1; i<n; i++){
			p[i].waitingTime = p[i-1].burstTime + p[i-1].waitingTime;
		}
	}
	
	public void calculateTurnAroundTime(){
		for(int i=0; i<n; i++){
			p[i].turnAroundTime = p[i].burstTime + p[i].waitingTime;
		}
	}
	
	public float calculateAvgWaitTime(){
		float avgWait=0;
		
		for(int i=0; i<n; i++){
			avgWait+=(float)p[i].waitingTime;
		}
		avgWait/=(float)n;
		return avgWait;
	}
	
	public float calculateAvgTurnAroundTime(){
		float avgTurn=0;
		
		for(int i=0; i<n; i++){
			avgTurn+=(float)p[i].turnAroundTime;
		}
		avgTurn/=(float)n;
		return avgTurn;
	}
	
	public void compute(){
		calculateWaitTime();
		calculateTurnAroundTime();
	}
	
	void show(){
		System.out.println("Process \tBurst Time\tWaiting Time\tTurn Around Time");
		for(int i=0; i<n; i++){
			System.out.println(p[i].processNumber+"\t\t"+p[i].burstTime+"\t\t"+p[i].waitingTime+"\t\t"+p[i].turnAroundTime);
		}
		System.out.println("Average Waiting Time = " + calculateAvgWaitTime());
		System.out.println("Average Turn Around Time = " + calculateAvgTurnAroundTime());
	}
}