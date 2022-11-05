package org.university.hardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.university.software.CampusCourse;
import org.university.software.Course;

public class Classroom implements Serializable
{

	// Variables
	private String roomNumber;
	private ArrayList<CampusCourse> courseList;

	// Constructor
	public Classroom()
	{
		this.courseList = new ArrayList<CampusCourse>();
	}

	// Getters
	public String getRoomNumber()
	{
		return this.roomNumber;
	}

	public ArrayList<CampusCourse> getCourseList()
	{
		return this.courseList;
	}

	// Setters and Adders
	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}

	public void addCourse(CampusCourse course)
	{
		this.courseList.add(course);
	}

	// Additional Methods
	public void printSchedule()
	{

		ArrayList<String> schedule = new ArrayList<String>();

		for (int i = 0; i < this.courseList.size(); i++)// iterates courses
		{

			for (int j = 0; j < this.courseList.get(i).getSchedule().size(); j++)// iterates course schedules.
			{

				schedule.add(this.courseList.get(i).getSchedule().get(j)
						+ this.courseList.get(i).printTimeSlot(this.courseList.get(i).getSchedule().get(j)) + " "
						+ this.courseList.get(i).getDepartment().getDepartmentName()
						+ this.courseList.get(i).getCourseNumber() + " " + this.courseList.get(i).getName());

			}

		}

		Collections.sort(schedule);

		for (int i = 0; i < schedule.size(); i++)
		{
			System.out.println(schedule.get(i).substring(3));
		}

	}

	public Course isOccupied(CampusCourse course)// checks to see if another course occupies the room at the same time.
	{
		// TODO could possibly be made more efficient by referencing this schedule
		// instead of looking at the courses.
		for (int i = 0; i < this.courseList.size(); i++)// iterates this room's courses
		{

			for (int j = 0; j < this.courseList.get(i).getSchedule().size(); j++)// iterates this rooms schedules
			{

				for (int k = 0; k < course.getSchedule().size(); k++)// iterates this classroom schedule
				{

					if (this.courseList.get(i).getSchedule().get(j) == course.getSchedule().get(k))
					{
						return courseList.get(i);
					}

				}

			}

		}

		return null;
	}

}
