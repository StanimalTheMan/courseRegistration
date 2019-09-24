package studentregistrar;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class Student extends User implements StudentInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Course> enrolledCourses = new ArrayList<> ();//necessary no?
	public Student(String fname, String lname, String username, String password) {
		super(fname, lname, username, password);
	}
	//accessors
	public String getFirstName() {
		return super.getFirstName();
	}
	public String getLastName() {
		return super.getLastName();
	}
	public ArrayList<Course> getEnrolledCourses() {//are these getters bad code in Java?  It wasn't good to return types like vectors in C++
		return enrolledCourses;
	}
	/*
	 * view all classes (student doesn't have to know number of students in class, max number (only needs to know if class
		is full?
	*/
	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		for (int i = 0; i < courses.size(); ++i) {
			System.out.println("Course Name: " + courses.get(i).getCourseName());
			System.out.println("Course ID: " + courses.get(i).getCourseID());
			System.out.println("Course Section Number: " + courses.get(i).getCourseSecNumber());
			System.out.println("Course Location: " + courses.get(i).getCourseLocation());
			System.out.println(" ");
		}
	}
	
	//view all classes that are not full
	public void viewAllOpenCourses(ArrayList<Course> courses) {
		for (Course c : courses) {
			if (c.getCurrStudentNum() != c.getMaxStudentNum()) {
				System.out.println("Course Name: " + c.getCourseName());
				System.out.println("Course ID: " + c.getCourseID());
				System.out.println("Course Section Number: " + c.getCourseSecNumber());
				System.out.println("Course Location: " + c.getCourseLocation());
				System.out.println(" ");
			}
		}
	}
	/*
	 * Register on a course (in this case the student must enter the course name, section, and
	student full name, the name will be added to the appropriate course)
	*/
	public void registerCourse(ArrayList<Course> courses) {//Overload?
		BufferedReader sInput = new BufferedReader(new InputStreamReader(System.in));
		try {
			//BufferedReader sInput = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the name of the course you want to register for (case sensitive): ");
			String courseName = sInput.readLine();
			System.out.println("Enter course ID: ");
			String cID = sInput.readLine();
			System.out.println("Enter the unique section number (case sensitive): ");
			int secNum = Integer.parseInt(sInput.readLine());
			System.out.println("Enter your full name with spaces in between: ");
			String name = sInput.readLine();
			for (Course course : courses) {//assume course exists
				if (courseName.equals(course.getCourseName()) && secNum == course.getCourseSecNumber() && cID.equals(course.getCourseID()) && course.getCurrStudentNum() != course.getMaxStudentNum()) {
					enrolledCourses.add(course);
					course.addToNamesOfEnrolledStudents(name);
				}
			}
			//sInput.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * withdraw from a course (in this case the student will be asked to enter her/his student	 
	name and the course, then the name of the student will be taken off from the given course¡¯s
	list)
	*/
	public void withdraw(ArrayList<Course> courses){
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your first and last name: ");
			String name = input.readLine();
			System.out.println("Enter the name of the course you want to drop (case sensitive): ");
			String courseName = input.readLine(); 
			//take out the course from student's enrolled courses?
			Iterator<Course> citer = enrolledCourses.iterator();

			while (citer.hasNext()) {
			    Course sc = citer.next();

			    if (sc.getCourseName().equals(courseName)) {
			        citer.remove();
			}
		
			//proceed to take off name from master course list?
			for(Course c : courses) {
				if (c.getCourseName().equals(courseName)) {
					c.remove(name);
				}
			}
			    /*
			for (int i = 0; i < courses.size(); i++) {
				if (courses.get(i).getCourseName().equals(courseName)) {
					courses.get(i).getListOfStudentNames().remove(courseName);
				}
			}*/
		
			}
			//input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//View all courses that the current student is being registered in
	//can either view using the arraylist of student's enrolled classes or the masterlist of courses but former choice is more efficient?
	public void viewAllEnrolledCourses() {
		viewAllCourses(enrolledCourses);
		/*
		for (int i = 0; i < enrolledCourses.size(); ++i) {
			System.out.println("Course Name: " + enrolledCourses.get(i).getCourseName());
			System.out.println("Course ID: " + enrolledCourses.get(i).getCourseID());
			System.out.println("Course Section Number: " + enrolledCourses.get(i).getCourseSecNumber());
			System.out.println("Course Location: " + enrolledCourses.get(i).getCourseLocation());
			System.out.println(" ");
		}
		*/
	}
	//Exit
	public void exit(){
		System.out.println("Logging off...Thank you for using the app, Student!");
		//return;
	}
}
