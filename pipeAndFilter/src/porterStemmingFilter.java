import java.util.concurrent.BlockingQueue;

import org.tartarus.snowball.ext.PorterStemmer;

public class porterStemmingFilter extends filters{
	
	private int m;
	private PorterStemmer ps;
	public porterStemmingFilter(BlockingQueue<String> dataIn, BlockingQueue<String> dataOut) {
		super(dataIn, dataOut);
		ps = new PorterStemmer();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getFilter(String input) {
		if(input == null) {
			return null;
		}
		String temp;
			ps.setCurrent(input);
			m++;
			if(ps.stem()) {
				temp = ps.getCurrent();
				return temp;
			}
			else {
				return "";
			}
		
	
	   

		
	}
	@Override
	public void prints() {
		System.out.printf("after porter stemming filter  :" + m);
		System.out.println();
		
	}
	

	
	

}
