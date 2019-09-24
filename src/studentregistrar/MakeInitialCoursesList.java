package studentregistrar;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MakeInitialCoursesList  {
	public ArrayList<Course> retrieveCourseData() {//reads CSV file using tokenizer to set courses
		String courseData = "MyUniversityCourses.csv";
		ArrayList<Course> courseList = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(courseData);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			bufferedReader.readLine();//skip over first line because it is useless?
			String lineOfCSV = bufferedReader.readLine();
			StringTokenizer strTokens = new StringTokenizer(lineOfCSV);
			do  {
				while (strTokens.hasMoreTokens()) {
					String courseName = strTokens.nextToken(",");
					String courseID = strTokens.nextToken(",");
					int maxNumOfStudents = Integer.parseInt(strTokens.nextToken(","));
					int currNumOfStudents = Integer.parseInt(strTokens.nextToken(","));
					ArrayList<String> studentNames = null;//idk how to bypass this but since student names list is empty
					strTokens.nextToken(",");
					String instructorName = strTokens.nextToken(",");
					int courseSecNum = Integer.parseInt(strTokens.nextToken());
					String courseLoc = strTokens.nextToken(",");
					Course course = new Course(courseName, courseID, instructorName, courseSecNum, courseLoc, studentNames,
							 maxNumOfStudents, currNumOfStudents);
					courseList.add(course);
				}
			} while ((lineOfCSV = bufferedReader.readLine()) != null && (strTokens = new StringTokenizer(lineOfCSV)) != null);
			bufferedReader.close();
			
		}
		//The catch block performs a specific action depending on the exception
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + courseData + "'");
			ex.printStackTrace();//as quoted in Dr. Bari's manual, this will print out an error output stream
		}
		catch (IOException ex) {
			System.out.println("Error reading file '" + courseData + "'");
			ex.printStackTrace();
		}
		return courseList;

		
		/*
		String input = new Scanner(new File(file)).useDelimiter("\\A").next();
		StringTokenizer strTokens = new StringTokenizer(input);
		*/
	}
}
