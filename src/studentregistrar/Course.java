package studentregistrar;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String id;
	private String instructor;
	private int courseSectionNumber;
	private String courseLocation;
	private ArrayList<String> namesOfEnrolledStudents;//arraylist of string or student?
	private int maxNumOfStudents;
	private int currNumOfStudents;//current number of students in a given course
	
	public Course() {}
	
	public Course(String name, String id, String instructorName, int courseSectionNumber, 
			String courseLocation, ArrayList<String> students, int maxNumOfStudents, int currNumOfStudents) {
		this.name = name;
		this.id = id;
		instructor = instructorName;
		this.courseSectionNumber = courseSectionNumber;
		this.courseLocation = courseLocation;
		this.namesOfEnrolledStudents= students;
		this.maxNumOfStudents = maxNumOfStudents;
		this.currNumOfStudents = currNumOfStudents;
	}
	
	//accessors
	public String getCourseLocation() {
		return courseLocation;
	}
	public ArrayList<String> getListOfStudentNames() {
		return namesOfEnrolledStudents;
	}
	public int getMaxStudentNum() {
		return maxNumOfStudents;
	}
	public int getCourseSecNumber() {
		return courseSectionNumber;
	}
	public int getCurrStudentNum() {
		return currNumOfStudents;
	}
	public String getInstructorName() {
		return instructor;
	}
	public String getCourseName() {
		return name;
	}
	public String getCourseID() {
		return id;
	}
	
	//mutators that enable the admin to edit any course?
	public void setCourseLocation(String location) {
		courseLocation = location;
	}
	public void setMaxStudentNum(int maxNum) {
		maxNumOfStudents = maxNum;
	}
	public void setCourseSecNumber(int secNum) {
		courseSectionNumber = secNum;
	}
	public void setInstructor(String name) {
		instructor = name;
	}
	public void setCourseName(String name) {
		this.name = name;
	}
	public void setCourseID(String id) {
		this.id = id;
	}
	public void addToNamesOfEnrolledStudents(String studentName) {
		namesOfEnrolledStudents = new ArrayList<String>();
		namesOfEnrolledStudents.add(studentName);
		currNumOfStudents++;
	}
	public void remove(String name) {//removes student's name from list of enrolled student names
		Iterator<String> striter = namesOfEnrolledStudents.iterator();
		while (striter.hasNext()) {
			String str = striter.next();//concept of iterators from Stack Overflow etc. 
			
			if(str.equals(name)) {
				striter.remove();
				currNumOfStudents--;
			}
		}
	}
}
