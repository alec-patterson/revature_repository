package threading;

public class Mthreading {
	public static void main(String[] args) throws Exception{
		int n = 8;
		for(int i = 0; i < n; i++) {
			MultithreadingDemo obj = new MultithreadingDemo();
//			Thread.sleep(500);
//			Thread obj = new Thread(new MultithreadingDemo());
			obj.start();
		}
	}
}


class MultithreadingDemo extends Thread {//implements Runnable {
	public static int i = 0;
	public void run() {
		try {
			// Displaying the thread that is running
//			System.out.println("Thread " + getName() + " is running");
//			sleep(5000);
//			System.out.println("slept good? " + getName());
			updateI();
		}
		catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}
	synchronized void updateI() {
		System.out.println(i++);
	}
}