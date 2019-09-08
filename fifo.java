import java.io.File; 
import java.util.Scanner; 
public class fifo
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("input.dat"); 
    	Scanner sc = new Scanner(file); 
  		int n=Integer.parseInt(sc.nextLine());
  		long arr[]=new long[n];
  		long bt[]=new long[n];
  		for(int i=0;i<n;i++)
  		{
  			arr[i]=Long.parseLong(sc.next());
  			bt[i]=Long.parseLong(sc.next());
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
  		long et[]=new long[n];
  		long rt[]=new long[n];
  		long turn[]=new long[n];
  		long s=0,t=0;
  		for(int i=0;i<n;i++)
  		{
  			s=((arr[i]<s)?s:arr[i])+bt[i];
  			et[i]=s;
  			turn[i]=et[i]-arr[i];
  		}
  		rt[0]=0;s=0;
  		for(int i=1;i<n;i++)
  			rt[i]=(et[i-1]<arr[i])?0:(et[i-1]-arr[i]);
  		for(int i=0;i<n;i++)
  		{
  			System.out.println("Response Time for process "+(i+1)+":"+rt[i]);
  			System.out.println("Turnaround Time for process "+(i+1)+":"+turn[i]);
  			System.out.println("Waiting Time for process "+(i+1)+":"+rt[i]);
  			System.out.println();
  			s=s+rt[i];t=t+turn[i];
  		}
  		System.out.println("Average Response Time:"+(double)((double)s/(double)n));
  		System.out.println("Average Turnaround Time:"+(double)((double)t/(double)n));
	}
}