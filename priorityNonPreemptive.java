import java.io.File; 
import java.util.Scanner; 
import java.util.Arrays; 
public class priorityNonPreemptive
{
	public static void sortpart(long arr[],long pr[], long bt[])
    { 
   		int n=arr.length;
     	for(int i=0;i<n;i++)
       	{
        	int x=i;
         	for(int j=i;j<n;j++)
        	{
           		if(arr[j]==arr[i])
            	x=j;
        	}
          if(x!=i)
        	{
            for(int j=0;j<n;j++)
            {
              for(int k=i;k<n;k++)
              {
                if(pr[j]>pr[k])
                {
                  long t=pr[j];
                  pr[j]=pr[k];
                  pr[k]=t;
                  t=bt[j];
                  bt[j]=bt[k];
                  bt[k]=t;
                }
              }
            }
          }
        	i=x;
      	}
  	}
	public static void main(String[] args) throws Exception
	{
		File file = new File("input_ps_pe.dat"); 
    	Scanner sc = new Scanner(file); 
  		int n=Integer.parseInt(sc.nextLine());
  		long arr[]=new long[n];
  		long arrc[]=new long[n];
  		long bt[]=new long[n];
      long pr[]=new long[n];
  		for(int i=0;i<n;i++)
  		{
  			arr[i]=Long.parseLong(sc.next());
  			bt[i]=Long.parseLong(sc.next());
        pr[i]=Long.parseLong(sc.next());
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
        arrc[i]=arr[i];
      }
   		sortpart(arr,pr,bt);
  		long et[]=new long[n];
  		long rt[]=new long[n];
  		long turn[]=new long[n];
  		long s=0,t=arrc[0];
  		for(int i=0;i<n;i++)
      {
        s=((arr[i]<s)?s:arr[i])+bt[i];
        et[i]=s;
        turn[i]=et[i]-arr[i];
        for(int j=i+1;j<n;j++)
        {
          arr[j]-=arr[i];
          arr[j]-=bt[i];
          if(arr[j]<0)
            arr[j]=0;
        }
        bt[i]=0;
        sortpart(arr,bt);
      }
      rt[0]=0;s=0;
      for(int i=1;i<n;i++)
        rt[i]=(et[i-1]<arrc[i])?0:(et[i-1]-arrc[i]);
      int r=0;
      for(int i=0;i<n;i++)
      {
        System.out.println("Response Time for process "+(i+1)+":"+rt[i]);
        System.out.println("Turnaround Time for process "+(i+1)+":"+turn[i]);
        System.out.println("Waiting Time for process "+(i+1)+":"+rt[i]);
        System.out.println();
        s=s+rt[i];t=t+turn[i];r=r+rt[i];
      }
      System.out.println("Average Response Time:"+(double)((double)s/(double)n));
      System.out.println("Average Turnaround Time:"+(double)((double)t/(double)n));
      System.out.println("Average Waiting Time:"+(double)((double)r/(double)n));
  }
}
          