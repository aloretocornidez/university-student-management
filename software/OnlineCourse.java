package org.university.software;

import java.io.Serializable;

import org.university.people.Student;

public class OnlineCourse extends Course implements Serializable{

	OnlineCourse()
	{
		this.isOnline = true;
	}
	public void setCreditUnits(int i) {
		super.setCreditUnits(i);
	}

	// Checks that the student is taking at least 6 campus credit hours
	// returns true if the student meets the requirements to take an online course.
	public boolean availableTo(Student student) {
		if (student.getCampusCredits() >= 6) // check that the student is taking at least 6 campus credits.
		{
			return true;

		}

		else {
			System.out.println("Student " + student.getName() + " has only " + student.getCampusCredits()
					+ " on campus credits enrolled. Should have at least 6 credits registered "
					+ "before registering online courses.");
			return false;
		}
	}

	public void setSchedule(int time) {
		System.out.println(this.getName() + " is an online class, it cannot be added to a schedule");
	}

}
