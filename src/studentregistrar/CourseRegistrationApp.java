package studentregistrar;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.StringTokenizer;

import java.io.*;


public class CourseRegistrationApp implements Serializable {//has the main method to run different scenarios
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Student> students;//master list of students
	private static ArrayList<Course> courses;//master list of courses
	
	public static void main(String[] args) {
		//courses = retrieveCourseData(); //use this if .ser file doesn't exist and you have to populate courses
				//deserialization
				 try{
					  //FileInputSystem recieves bytes from a file
				      FileInputStream fis = new FileInputStream("courseRegistrationSystemApp.ser");
				      
				      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
				      ObjectInputStream ois = new ObjectInputStream(fis);
				      
				      //Cast as Employee. readObject will take the object from ObjectInputStream
				      courses = (ArrayList<Course>)ois.readObject();
				      students = (ArrayList<Student>)ois.readObject();
				      ois.close();
				      fis.close();
				      System.out.println("Deserialization complete!");
				    }
				    catch(IOException ioe) {
				       ioe.printStackTrace();
				       return;
				    }
				 catch(ClassNotFoundException cnfe) {
				       cnfe.printStackTrace();
				       return;
				 }

		System.out.println("Enter if you're an admin (Admin) or student (Student): ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
		String identity = input.readLine();
		while (!(identity.equals("Admin")) && !(identity.equals("Student"))) {
			System.out.println("Try again with a valid identity verification: ");
			identity = input.readLine();
		}
		if (identity.equals("Admin")) {//only one admin and username is Admin and password is Admin001
			System.out.println("Enter your username: ");
			String username = input.readLine();
			System.out.println("Enter your password: ");
			String password = input.readLine();
			while (!(username.equals("Admin")) || !(password.equals("Admin001"))) {
				if (!(username.equals("Admin"))) {
					System.out.println("Username is incorrect. Try again: ");
					username = input.readLine();
				}
				else {//scenario in which password is invalid
					System.out.println("Password is incorrect.  Try again: ");
					password = input.readLine();
				}
			}
			/*
			System.out.println("What is your first name?");//seems illogical in real world but I am to create an admin using its constructor
			String firstName = input.readLine();
			System.out.println("What is your last name?");//illogical here too lol?
			String lastName = input.readLine();
			*/
			Admin holyAdmin = new Admin();
			
			//Menu
			System.out.println("What do you want to do today? ");
			adminMenu(holyAdmin);
		}
		else {//scenario in which user declares himself / herself a Student
			System.out.println("Enter your username: ");
			String username = input.readLine();//username can be anything during first login?
			System.out.println("Enter your password: ");
			String password = input.readLine();//password can be anything during first login?
			System.out.println("Enter your first name: ");
			String fname = input.readLine();
			System.out.println("Enter your last name: ");
			String lname = input.readLine();
			Student peasant = new Student(fname, lname, username, password);
			students.add(peasant);
			System.out.println("What do you want to do today? ");
			studentMenu(peasant);		
		}
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
		//Serialization from Dr. Anasse Bari's sample code
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("courseRegistrationSystemApp.ser");
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(courses);
			oos.writeObject(students);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
		
	public static void adminMenu(Admin holyAdmin) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Admin options: ");
		System.out.println("1. Create a new course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display information for a given course");
		System.out.println("5. Register a student");
		System.out.println("6. View all courses");
		System.out.println("7. View all FULL courses");
		System.out.println("8. Write to a file the list of courses that are full");
		System.out.println("9. View the names of students registered in a given course");
		System.out.println("10. View the list of courses that a given student is registered on");
		System.out.println("11. Sort courses based on number of current student registers: ");
		System.out.println("12. Exit");
		System.out.println("Enter your choice(integers from 1-12): ");
		int adminInput = Integer.parseInt(input.readLine());
		while (adminInput < 1 || adminInput > 12) {
			System.out.println("Try again: ");
			adminInput = Integer.parseInt(input.readLine());
		} 
		while (adminInput >= 1 && adminInput < 12) {
			try {
				switch(adminInput) {
				//Courses Management
				case 1: System.out.println("Create a new course\n");
						holyAdmin.createCourse(courses);
						break;
				case 2:	System.out.println("Delete a course\n");
						System.out.println("Enter valid ID of course: ");
						String ID = input.readLine();
						System.out.println("Enter valid section number of course: ");
						int secNum = Integer.parseInt(input.readLine());
						holyAdmin.deleteCourse(courses, students, ID, secNum);
						break;
				case 3:	System.out.println("Edit a course\n");
						System.out.println("Enter valid ID of course: ");
						String iD = input.readLine();
						System.out.println("Enter a valid course section number: ");
						int sectNum = Integer.parseInt(input.readLine());
						holyAdmin.editCourse(courses, students, iD, sectNum);
						break;
				case 4:	System.out.println("Display information for a given course\n");
						System.out.println("Enter valid course ID: ");
						String courseID = input.readLine();
						holyAdmin.displayCourseInfo(courses, courseID);
						break;
				case 5:	System.out.println("Register a student\n");
						holyAdmin.registerStudent(students);
						break;
				//Reports
				case 6:	System.out.println("View all courses\n");
						holyAdmin.viewAllCourses(courses);
						break;
				case 7:	System.out.println("View all FULL courses\n");
						holyAdmin.viewAllFullCourses(courses);
						break;
				case 8:	System.out.println("Write to a file the list of coures that are FULL\n");
						holyAdmin.writeToFileCoursesFull(courses);
						break;
				case 9:	System.out.println("View the names of students registered in a given course\n");
						System.out.println("Enter valid course ID: ");
						String cID = input.readLine();
						System.out.println("Enter valid course section number: ");
						int sNumb = Integer.parseInt(input.readLine());
						holyAdmin.viewStudentsOfCourse(courses, cID, sNumb);
						break;
				case 10: System.out.println("View the list of courses that a given student is registered on\n");
					 	 System.out.println("Enter student's first name (case sensitive)");	 
					 	 String fname = input.readLine();
					 	 System.out.println("Enter student's last name (case sensitive)");
					 	 String lname = input.readLine();
					 	 holyAdmin.viewAllEnrolledCourses(students, fname, lname);
					 	 break;
				case 11: System.out.println("Sort courses based on number of current student registers\n");
					 	 holyAdmin.sortCoursesByCurrNumOfStudents(courses);
					 	 break;
				case 12: System.out.println("Exit\n");
					     holyAdmin.exit();
					     //input.close();
					     break;
				}
				System.out.println("Select another option: ");
				System.out.println("Admin options: ");
				System.out.println("1. Create a new course");
				System.out.println("2. Delete a course");
				System.out.println("3. Edit a course");
				System.out.println("4. Display information for a given course");
				System.out.println("5. Register a student");
				System.out.println("6. View all courses");
				System.out.println("7. View all FULL courses");
				System.out.println("8. Write to a file the list of courses that are full");
				System.out.println("9. View the names of students registered in a given course");
				System.out.println("10. View the list of courses that a given student is registered on");
				System.out.println("11. Sort courses based on number of current student registers: ");
				System.out.println("12. Exit");
				adminInput = Integer.parseInt(input.readLine());
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
			}
		
	public static void studentMenu(Student peasant) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Student options");
		System.out.println("1. View all courses");
		System.out.println("2. View all courses that are NOT full");
		System.out.println("3. Register on a course:");
		System.out.println("4. Withdraw from a course: ");
		System.out.println("5. View all courses that student is registered in");
		System.out.println("6. Exit");
		System.out.println("Enter a choice (integers from 1-6)");
		int studentInput = Integer.parseInt(input.readLine());
		while (studentInput < 1 || studentInput > 6) {
			System.out.println("Try again: ");
			studentInput = Integer.parseInt(input.readLine());
		}
		while (studentInput >= 1 && studentInput < 6) {
		try {
			switch(studentInput) {
				//Course Management
			case 1: System.out.println("View all courses\n");
					peasant.viewAllCourses(courses);
					break;
			case 2: System.out.println("View all open courses\n");
					peasant.viewAllOpenCourses(courses);
					break;
			case 3: System.out.println("Register on a course\n");
					peasant.registerCourse(courses);
					break;
			case 4: System.out.println("Withdraw from a course\n");
					peasant.withdraw(courses);
					break;
			case 5: System.out.println("View all courses that a student is registered in\n");
					peasant.viewAllEnrolledCourses();
					break;	
			case 6: System.out.println("Exit\n");
					peasant.exit();
					input.close();
					break;
			}
			System.out.println("Select another option: ");
			System.out.println("Student options");
			System.out.println("1. View all courses");
			System.out.println("2. View all courses that are NOT full");
			System.out.println("3. Register on a course:");
			System.out.println("4. Withdraw from a course: ");
			System.out.println("5. View all courses that student is registered in");
			System.out.println("6. Exit");
			studentInput = Integer.parseInt(input.readLine());
		}catch (IOException e) {
			e.printStackTrace();
		}
		}
	}		
	public static ArrayList<Course> retrieveCourseData() {//reads CSV file using tokenizer to set courses
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
	}
	
	/*
	public static boolean same (String str1, String str2){
	   return str1.equals(str2);
	}
	*/
	
}
