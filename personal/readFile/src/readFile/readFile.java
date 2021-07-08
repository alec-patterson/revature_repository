package readFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readFile {
	public static void main(String[] args) {
		Scanner fn = new Scanner(System.in);
		System.out.print("Enter the name if the file: ");
		String fileName = fn.nextLine();
		fn.close();
		try {
			File obj = new File(fileName);
			Scanner reader = new Scanner(obj);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			reader.close();
		}
			
		catch(FileNotFoundException e) {
			System.out.println("Error has occured");
		}
	}
}
