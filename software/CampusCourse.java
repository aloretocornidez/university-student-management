package org.university.software;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

import org.university.hardware.Classroom;
import org.university.people.Student;

public class CampusCourse extends Course implements Serializable
{

	private Integer maxNumberOfStudents;

	private Classroom classroom;

	// Constructors

	// Getters and Setters
	public Classroom getClassroom()
	{
		return this.classroom;
	}

	public Integer getMaxNumberOfStudents()
	{
		return this.maxNumberOfStudents;
	}

	public ArrayList<Integer> getSchedule()
	{
		return super.getSchedule();
	}

	public void setMaxCourseLimit(Integer limit)
	{
		this.maxNumberOfStudents = limit;
	}

	// Additional Methods
	public boolean availableTo(Student student)
	{

		// Checks if the class capacity has been reached
		if (this.maxNumberOfStudents == this.getStudentRoster().size())
		{
			return false;
		}

		return true;

	}

	public void setSchedule(Integer number)
	{
		this.getSchedule().add(number);
	}

	public void setRoomAssigned(Classroom classroom)

	{

		for (int i = 0; i < classroom.getCourseList().size(); i++)// iterates each course in the class
		{
			for (int j = 0; j < classroom.getCourseList().get(i).getSchedule().size(); j++)// iterates each class time
																							// slot for room
			{
				for (int k = 0; k < this.getSchedule().size(); k++)// iterates this course time slots.
				{
					if (classroom.getCourseList().get(i).getSchedule().get(j) == this.getSchedule().get(k))
					{

						System.out.println(this.getDepartment().getDepartmentName() + this.getCourseNumber()
								+ " conflicts with "
								+ classroom.getCourseList().get(i).getDepartment().getDepartmentName()
								+ classroom.getCourseList().get(i).getCourseNumber() + ". Conflicting time slot "
								+ this.printTimeSlot(classroom.getCourseList().get(i).getSchedule().get(j)) + ". "
								+ this.getDepartment().getDepartmentName() + this.getCourseNumber()
								+ " course cannot be added to " + classroom.getRoomNumber() + "'s Schedule.");
						return;

					}
				}
			}
		}

		this.classroom = classroom;
		classroom.addCourse(this);
		
	}

	public void printSchedule()
	{

		ArrayList<String> sortSchedule = new ArrayList<String>();

		for (int i = 0; i < this.getSchedule().size(); i++)
		{
			sortSchedule.add(this.getSchedule().get(i) + this.printTimeSlot(this.getSchedule().get(i)) + " "
					+ this.getClassroom().getRoomNumber()

			);
		}

		Collections.sort(sortSchedule);

		for (int i = 0; i < sortSchedule.size(); i++)
		{
			System.out.println(sortSchedule.get(i).substring(3));
		}

	}

}
