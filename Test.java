import java.io.*;
import java.util.*;
import java.text.*;
import java.util.concurrent.*;
import java.util.zip.GZIPOutputStream;

public class Test
{
	static int ThreadNum =1; 
	static int Duration = 300; // seconds;  Program will exit after Duration of seconds.
	
	static int ReferenceSize = 1024 * 10;  // each reference object size; 
        static int CountDownSize = 1000 * 100;
        static int EachRemoveSize = 1000 * 50; // remove # of elements each time. 
        
        
	public static void main(String[] args) 
            throws IOException
	{
		if (args.length > 0 ) {
			Duration = Integer.parseInt(args[0]);
			ThreadNum = Integer.parseInt(args[1]); 
		}
		
		for(int i=0; i< ThreadNum; i++ ) {
			LoadThread thread = new LoadThread(); 
			thread.start(); 
		}

	}
}

class LoadThread extends Thread {
	long timeZero = System.currentTimeMillis(); 
	long finishedUnit = 0; 

	public LoadThread() {
	}

	public void run() {		
		AbstractQueue<String> q = new ArrayBlockingQueue<String>(Test.CountDownSize);			
		char[] srcArray =new char[Test.ReferenceSize];
		String emptystr = new String(srcArray);
	  	finishedUnit =0; 
		long prevTime = timeZero; 
	
		for (int i = 0;; i = i + 1) {		
			// Simulate object use to force promotion into OldGen and then GC
			if (q.size() >= Test.CountDownSize) {
				String strHuge_remove =null; 
				for (int j = 0; j < Test.EachRemoveSize; j++) {
					strHuge_remove = q.remove();
				}
 				finishedUnit ++; 

				// every 1000 removal is counted as 1 unit. 
				long curTime = System.currentTimeMillis(); 
				long timeDiff = curTime - prevTime; 
				prevTime = curTime; 

  				long totalTime = curTime - timeZero; 
  				if (totalTime > Test.Duration * 1000){
                                   System.exit(0); 
				}
                                

                                Date dNow = new Date( );
                                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSSSS");
				System.out.println(ft.format(dNow) + " finished Units (1K) = " + finishedUnit ); 
			}
			srcArray = new char[Test.ReferenceSize]; 
			emptystr = new String(srcArray);
			String str = emptystr.replace('\0', 'a');
			q.add(str);
		}
 	}
}
