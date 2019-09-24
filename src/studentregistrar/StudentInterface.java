package studentregistrar;

import java.util.ArrayList;

public interface StudentInterface {//do i not need user interface too?
	public void viewAllCourses(ArrayList<Course> courseCatalog);
	public void viewAllOpenCourses(ArrayList<Course> courseCatalog);//WHAT ARE THE PARAMETERS?	
	public void registerCourse(ArrayList<Course> courseCatalog);
	public void withdraw(ArrayList<Course> courseCatalog);
	public void viewAllEnrolledCourses();
	public void exit();
}
