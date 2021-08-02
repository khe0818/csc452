import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class stopWordsFilter extends filters{

	private HashSet<String> stopWords;
	private int w;
	public stopWordsFilter(BlockingQueue<String> dataIn, BlockingQueue<String> dataOut, HashSet<String> stopWords) {
		super(dataIn, dataOut);
		if(stopWords != null) {
			this.stopWords = stopWords;
		}
		else {
			stopWords = new HashSet<String>();
		}
		
	}
	
	@Override
	public String getFilter(String input) {
		if(input == null)
		{
			return null;
		}
		if(stopWords.contains(input.toLowerCase())) {
			w++;
			return "";
		}
			w++;
			return input;

	}
	
	@Override
	public void prints() {
		//for(String e: FromQueue) {
	//		System.out.println(e);
	//	}
		
		System.out.printf("total words read from files :" + w);
		System.out.println();
	}
	
	@Override
    public void sleep() throws InterruptedException {
		Thread.sleep(100);
	}

}
