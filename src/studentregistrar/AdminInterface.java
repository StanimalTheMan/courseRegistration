package studentregistrar;

import java.util.ArrayList;

public interface AdminInterface {
	public void createCourse(ArrayList<Course>courseCatalog);
	public void deleteCourse(ArrayList<Course> courseCatalog, ArrayList<Student> students, String id, int secNum);
	public void editCourse(ArrayList<Course> courseCatalog, ArrayList<Student> students, String id, int secNum);
	public void displayCourseInfo(ArrayList<Course> courseCatalog, String id);
	public void registerStudent(ArrayList<Student> students);
	public void viewAllCourses(ArrayList<Course> courseCatalog);
	public void viewAllFullCourses(ArrayList<Course> courseCatalog);
	public void writeToFileCoursesFull(ArrayList<Course> courseCatalog);
	public void viewStudentsOfCourse(ArrayList<Course> courseCatalog, String id, int secNum);
	//public void writeToFileClassesFull(ArrayList<Course> courses);
	public void viewAllEnrolledCourses(ArrayList<Student> students, String studentFname, String studentLname);
	public void sortCoursesByCurrNumOfStudents(ArrayList<Course> courseCatalog);
	public int compare(Course c1, Course c2);//overridden compare method 
	public void exit();
}
