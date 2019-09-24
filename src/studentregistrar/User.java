package studentregistrar;

import java.io.*;
import java.util.ArrayList;

public abstract class User implements Serializable {//have abstract methods that student and/or admin has to implement, common methods
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public User() {//meant for admin constructor to call
		username = "Admin";
		password = "Admin001";
	}
	public User(String fname, String lname, String username, String password) {
		firstName = fname;
		lastName = lname;
		this.username = username;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	//view all courses
	public abstract void exit();//overriden
	public abstract void viewAllCourses(ArrayList<Course> courses);//is overriden by both student and admin classes
	public void viewAllEnrolledCourses() {//overloaded
		return;
	}
	//public abstract void register(ArrayList<Object> list);
}
