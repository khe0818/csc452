import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

public class removeNonAlphabetical extends filters{
	private int m;
	private int n;
	public removeNonAlphabetical(BlockingQueue<String> dataIn, BlockingQueue<String> dataOut) {
		super(dataIn, dataOut);
	}
	@Override
	public String getFilter(String input) {
		
		 if(input == null){
	            return null;
	        }
		 if(!Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", input)){
			 n++;
		   return "";
		 }
		else{	
			m++;
			n++;
		  return input;
		}
	}

	@Override
	public void prints() {
		System.out.printf("after remove non stop words  :" + n);
		System.out.println();
		System.out.printf("after remove non alohabetical  :" + m);
		System.out.println();
		
	}
	


}
