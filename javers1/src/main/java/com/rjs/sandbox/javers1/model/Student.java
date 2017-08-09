package com.rjs.sandbox.javers1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 8/9/2017.
 *
 * @author Randy Joe
 */
public class Student {
	private String firstName;
	private String lastName;
	private List<Course> courses = new ArrayList<>();

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}
}
