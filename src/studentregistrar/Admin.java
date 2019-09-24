package studentregistrar;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Admin extends User implements AdminInterface, Comparator<Course>, Serializable {
	//The username and password for the admin are: Admin and Admin001
	public Admin() {
		super();
	}
	public Admin(String fname, String lname) {
		super(fname, lname, "Admin", "Admin001");
	}
	//Create a new course
	public void createCourse(ArrayList<Course> courses) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter Course Name: ");
			String courseName = input.readLine();
			System.out.println("Enter Course ID: ");
			String courseID = input.readLine();
			System.out.println("Enter Instructor Name: ");
			String instructorName = input.readLine();
			System.out.println("Enter Section Number (integer): ");
			int secNum = Integer.parseInt(input.readLine());
			System.out.println("Enter Course Location: ");
			String loc = input.readLine();
			//don't ask user for list of students in course bc no one is in a class initially
			System.out.println("Enter Course Max Capacity (integer): ");
			int maxNum = Integer.parseInt(input.readLine());
			//Similarly, don't ask user for current number of students bc it is 0 initially
			Course newCourse = new Course(courseName, courseID, instructorName, secNum, loc, null, maxNum, 0);
			courses.add(newCourse);
			//input.close(); //closing causes a problem because it goes in a loop and says stream closed?
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Delete a course
	public void deleteCourse(ArrayList<Course> courses, ArrayList<Student> students, String id, int secNum) {//Couldn't bypass concurrent modification error without using
		//iterators; received some help from Stack Overflow
		Iterator<Course> citer = courses.iterator();

		while (citer.hasNext()) {
		    Course c = citer.next();

		    if (c.getCourseID().equals(id) && c.getCourseSecNumber() == secNum) {
		        citer.remove();
		}
	

		for (Student s : students) {
			ArrayList<Course> enrolledCourses = s.getEnrolledCourses();
			Iterator<Course> iter = enrolledCourses.iterator();
			
			while (iter.hasNext()) {
				Course sc = iter.next();
				
				if (sc.getCourseID().equals(id) && sc.getCourseSecNumber() == secNum) {
					iter.remove();
				}
			}
		}

		}
	}
	//Edit a course (name, id, section number, location, max number of students); don't think i need to take into account other course fields
	public void editCourse(ArrayList<Course> courses, ArrayList<Student> students, String id, int secNum) throws NumberFormatException {
		try {
			System.out.println("Enter a number indicating what"
					+ " you want to change about a course (name = 1, id = 2, section number = 3, location = 4, max number of students = 5,"
					+ " instructor name = 6, adding a student to a class = 7, withdrawing from a class = 8");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int choice = Integer.parseInt(input.readLine());//1 = name, 2 = id, 3 = section number, 4 = location, 5 = max number of students, 6 = instructor name
			switch(choice) {
			case 1: System.out.println("Enter new course name (case sensitive): ");
					String newName = input.readLine();
					System.out.println("Enter the name of the course you want to change: ");//all courses with this name will be changed?
					String oldName = input.readLine();
					for (Course c : courses) {
						if (c.getCourseName().equals(oldName)) {
							c.setCourseName(newName);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseName().equals(oldName)) {
								c.setCourseName(newName);
							}
						}
					}
					break;
			case 2: System.out.println("Enter new course id: ");
					String newID = input.readLine();
					System.out.println("Enter the course's old ID (case sensitive): ");//do i assume that courses with dif names that have same ids will all be changed?
					String oldID = input.readLine();
					System.out.println("Enter the section number: ");//just in case ask for section number too
					int secNumber = Integer.parseInt(input.readLine());
					for (Course c : courses) {
						if (c.getCourseID().equals(oldID) && c.getCourseSecNumber() == secNumber) {
							c.setCourseID(newID);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseID().equals(oldID) && c.getCourseSecNumber() == secNumber) {
								c.setCourseID(newID);
							}
						}
					}
					break;				
			case 3: System.out.println("Enter new section number: ");//section number isn't that unique so admin might need to provide more info
					int newSecNum = Integer.parseInt(input.readLine());
					System.out.println("Enter the ID of the course (case sensitive): ");//combo of id and section number is more unique?
					String courseID = input.readLine();
					System.out.println("Enter old section number: ");//courses with same name and id appear to have dif section numbers in csv file
					int oldSecNum = Integer.parseInt(input.readLine());
					for (Course c : courses) {
						if (c.getCourseID().equals(id) && c.getCourseSecNumber() == oldSecNum) {
							c.setCourseSecNumber(newSecNum);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseID().equals(courseID) && c.getCourseSecNumber() == oldSecNum) {
								c.setCourseSecNumber(newSecNum);
							}
						}
					}
					break;
			case 4: System.out.println("Enter new location: ");//more info might similarly need to be provided
					String newLoc = input.readLine();
					System.out.println("Enter old location (case sensitive): ");
					String oldLoc = input.readLine();
					System.out.println("Enter course ID (case sensitive: ");
					String courseId = input.readLine();
					System.out.println("Enter section number: ");
					int courseSectNum = Integer.parseInt(input.readLine());
					for (Course c : courses) {
						if (c.getCourseID().equals(courseId) && c.getCourseSecNumber() == courseSectNum && c.getCourseLocation().equals(oldLoc)) {
							c.setCourseLocation(newLoc);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseID().equals(courseId) && c.getCourseSecNumber() == courseSectNum && c.getCourseLocation().equals(oldLoc)) {
								c.setCourseLocation(newLoc);
							}
						}
					}
					break;
			case 5: System.out.println("Enter new max number of students: ");
					int newMax = Integer.parseInt(input.readLine());
					System.out.println("Enter course ID (case sensitive): ");
					String cID = input.readLine();
					System.out.println("Enter section number: ");
					int sNum = Integer.parseInt(input.readLine());
					for (Course c : courses) {
						if (c.getCourseID().equals(cID) && c.getCourseSecNumber() == sNum) {
							c.setMaxStudentNum(newMax);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseID().equals(cID) && c.getCourseSecNumber() == sNum) {
								c.setMaxStudentNum(newMax);
							}
						}
					}
					break;
			case 6: System.out.println("Enter new instructor name: ");
					String newInstrName = input.readLine();
					System.out.println("Enter course ID (case sensitive): ");
					String coID = input.readLine();
					System.out.println("Enter section number: ");
					int sectNum = Integer.parseInt(input.readLine());
					for (Course c : courses) {
						if (c.getCourseID().equals(coID) && c.getCourseSecNumber() == sectNum) {
							c.setInstructor(newInstrName);
						}
					}
					for (Student s : students) {
						for (Course c : s.getEnrolledCourses()) {
							if (c.getCourseID().equals(coID) && c.getCourseSecNumber() == sectNum) {
								c.setInstructor(newInstrName);
							}
						}
					}
					break;
			case 7: System.out.println("Enter your first name: ");//assuming student exists already
					String fName = input.readLine();
					System.out.println("Enter your last name: ");
					String lName = input.readLine();
					for (Student s : students) {
						if (s.getFirstName().equals(fName) && s.getLastName().equals(lName)) {
							s.registerCourse(courses);
							}
						}
					break;
			case 8: System.out.println("Enter your first name: ");
					String firstName = input.readLine();
					System.out.println("Enter your last name: ");
					String lastName = input.readLine();
					for (Student s : students) {
						if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) {
							s.withdraw(courses);
						}
					}
			//input.close();
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Display a course's info by accessing course id
	public void displayCourseInfo(ArrayList<Course> courses, String id) {
		for (Course c : courses) {
			if (c.getCourseID() == id) {
				System.out.println("Course name: " + c.getCourseName());
				System.out.println("Course ID: " + c.getCourseID());
				System.out.println("Course Location: " + c.getCourseLocation());
				System.out.println("Course Section Number: " + c.getCourseSecNumber());
				System.out.println("Course Max Capacity: " + c.getMaxStudentNum());
				System.out.println("Course Instructor: " + c.getInstructorName());
			}
		}
	}
	//Register a student //similar to creating new course?
	public void registerStudent(ArrayList<Student> students) {//Overload?
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your first name (Caps First Letter): ");
			String fname = input.readLine();
			System.out.println("Enter your last name (Caps Last Letter): ");
			String lname = input.readLine();
			System.out.println("Enter your username: ");
			String uname = input.readLine();
			System.out.println("Enter your password: ");
			String pw = input.readLine();
			Student newStudent = new Student(fname, lname, uname, pw);
			students.add(newStudent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*view all classes (for every course the admin should be able to see the list of course name,
course id, and number of students registered and the maximum number of students allowed
to be registered)*/
	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		for (Course c : courses) {
			System.out.println("Course Name: " + c.getCourseName());
			System.out.println("Course ID: " + c.getCourseID());
			System.out.println("Number of Students Registered: " + c.getCurrStudentNum());
			System.out.println("Maximum Number of Students Allowed to be Registered: " + c.getMaxStudentNum());
			System.out.println(" ");
		}
	}
	//view all classes that are full
	public void viewAllFullCourses(ArrayList<Course> courses) {
		for (Course c : courses) {
			if (c.getCurrStudentNum() == c.getMaxStudentNum()) {
				System.out.println("Course Name:" + c.getCourseName() + ", Course ID: " + c.getCourseID() + ", Course Sec Number: " 
			+ c.getCourseSecNumber());
				System.out.println(" ");
			}
		}
	}
	
	//write to a file the list of courses that are full
	public void writeToFileCoursesFull(ArrayList<Course> courses) {//writing mainly from Anasse's code
		String fileName = "FullClasses.txt";
		//Scanner scan = new Scanner(System.in);
		
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//bufferedWriter.write("[ ");
			for (Course c : courses) {
				if (c.getCurrStudentNum() == c.getMaxStudentNum()) {
					bufferedWriter.write(c.getCourseName() + ", ");
					bufferedWriter.write(c.getCourseID() + ", ");
					bufferedWriter.write(c.getCourseSecNumber());
					bufferedWriter.newLine();
				}
			}
			//bufferedWriter.newLine();
			//bufferedWriter.write("]");
			
	//Always close writer
			bufferedWriter.close();
		}

		//Always close files

		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
	
	//view the names of students being registered in a specific course
	public void viewStudentsOfCourse(ArrayList<Course> courses, String id, int secNum) {
		System.out.println("Names of Students Registered in Course: ");
		for (Course c : courses) {
			if (c.getCourseID().equals(id) && c.getCourseSecNumber() == secNum) {
				for (String name : c.getListOfStudentNames()) {
					System.out.println(name);
				}
			}
		}
	}
	//view the list of courses that a given student is registered on(takes student's first name and last name as inputs)
	public void viewAllEnrolledCourses(ArrayList<Student> students, String studentFname, String studentLname) {
		for (Student s : students) {
			if (s.getFirstName().equals(studentFname) && s.getLastName().equals(studentLname)) {
				for (Course c : s.getEnrolledCourses()) {
					System.out.println("Course name: " + c.getCourseName());
					System.out.println("Course ID: " + c.getCourseID());
					System.out.println("Course Section Number: " + c.getCourseSecNumber());
					System.out.println(" ");
				}
			}
		}	
	}
	//sort courses list based on the current number of student registers
	public void sortCoursesByCurrNumOfStudents(ArrayList<Course> courses) {
		Collections.sort(courses, new Comparator<Course>() {
			 @Override   //concept of comparators etc from stack overflow etc.
		     public int compare(Course c1, Course c2) {
		         return Integer.compare(c1.getCurrStudentNum(), c2.getCurrStudentNum()); // Ascending
		     }
	    });
	}
	 @Override 
     public int compare(Course c1, Course c2) {
		 return Integer.compare(c1.getCurrStudentNum(), c2.getCurrStudentNum());
     }
	 public void exit() {
		 System.out.println("Logging off...Thank you for using the app, Admin!");
		 return;
	 }
}
