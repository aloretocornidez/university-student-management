package org.university.people;

import java.io.Serializable;
import java.util.*;

import org.university.software.CampusCourse;
import org.university.software.Course;
import org.university.software.OnlineCourse;

public abstract class Person implements Serializable
{

	// Variables
	private String name;
	private ArrayList<Course> schedule;

	// Constructors
	Person()
	{
		this.schedule = new ArrayList<Course>();

	}

	// getters and setters

	public String getName()
	{
		return this.name;
	}

	public ArrayList<Course> getSchedule()
	{
		return this.schedule;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// Methods
	public boolean detectConflict(CampusCourse course)
	{

		boolean flag = false;

		// Checks that the course has a schedule.
		if (course.getSchedule().isEmpty())
		{
			return false;
		}

		// Checks that the person has a schedule.
		else if (this.schedule.isEmpty())
		{
			return false;
		}

		// Checks if the class capacity is reached.
		else if (course.getMaxNumberOfStudents() == course.getStudentRoster().size())
		{
			System.out.println(this.getName() + " can't add Campus Course " + course.getDepartment().getDepartmentName()
					+ course.getCourseNumber() + " " + course.getName()
					+ ". Because this Campus course has enough student.");
			return true;
		}

		else// If both have an existing schedule, then we execute the loop to check for time
			// conflicts.
		{
			// Tests schedule availability for person.
			for (int i = 0; i < course.getSchedule().size(); i++) // gets the size and iterates each time for the class
			{

				for (int j = 0; j < this.schedule.size(); j++) // gets the size of the person's schedule and iterates
																// each course in their schedule.
				{

					if (this.schedule.get(j).getSchedule() != null)// if the schedule does not exist, then the course is
																	// online.
					{

						for (int k = 0; k < this.schedule.get(j).getSchedule().size(); k++) // iterates the times for
																							// the course determined by
																							// the previous loop..
						{

							if (course.getSchedule().get(i).byteValue() == this.schedule.get(j).getSchedule().get(k)
									.byteValue())// this.getShedule().get(j))
							{
								/*
								 * ECE107 course cannot be added to Tharp's Schedule. ECE107 conflicts with
								 * ECE320. Conflicting time slot is Mon 9:30am to 10:45am.
								 */
								System.out.println(course.getDepartment().getDepartmentName() + course.getCourseNumber()
										+ " course cannot be added to " + this.getName() + "'s Schedule. "
										+ course.getDepartment().getDepartmentName() + course.getCourseNumber()
										+ " conflicts with "
										+ this.getSchedule().get(j).getDepartment().getDepartmentName()
										+ this.getSchedule().get(j).getCourseNumber() + ". Conflicting time slot is "
										+ course.printTimeSlot(course.getSchedule().get(i)) + ".");
								flag = true;
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public void printSchedule()
	{

		ArrayList<String> courseList = new ArrayList<String>();

		for (int i = 0; i < this.schedule.size(); i++)// iterates courses
		{

			for (int j = 0; j < this.schedule.get(i).getSchedule().size(); j++)// iterates course schedules.
			{

				courseList.add(this.schedule.get(i).getSchedule().get(j)
						+ this.schedule.get(i).printTimeSlot(this.schedule.get(i).getSchedule().get(j)) + " "
						+ this.schedule.get(i).getDepartment().getDepartmentName()
						+ this.schedule.get(i).getCourseNumber() + " " + this.schedule.get(i).getName());

			}

		}

		
		Collections.sort(courseList);// sort list elements

		for (int i = 0; i < courseList.size(); i++)// print list elements
		{
			System.out.println(courseList.get(i).substring(3));
		}

	}

	// Abstract Methods
	public abstract void addCourse(CampusCourse course);

	public abstract void addCourse(OnlineCourse course);

}
