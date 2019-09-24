//package studentregistrar;
//import java.io.*;
//
//public class OpenCSVFileTest {
//
//	public static void main(String[] args) {
//		String fileToOpen = "MyUniversityCourses.csv";
//		String line = null;
//		
//		try {
//			FileReader fileReader = new FileReader(fileToOpen);
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			while ((line= bufferedReader.readLine()) != null) {
//				System.out.println(line);
//			}
//			bufferedReader.close();
//		}
//		catch(FileNotFoundException ex) {
//			System.out.println("Unable to open file");
//			ex.printStackTrace();
//		}
//		catch (IOException ex) {
//			System.out.println("Could not read file");
//		}
//	
//	}
//
//}
