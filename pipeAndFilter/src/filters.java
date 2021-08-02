import java.util.concurrent.BlockingQueue;

public class filters implements Runnable{
	
	protected BlockingQueue<String> dataIn;
	protected BlockingQueue<String> dataOut;
	private volatile boolean flag = true;
	
	public filters(BlockingQueue<String> dataIn,BlockingQueue<String> dataOut) {
		this.dataIn = dataIn;
		this.dataOut = dataOut;
	}
	public String getFilter(String input) {
		return null;
		
	}
	public void run() {

		while(flag) {
			if(!dataIn.isEmpty()){
				//System.out.println("sdsds");
				String temp = getFilter(dataIn.poll());
				//System.out.println(temp);
				if(dataOut != null && (temp != null && !temp.isEmpty())) {
					dataOut.add(temp);
				}
			}
		}
		
		
	}
	
	public void prints() {
	}
	public void stopRunning() {
		flag = false;
	}
	
	public void sleep() throws InterruptedException {
		Thread.sleep(1000);
	}
	

}
