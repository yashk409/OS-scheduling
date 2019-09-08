import java.io.File; 
import java.util.Scanner; 
import java.util.Arrays; 
public class stcf
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("input.dat"); 
    Scanner sc = new Scanner(file); 
  	int n=Integer.parseInt(sc.nextLine());
		long arr[]=new long[n];
  	long bt[]=new long[n];
    long btc[]=new long[n];
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
      btc[i]=bt[i];
      }
    long waiting[]=new long[n];
    long turnaround[]=new long[n];
    long response[]=new long[n];
    int smallest,y=0,count=0;
    double avgt=0,avgw=0,avgr=0;
    long end=0;int prevsm=-1;
    int c=0;
    for(int i=0;i<n;i++)
      {
        if(btc[i]>btc[y])
          y=i;
      }
    for(long time=arr[0];count!=n;time++)
    {
      smallest=y;
      for(int i=0;i<n;i++)
      {
        if(arr[i]<=time && bt[i]<bt[smallest] && bt[i]>0 )
        smallest=i;
      }
      if(smallest!=prevsm && c<n)
      {
        response[smallest]=time-arr[smallest];
        if(response[smallest]<0)
          response[smallest]=0;
        c++;
      }
      prevsm=smallest;
      bt[smallest]--;
      if(bt[smallest]==0)
      {
        count++;
        end=time+1;
        waiting[smallest]= end - arr[smallest] - btc[smallest];
        turnaround[smallest] = end - arr[smallest];
      }
    }
    for(int i=0;i<n;i++)
    {
      System.out.println("Turnaround Time for process "+(i+1)+":"+turnaround[i]);
      System.out.println("Waiting Time for process "+(i+1)+":"+waiting[i]);
      System.out.println("Response Time for process"+(i+1)+":"+response[i]+"\n");
      avgt+=turnaround[i];
      avgw+=waiting[i];
      avgr+=response[i];
    }
    System.out.println("\nAverage waiting time:"+avgw/n);
    System.out.println("Average turnaround time:"+avgt/n);
    System.out.println("Average response time:"+avgr/n);
  }
}
