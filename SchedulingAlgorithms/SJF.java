public class SJF {
	Process p[];
	int n;
	float avgWaitingTime, avgTurnAroundTime;
	
	public SJF(Process[] p, int n){
		this.p = p;
		this.n = n;
	}
	
	public void computeWaitingTime(){
		//Computing Remaining time
		int remaining_time[] = new int[n];
		
		for(int i=0; i<n; i++)
			remaining_time[i] = p[i].burstTime;
		
		boolean check = false;
		
		int complete = 0, t = 0, minVal = Integer.MAX_VALUE;
		int shortest = 0, finish_time;
		
		while(complete!=n){
			//Compute Shortest Remaining Time
			for(int i=0; i<n; i++){
				if((p[i].arrivalTime<=t) && (remaining_time[i]<minVal) && (remaining_time[i]>0)){
					minVal = remaining_time[i];
					shortest = i;
					check = true;
				}
			}
			
			if(check == false){
				t++;
				continue;
			}
			
			remaining_time[shortest]--;//Reduce Remaining Time by 1
			
			//Update Minimum
			minVal = remaining_time[shortest];
			if(minVal==0)
				minVal=Integer.MAX_VALUE;
			
			 // If a process gets completely executed 
            if (remaining_time[shortest] == 0) { 
       
                // Increment complete 
                complete++; 
                check = false; 
       
                // Find finish time of current 
                // process 
                finish_time = t + 1; 
       
                // Calculate waiting time 
                p[shortest].waitingTime = finish_time - p[shortest].burstTime - p[shortest].arrivalTime; 
       
                if (p[shortest].waitingTime < 0) 
                	p[shortest].waitingTime = 0; 
            } 
            // Increment time 
            t++; 
		}
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
		System.out.println("Process \tBurst Time\tWaiting Time\tTurn Around Time");
		for(int i=0; i<n; i++){
			System.out.println(p[i].processNumber+"\t\t"+p[i].burstTime+"\t\t"+p[i].waitingTime+"\t\t"+p[i].turnAroundTime);
		}
		System.out.println("Average Waiting Time = " + avgWaitingTime);
		System.out.println("Average Turn Around Time = " + avgTurnAroundTime);
	}
}