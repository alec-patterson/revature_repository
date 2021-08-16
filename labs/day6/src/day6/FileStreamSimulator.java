package day6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamSimulator {
	/*
	 * Most common streams that are used in java come
	 * from the Java.io package
	 */
	private static FileOutputStream fos = null;
	private static FileInputStream fis = null;
	
	public static void main(String[] args) {
		System.out.println("::: Start of Application :::");
		try {
			fosExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fisExample();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("::: End of Application :::");
		
	}
	
	/*
	 * File output streams allow you to write to a file 
	 * location that is specified. we can put the path to the file (as it relates to the current project structure)
	 * in the constructor for the FileOutputStream object
	 */
	private static void fosExample() throws IOException {
		fos = new FileOutputStream("files/myfile.txt");
		for(int i = 0; i < 5; i++) {
			for( char letter = 'A'; letter <= 'Z'; letter++) {
				fos.write(letter);
				
				if (letter == 'Z')
					fos.write('\n');
			}
		}
		
		if (fos != null)
			fos.close();
	}
	
	/*
	 * FileInputStreams allow you to read from a source location (file)
	 * and read the date, one byte at a time.
	 */
	private static void fisExample() throws IOException {
		fis =  new FileInputStream("files/myfile.txt");
		byte in;
		while((in = (byte)(fis.read())) != -1) {
			System.out.print((char)in);
		}
		
		/*
		 * streams are resource intensive processes. and as such
		 * we should close the stream when we no longer require it
		 * in our program to save on those resources used
		 */
		if(fis != null)
			fis.close();
	}
}
