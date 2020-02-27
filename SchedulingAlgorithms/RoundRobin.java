public class RoundRobin {
	Process p[];
	int n,quantum;
	float avgTurnAroundTime,avgWaitingTime=0;
	
	public RoundRobin(Process[] p, int n, int quantum){
		this.p = p;
		this.n = n;
		this.quantum = quantum;
	}
	
	public void computeWaitingTime(){
		int rem_burst_time[] = new int[n];
		for(int i=0; i<n; i++){
			rem_burst_time[i]=p[i].burstTime;
		}
		
		int t=0, cnt=0;
		int[] burst = new int[n];
		
		for(int i=0;i<n;i++){
			burst[i] = p[i].burstTime;
		}
		
		do{
			for(int i=0;i<n;i++){
				if(p[i].arrivalTime <= t && burst[i] != 0 && p[i].executed){
					int temp = burst[i]<quantum ? burst[i] : quantum;
					burst[i] -= temp;
					t += temp;
				}
				if(burst[i] == 0 && p[i].executed){
					cnt++;
					p[i].turnAroundTime = t - p[i].arrivalTime;
					p[i].waitingTime= t - p[i].arrivalTime - p[i].burstTime;
					p[i].executed = false;
				}
				else if(!p[i].executed){
					continue;
				}
			}
		}while(cnt < n);
	}
	
	public void computeTurnAroundTime(){
		for(int i=0; i<n; i++){
			p[i].turnAroundTime = p[i].burstTime + p[i].waitingTime;
		}
	}
	
	public void compute(){
		computeWaitingTime();
		computeTurnAroundTime();
		for(int i=0; i<n; i++){
				avgWaitingTime += p[i].waitingTime;
				avgTurnAroundTime += p[i].turnAroundTime; 
		}
		
		avgWaitingTime = (float)avgWaitingTime/(float)n;
		avgTurnAroundTime= (float)avgTurnAroundTime/(float)n;
	}
	
	void show(){
		System.out.println("Process \tBurst Time\tArrivalTime\tWaiting Time\tTurn Around Time");
		for(int i=0; i<n; i++){
			System.out.println(p[i].processNumber+"\t\t"+p[i].burstTime+"\t\t"+p[i].arrivalTime+"\t\t"+p[i].waitingTime+"\t\t"+p[i].turnAroundTime);
		}
		System.out.println("Average Waiting Time = " + avgWaitingTime);
		System.out.println("Average Turn Around Time = " + avgTurnAroundTime);
	}
}