package threading;

import java.util.List;
import java.util.ArrayList;

public class ThreadEx {
	public static void main(String[] args) {
		for(int i = 0; i < 8; i++) {
			RunThread obj = new RunThread();
			obj.start();
		}
	}
}

class RunThread extends Thread {
	public static List<String> l = new ArrayList<String>();
	public static boolean stop = false;
	public void run() {
		try {
			printList();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void printList() {
		try {
			l.add(getName());
			for(String s: l) {
				System.out.println(s);
			}
			System.out.println();
		} catch (Exception e) {};
	}
}