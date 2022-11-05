package org.university.people;

import java.util.ArrayList;
import java.util.Collections;

import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;
import java.io.Serializable;

public class Professor extends Employee implements Serializable
{

	// Variables
	private ArrayList<CampusCourse> campusCourseList;
	private ArrayList<OnlineCourse> onlineCourseList;
	private double salary;

	// Constructors
	public Professor()
	{
		this.campusCourseList = new ArrayList<CampusCourse>();
		this.onlineCourseList = new ArrayList<OnlineCourse>();
	}

	// Getters
	public String getName()
	{
		return super.getName();
	}

	public ArrayList<OnlineCourse> getOnlineCourseList()
	{
		return this.onlineCourseList;
	}

	public ArrayList<CampusCourse> getCampusCourseList()
	{
		return this.campusCourseList;
	}

	public double getSalary()
	{
		return salary;
	}

	// Setters
	public void setName(String name)
	{
		super.setName(name);
	}

	public void setSalary(double salary)
	{
		this.salary = salary;
	}

	public void addCourse(CampusCourse course)
	{

		// test to see if course has professor
		if (course.getInstructor() != null)
		{

			/*
			 * The professor cannot be assigned to this course because professor Kececioglu
			 * is already assigned to the course Enterprise Web Applications.
			 */
			System.out.println("The professor " + this.getName()
					+ " cannot be assigned to this campus course because professor " + course.getInstructor().getName()
					+ " is already assigned to the course " + course.getName() + ".");
			return;
		}

		// check if there is a conflict with the professor's schedule for campusCourses
		if (this.detectConflict(course) == false)
		{
			this.campusCourseList.add(course);
			this.getSchedule().add(course);
			course.setInstructor(this);
		}

	}

	public void addCourse(OnlineCourse course)
	{

		// test to see if course has professor
		if (course.getInstructor() != null)
		{

			/*
			 * The professor cannot be assigned to this course because professor Kececioglu
			 * is already assigned to the course Enterprise Web Applications.
			 */
			System.out.println("The professor cannot be assigned to this online course because professor "
					+ course.getInstructor().getName() + " is already assigned to the online course " + course.getName()
					+ ".");
			return;
		}

		this.onlineCourseList.add(course);
		this.getSchedule().add(course);
		course.setInstructor(this);

	}

	public void printSchedule()
	{

		ArrayList<String> schedule = new ArrayList<String>();

		if (this.getSchedule().isEmpty())
		{
			return;
		}

		for (int i = 0; i < this.onlineCourseList.size(); i++)
		{

			schedule.add("Onl" + this.onlineCourseList.get(i).getDepartment().getDepartmentName() 
					+ this.onlineCourseList.get(i).getCourseNumber() + " "
					+ this.onlineCourseList.get(i).getName());

		}

		for (int i = 0; i < this.campusCourseList.size(); i++)// iterates courses
		{

			for (int j = 0; j < this.campusCourseList.get(i).getSchedule().size(); j++)// iterates course schedules.
			{

				schedule.add(this.campusCourseList.get(i).getSchedule().get(j)
						+ this.campusCourseList.get(i).printTimeSlot(this.getSchedule().get(i).getSchedule().get(j))
						+ " " + this.campusCourseList.get(i).getDepartment().getDepartmentName()
						+ this.campusCourseList.get(i).getCourseNumber() + " "
						+ this.campusCourseList.get(i).getName());

			}

		}

		Collections.sort(schedule);// sort list elements

		for (int i = 0; i < schedule.size(); i++)// print list elements
		{
			System.out.println(schedule.get(i).substring(3));
		}
	}

	public double earns()
	{
		return this.salary / 26;
	}

	public void raise(double percent)
	{
		this.salary = this.salary + ((percent / 100) * this.salary);
	}

}
