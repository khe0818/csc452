import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class FilterTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String[] list = {"alice30.txt", "kjbible.txt", "usdeclar.txt"};

		File files;
        File stopWordsFile = new File("stopwords.txt");
        HashSet<String> stopWords = new HashSet<String>();
        String st;
        try {
        	BufferedReader br  = new BufferedReader(new FileReader(stopWordsFile));

           
            while((st = br.readLine()) != null){
                stopWords.add(st);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BlockingQueue<String> q1 = new LinkedBlockingQueue<String>();
        BlockingQueue<String> q2 = new LinkedBlockingQueue<String>();
        BlockingQueue<String> q3 = new LinkedBlockingQueue<String>();
        BlockingQueue<String> q4 = new LinkedBlockingQueue<String>();

        filters f1 = new stopWordsFilter(q1, q2, stopWords);
        filters f2 = new removeNonAlphabetical(q2, q3);
        filters f3 = new porterStemmingFilter(q3, q4);
        filters f4 = new dataSinkFilter(q4, null); 

        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
        Thread t3 = new Thread(f3);
        Thread t4 = new Thread(f4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        int w = 0;
        BufferedReader ss;
        try {	
        	Pattern pattern = null;
        	 	for(int i = 0; i< list.length; i++) {
        		files = new File(list[i]);
        		ss = new BufferedReader(new FileReader(files));        
        		while((st = ss.readLine()) != null){
					Matcher matcher = pattern.compile("[a-zA-Z_0-9]+").matcher(st);
	                while(matcher.find()){
	                    q1.add(st.substring(matcher.start(), matcher.end())); 
	                    w++;
	                }
            }
        		ss.close();
        	 	}
        	 	System.out.println("total numbers when read: " + w);
        	 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        f1.stopRunning();
        f1.sleep();
        f2.stopRunning();
        f2.sleep();
        f3.stopRunning();
        f3.sleep();
        f4.stopRunning();
        f4.sleep();
        f1.prints();
        f2.prints();
        f3.prints();
        f4.prints();

    }
}
