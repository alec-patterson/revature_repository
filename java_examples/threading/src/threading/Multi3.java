package threading;

public class Multi3 implements Runnable{
	public void run() {
		System.out.println("thread is running... ");
	}
	
	public static void main(String[] args) {
		Multi3 m1 = new Multi3();
		Thread t1 = new Thread(m1);
//		System.out.println(t1.getName());
		t1.start();
		Thread t2 = new Thread(m1);
		t2.start();
		
	}
}
