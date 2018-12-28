package com.ngser.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;

/**
 * The persistent class for the activity database table.
 * 
 */
@Entity
@Table(name = "student")
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID studentId;
	
	private String studentFirstName;

	private String studentLastName;

	private Integer studentAge;

	
	
	public Boolean getStudentIsDeleted() {
		return studentIsDeleted;
	}

	public void setStudentIsDeleted(Boolean studentIsDeleted) {
		this.studentIsDeleted = studentIsDeleted;
	}

	private Boolean studentIsDeleted;

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentFirstName=" + studentFirstName + ", studentLastName="
				+ studentLastName + ", studentAge=" + studentAge + ", studentIsDeleted=" + studentIsDeleted + "]";
	}

	
}