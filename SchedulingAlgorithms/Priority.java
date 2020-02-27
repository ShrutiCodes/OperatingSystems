public class Priority {
	Process p[];
	int n;
	float avgTurnAroundTime,avgWaitingTime=0;
	
	public Priority(Process[] p, int n){
		this.p = p;
		this.n = n;
	}
	
	public void swapMembers(Process p1, Process p2){
		int tempProcess=p1.processNumber;
		int tempPriority = p1.priority;
		int tempBurst = p1.burstTime;
		
		p1.processNumber = p2.processNumber;
		p1.priority = p2.priority;
		p1.burstTime = p2.burstTime;
		
		p2.processNumber = tempProcess;
		p2.priority = tempPriority;
		p2.burstTime = tempBurst;
	}
	
	public void sort(){
		int min;
		for(int i=0; i<n-1; i++){
			min=i;
			for(int j=i+1; j<n; j++){
				if(p[min].priority>p[j].priority)
					min=j;
			}
			swapMembers(p[min], p[i]);
		}
	}
	
	public void computeWaitingTime(){	
		// waiting time for first process is 0 
	    p[0].waitingTime = 0; 
	  
	    for (int  i = 1; i < n ; i++ ){ 
	        p[i].waitingTime =  p[i-1].burstTime + p[i-1].waitingTime ;
	    }
		
	}
	
	public void computeTurnAroundTime(){
		for(int i=0; i<n; i++){
			p[i].turnAroundTime = p[i].burstTime + p[i].waitingTime;
		}
	}
	
	public void compute(){
		sort();
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
		System.out.println("Process \tPriority \tBurst Time\tWaiting Time\tTurn Around Time");
		for(int i=0; i<n; i++){
			System.out.println(p[i].processNumber+"\t\t"+p[i].priority+"\t\t"+p[i].burstTime+"\t\t"+p[i].waitingTime+"\t\t"+p[i].turnAroundTime);
		}
		System.out.println("Average Waiting Time = " + avgWaitingTime);
		System.out.println("Average Turn Around Time = " + avgTurnAroundTime);
	}
}