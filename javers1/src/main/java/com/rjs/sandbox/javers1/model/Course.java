package com.rjs.sandbox.javers1.model;

import org.javers.core.metamodel.annotation.Id;

/**
 * Created on 8/9/2017.
 *
 * @author Randy Joe
 */
public class Course {
	@Id
	private String number;
	private String professor;

	public Course(String number, String professor) {
		this.number = number;
		this.professor = professor;
	}

	public String getNumber() {
		return number;
	}

	public String getProfessor() {
		return professor;
	}
}
