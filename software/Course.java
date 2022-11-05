package org.university.software;

import java.io.Serializable;
import java.util.ArrayList;

import org.university.hardware.Department;
import org.university.people.Person;
import org.university.people.Professor;
import org.university.people.Student;

public abstract class Course implements Serializable{

	private String courseName;
	private Integer units;
	private Integer courseNumber;
	private Department department;
	private Professor instructor;
	private ArrayList<Person> studentRoster;
	private ArrayList<Integer> schedule;
	protected Boolean isOnline;

	// Constructor
	public Course() {
		this.studentRoster = new ArrayList<Person>();
		this.instructor = null;
		this.schedule = new ArrayList<Integer>();
		this.isOnline = false;
	}

	// Getters
	public String getName() {
		return this.courseName;
	}

	public Integer getUnits() {
		return this.units;
	}

	public Integer getCourseNumber() {
		return this.courseNumber;
	}

	public Department getDepartment() {
		return this.department;
	}

	public Professor getInstructor() {
		return this.instructor;
	}

	public ArrayList<Person> getStudentRoster() {
		return this.studentRoster;
	}

	public ArrayList<Integer> getSchedule() {
		return this.schedule;
	}
	
	public boolean getIsOnline()
	{
		return this.isOnline;
	}

	// Setters
	public void setName(String courseName) {
		this.courseName = courseName;
	}

	public void setCreditUnits(Integer units) {
		this.units = units;
	}

	public void setCourseNumber(int i) {
		this.courseNumber = i;
	}

	public void addStudentToRoster(Person student) {
		this.studentRoster.add(student);
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
	}
	
	public void setIsOnline(Boolean s)
	{
		this.isOnline = s;
	}

	// Additional Methods
	public boolean isStudentEnrolled(Student student) {

		for (int i = 0; i < this.studentRoster.size(); i++) {
			if (studentRoster.get(i) == student) {
				return true;
			}
		}

		return false;
	}

	public void printSchedule() {
		for (Integer meetingTime : schedule) {
			printTimeSlot(meetingTime);
		}
	}

	public String printTimeSlot(Integer number) {

		// Variables
		String[] daySlot = { "Mon", "Tue", "Wed", "Thu", "Fri" };

		String[] timeSlot = { "8:00am to 9:15am", "9:30am to 10:45am", "11:00am to 12:15pm", "12:30pm to 1:45pm",
				"2:00pm to 3:15pm", "3:30pm to 4:45pm" };

		Integer day = number / 100;
		Integer time = number % 100;

		return (daySlot[day - 1] + " " + timeSlot[time - 1]);

	}

	public void printRoster() {
		for (int i = 0; i < this.studentRoster.size(); i++) {

			System.out.println(this.studentRoster.get(i).getName());

		}
	}

	// Abstract Methods
	public abstract boolean availableTo(Student student);



}