package org.university.people;

import java.util.ArrayList;
import java.util.Collections;

import org.university.software.CampusCourse;
import org.university.software.OnlineCourse;
import java.io.Serializable;

public class Staff extends Employee implements Serializable
{

	Double payRate;
	Integer hoursWorked;

	public void setPayRate(double payRate)
	{
		this.payRate = payRate;
	}

	public void setMonthlyHours(int i)
	{
		this.hoursWorked = i;
	}

	@Override
	public double earns()
	{
		return hoursWorked * payRate;
	}

	public void raise(double percent)
	{
		payRate = payRate + (payRate * (percent / 100.));

	}

	public void addCourse(CampusCourse course)
	{
		if (this.getSchedule().isEmpty() == false)
		{
			System.out.println(
					// CS387 is removed from Carol's schedule(Staff can only take one class at a
					// time).
					// CS372 has been added to Carol's Schedule.
					this.getSchedule().get(0).getDepartment().getDepartmentName()
							+ this.getSchedule().get(0).getCourseNumber() + " is removed from " + this.getName()
							+ "'s schedule(Staff can only take one class at a time). "
							+ course.getDepartment().getDepartmentName() + course.getCourseNumber()
							+ " has been added to " + this.getName() + "'s Schedule.");

			// Remove course from staff schedule.
			this.getSchedule().remove(0);

			// Remove staff from course
			course.getStudentRoster().remove(this);

		}

		this.getSchedule().add(course);
		course.addStudentToRoster(this);

	}

	public void addCourse(OnlineCourse course)
	{
		if (this.getSchedule().isEmpty() == false)
		{
			System.out.println(
					// CS387 is removed from Carol's schedule(Staff can only take one class at a
					// time).
					// CS372 has been added to Carol's Schedule.
					this.getSchedule().get(0).getDepartment().getDepartmentName()
							+ this.getSchedule().get(0).getCourseNumber() + " is removed from " + this.getName()
							+ "'s schedule(Staff can only take one class at a time). "
							+ course.getDepartment().getDepartmentName() + course.getCourseNumber()
							+ " has been added to " + this.getName() + "'s Schedule.");

			// Remove course from staff schedule.
			this.getSchedule().remove(0);

			// Remove staff from course
			course.getStudentRoster().remove(this);

		}

		this.getSchedule().add(course);
		course.addStudentToRoster(this);

	}

	public void printSchedule()
	{

		ArrayList<String> schedule = new ArrayList<String>();

		if (this.getSchedule().isEmpty())
		{
			return;
		}

		if (this.getSchedule().get(0).getSchedule().isEmpty())
		{
			schedule.add(
					"Onl" + this.getSchedule().get(0).getCourseNumber() + " " + this.getSchedule().get(0).getName());
		} else
		{
			for (int i = 0; i < this.getSchedule().get(0).getSchedule().size(); i++)
			{
				schedule.add(this.getSchedule().get(0).getSchedule().get(i)
						+ this.getSchedule().get(0).printTimeSlot(this.getSchedule().get(0).getSchedule().get(i)) + " "
						+ this.getSchedule().get(0).getDepartment().getDepartmentName()
						+ this.getSchedule().get(0).getCourseNumber() + " " + this.getSchedule().get(0).getName());

			}
		}

		Collections.sort(schedule);// sort list elements

		for (int i = 0; i < schedule.size(); i++)// print list elements
		{
			System.out.println(schedule.get(i).substring(3));
		}

	}

	public Integer getTuitionFee()
	{

		Integer fee = 0;

		// if course is online course
		if (this.getSchedule().get(0).getSchedule().isEmpty())
		{

			if (this.getSchedule().get(0).getUnits() == 3)
			{
				fee = 2000;
			}
			if (this.getSchedule().get(0).getUnits() == 4)
			{
				fee = 3000;
			}

		}

		// if course is in-person
		else
		{
			fee = 300 * this.getSchedule().get(0).getUnits();
		}

		return fee;
	}

}
