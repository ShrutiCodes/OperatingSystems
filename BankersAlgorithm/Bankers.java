package bankers;
import java.io.*;
import java.util.*;

public class Bankers {

	int countProcesses;
	int countResources;
	int Available[];
	int Max[][];
	int Allocation[][];
	int Need[][];
	int SafeSequence[];
	
	Bankers() throws IOException{
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("Enter number of processes");
//		countProcesses=sc.nextInt();
		
//		System.out.println("Enter number of resource types");
//		countResources=sc.nextInt();
		
		countProcesses=5;
		countResources=3;
		
		Available = new int[countResources];
		Max = new int[countProcesses][countResources];
		Allocation = new int[countProcesses][countResources];
		Need = new int[countProcesses][countResources];
		SafeSequence = new int[countProcesses];
		initializeData();
	}
	
	void initializeData() throws NumberFormatException, IOException{
		FileReader readerAlloc, readerMax, readerAvail;
		BufferedReader brAlloc = null, brMax = null, brAvail = null;
		String line, tokens[];
		
		try{
			readerAlloc = new FileReader("inputAllocation.txt");
			brAlloc = new BufferedReader(readerAlloc);
			
			readerMax = new FileReader("inputMax.txt");
			brMax = new BufferedReader(readerMax);
			
			readerAvail = new FileReader("inputAvailable.txt");
			brAvail = new BufferedReader(readerAvail);
		}catch(Exception e){
			System.out.println(e);
		}
		
		//initialize Allocation array
		int j=0;
		while ((line = brAlloc.readLine()) != null){
			tokens = line.split("\t");
			for(int i=0; i<tokens.length; i++){
				Allocation[j][i]=Integer.parseInt(tokens[i]);
			}
			j++;
		}
		
		//initialize Max array
		j=0;
		while ((line = brMax.readLine()) != null){
			tokens = line.split("\t");
			for(int i=0; i<tokens.length; i++){
				Max[j][i]=Integer.parseInt(tokens[i]);
			}
			j++;
		}
		
		//initialize Available array
		while ((line = brAvail.readLine()) != null){
			tokens = line.split("\t");
			for(int i=0; i<tokens.length; i++){
				Available[i]=Integer.parseInt(tokens[i]);
			}
		}
		
		//calculate Need based on Max and Allocation
		for(int i=0; i<countProcesses; i++){
			for(int k=0; k<countResources; k++){
				Need[i][k]=Max[i][k]-Allocation[i][k];
			}
		}
	}
	
	public boolean checkSafe(){
		int count=0, i, j;
		int Work[] = new int[countResources];
		boolean Visited[] = new boolean[countProcesses];
		
		for(i=0; i<Available.length; i++){
			Work[i]=Available[i];
		}
		for(i=0; i<Visited.length; i++){
			Visited[i]=false;
		}
		while(count<countProcesses){
			boolean flag=false;
			
			for(i=0; i<countProcesses; i++){
				if(Visited[i]==false){
					for(j=0; j<countResources; j++){
						if(Need[i][j]>Work[j]){
							break;
						}
					}
					
					if(j==countResources){
						SafeSequence[count]=i;
						count++;
						Visited[i]=true;
						flag=true;
						
						for(j=0; j<countResources; j++){
							Work[j]=Work[j]+Allocation[i][j];
						}
					}
				}
			}
			if(flag==false){
				break;
			}
		}
		if(count<countProcesses){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void displaySafeSequence(){
		System.out.println("Safe Sequence");
		for(int i=0; i<countProcesses; i++){
			System.out.print("P" + SafeSequence[i]); 
            if (i != countProcesses-1) 
            	System.out.print(" -> "); 
		}
	}
	
	public static void main(String[] args) throws IOException {
		Bankers ob = new Bankers();
		if(ob.checkSafe()){
			ob.displaySafeSequence();
		}
		else{
			System.out.println("System is UNSAFE!");
		}
	}

}
