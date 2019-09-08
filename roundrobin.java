import java.io.File; 
import java.util.Scanner; 
class roundrobin 
{ 
	public static void roundRobin(int a[], int b[], int n) 
	{ 
		int res = 0; 
		int resc = 0; 

		int res_b[] = new int[b.length]; 
		int res_a[] = new int[a.length]; 

		for (int i = 0; i < res_b.length; i++) 
		{ 
			res_b[i] = b[i]; 
			res_a[i] = a[i]; 
		} 
		int t = 0; 
		int wait[] = new int[a.length]; 
		int turn[] = new int[a.length];
		int resp[] = new int[a.length]; 
		int c=0;
		for(int i=0;i<a.length;i++)
			resp[i]=-1;
		while (true) 
		{ 
			boolean flag = true; 
			for (int i = 0; i < a.length; i++) 
			{
				if (res_a[i] <= t) 
				{ 
					if (res_a[i] <= n) 
					{
						if (res_b[i] > 0) 
						{ 
							if(resp[i]==-1)
								resp[i]=t-a[i];
							flag = false; 
							if (res_b[i] > n) 
							{ 
								t = t + n;
								res_b[i] = res_b[i] - n; 
								res_a[i] = res_a[i] + n; 
							} 
							else 
							{ 
						        t = t + res_b[i]; 
								turn[i] = t - a[i]; 
								wait[i] = t - b[i] - a[i]; 
								res_b[i] = 0; 
							} 
						} 
					} 
					else if (res_a[i] > n) 
					{ 
						for (int j = 0; j < a.length; j++) 
						{ 
							if (res_a[j] < res_a[i]) 
							{ 
								if (res_b[j] > 0) 
								{ 
									if(resp[i]==-1)
										resp[i]=t-a[i];
									flag = false; 
									if (res_b[j] > n) 
									{
										t = t + n; 
										res_b[j] = res_b[j] - n; 
										res_a[j] = res_a[j] + n; 
									} 
									else 
									{ 
										t = t + res_b[j]; 
										turn[j] = t - a[j]; 
										wait[j] = t - b[j] - a[j]; 
										res_b[j] = 0; 
									} 
								} 
							} 
						} 
                   		if (res_b[i] > 0) 
                   		{ 
                   			if(resp[i]==-1)
								resp[i]=t-a[i];
							flag = false; 
							if (res_b[i] > n) 
							{ 
								t = t + n; 
								res_b[i] = res_b[i] - n; 
								res_a[i] = res_a[i] + n; 
							} 
							else 
							{ 
								t = t + res_b[i]; 
								turn[i] = t - a[i]; 
								wait[i] = t - b[i] - a[i]; 
								res_b[i] = 0;  
							} 
						} 
					} 
				} 
				else if (res_a[i] > t) 
				{ 
					t++; 
					i--; 
				} 
			} 
			if (flag) 
				break;
		} 
		int resps=0;
		for (int i = 0; i < a.length; i++) 
		{ 
			System.out.println("Response time for process"+(i+1)+":"+resp[i]);
			System.out.println("Turnaround time for process"+(i+1)+":"+turn[i]);
			System.out.println("Waiting time for process"+(i+1)+":"+wait[i]+"\n"); 
			res+=wait[i]; 
			resc+=turn[i]; 
			resps+=resp[i];
		} 
		System.out.println("\nAverage waiting time is "+ (float)res / a.length); 
		System.out.println("Average turnaround time is "+ (float)resc / a.length);
		System.out.println("Average turnaround time is "+ (float)resps / a.length);  
	} 
	public static void main(String[] args) throws Exception
	{
		File file = new File("input.dat"); 
	    Scanner sc = new Scanner(file); 
	  	int n=Integer.parseInt(sc.nextLine());
		int arr[]=new int[n];
	  	int bt[]=new int[n];
	    int btc[]=new int[n];
		for(int i=0;i<n;i++)
	  	{
	  		arr[i]=Integer.parseInt(sc.next());
			bt[i]=Integer.parseInt(sc.next());
	    }
	    for(int i=0;i<n;i++)
	    {
	        for(int j=i;j<n;j++)
	        {
	         	 if(arr[i]>arr[j])
	         	{
	            	long t=arr[i];
		            arr[i]=arr[j];
		            arr[j]=t;
		            t=bt[i];
		            bt[i]=bt[j];
		            bt[j]=t;
	          	}
	        }
	    }
	    int x=Integer.parseInt(sc.next());
	    roundRobin(arr,bt,x);
	}
} 