package org.university.people;


import java.util.*;

import org.university.software.CampusCourse;
import org.university.software.Course;
import org.university.software.OnlineCourse;
import java.io.Serializable;
import org.university.hardware.Department;

public class Student extends Person implements Serializable
{
	private String name;
	private Department department;
	private Integer requiredCredits;
	private Integer enrolledCredits;
	private Integer completedUnits;
	private ArrayList<CampusCourse> campusCourseList;
	private ArrayList<OnlineCourse> onlineCourseList;
	
	
	
	
	
	//Constructor
	public Student()
	{
		this.campusCourseList = new ArrayList<CampusCourse>();
		this.onlineCourseList = new ArrayList<OnlineCourse>();
		this.requiredCredits = 0;
		this.enrolledCredits = 0;
		this.completedUnits = 0;
	}
	
	
	//getters
	public String getName() {return name;}
	
	public Department getDepartment() {return this.department;}
	
	public ArrayList<CampusCourse> getCourseList() {return this.campusCourseList;}
	
	public Integer getRequiredCredits() {return this.requiredCredits;}
	
	public Integer getCompletedUnits() {return this.completedUnits;}

	public int getCampusCredits()
	{

		if(this.campusCourseList.isEmpty()) {return 0;}//checks that the student is taking campus courses.
		
		int totalCampusCredits = 0;
		
		for(int i = 0; i < this.campusCourseList.size(); i++) {
			totalCampusCredits += this.campusCourseList.get(i).getUnits();
		}
		return totalCampusCredits;
	}


	
	//getters and setters
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setDepartment(Department department) 
	{
		this.department= department;
	}
	
	public void setRequiredCredits(Integer requiredCredits)
	{
		this.requiredCredits = requiredCredits;
	}
	
	public void setCompletedUnits(Integer completedUnits) {
		this.completedUnits = completedUnits;
	}
	
	
	//Additional Methods 
	public void dropCourse(Course course)
	{
		if(course.getIsOnline())
		{
			this.dropCourse((OnlineCourse)course);
		}
		else
		{
			this.dropCourse((CampusCourse)course);
		}
		
	}
	
	
	public void addCourse(Course course)
	{
		if(course.getIsOnline())
		{
			addCourse((OnlineCourse) course);
		}
		else
		{
			addCourse((CampusCourse) course);
		}
	}
	
	public void addCourse(CampusCourse course) 
	{


		//checks if there is a time conflict within the student's schedule.
		if(this.detectConflict(course))
		{
			return;
		}
		
		
		//Checks if the student fits in the class within the capacity.
		if(course.availableTo(this)) {

						
			//Conducting add course operations
			this.campusCourseList.add(course);
			this.getSchedule().add(course);
			course.addStudentToRoster(this);
			this.enrolledCredits += course.getUnits();
			
			

		}
		
		return;
	}

	public void addCourse(OnlineCourse course) {

		if(course.availableTo(this))
		{
			//Conducting adding course operation
			this.onlineCourseList.add(course);
			this.getSchedule().add(course);
			course.addStudentToRoster(this);
			
			//Adding the class units to the student's completed units.
			this.enrolledCredits += course.getUnits();
			

			
		}
		else 
		{
			System.out.println(
					this.getName() + " can't add online Course "
					+ course.getDepartment().getDepartmentName() +  course.getCourseNumber() + " " 
					+ course.getName() + ". Because this student doesn't have enough Campus course credit." 
					);
		}
		
	}

	public void dropCourse(CampusCourse course) 
	{		
		
		//If student is not enrolled in the course, print error else remove them.
		if(course.isStudentEnrolled(this) != true)
		{
			/* Prints: The course ECE373 could not be 
			 * dropped because Lahiru is not enrolled in ECE373.*/
			System.out.println(
					"The course " + course.getDepartment().getDepartmentName() +
					course.getCourseNumber() + " could not be dropped because " 
					+ this.getName() + " is not enrolled in " 
					+ course.getDepartment().getDepartmentName() 
					+ course.getCourseNumber() + ".");
			return;
		}
		
		
		//checks if the student is taking online courses.
		if(this.onlineCourseList.isEmpty() == false) {

			//makes sure that the student will not lose eligibility for online courses if this campus course is removed.
			if(this.getCampusCredits() - course.getUnits() < 6) {
				
				System.out.println(
						this.name + " can't drop this CampusCourse, because this student "
								+ "doesn't have enough campus course credit to hold the "
								+ "online course"
						);
				return;
			}
			
		}
	
		course.getStudentRoster().remove(this);
		this.getSchedule().remove(course);
		this.campusCourseList.remove(course);
		this.enrolledCredits -= course.getUnits();
		
		
		
		
		
	}
	
	public void dropCourse(OnlineCourse course) 
	{
		
		
		if(course.isStudentEnrolled(this) != true)
		{
			/* Prints: The course ECE373 could not be 
			 * dropped because Lahiru is not enrolled in ECE373.*/
			System.out.println(
					"The course " + course.getDepartment().getDepartmentName() +
					course.getCourseNumber() + " could not be dropped because " 
					+ this.getName() + " is not enrolled in " 
					+ course.getDepartment().getDepartmentName() 
					+ course.getCourseNumber() + ".");
			return;
		}
		
		
		this.onlineCourseList.remove(course);
		this.getSchedule().remove(course);
		course.getStudentRoster().remove(this);
		this.enrolledCredits -= course.getUnits();
	
		
		
	}
	
	public void printSchedule()
	{
		ArrayList<String> schedule = new ArrayList<String>();//new list to contain schedule
		
		
		
		/*
		 *Adds the elements in the schedule into one list array to be sorted. 
		 * 
		 */
		for(int i = 0; i < this.onlineCourseList.size(); i++)
		{
		
			schedule.add(
					"Onl" 
					+ this.onlineCourseList.get(i).getDepartment().getDepartmentName()
					+ this.onlineCourseList.get(i).getCourseNumber() + " "
					+ this.onlineCourseList.get(i).getName()
					);
			
		}	
		
		
		for(int i = 0; i < this.campusCourseList.size(); i++)//iterates courses
		{

			for(int j = 0; j < this.campusCourseList.get(i).getSchedule().size(); j++)//iterates course schedules.
			{
			
				schedule.add(
							this.campusCourseList.get(i).getSchedule().get(j)
							+ this.campusCourseList.get(i)
							.printTimeSlot(
									this.campusCourseList
									.get(i)
									.getSchedule()
									.get(j)) 
							+ " "
							+ this.campusCourseList.get(i).getDepartment().getDepartmentName()
							+ this.campusCourseList.get(i).getCourseNumber() + " "
							+ this.campusCourseList.get(i).getName()
				);

			}
	
		}
		
		Collections.sort(schedule);//sort list elements
		

		for(int i = 0; i < schedule.size(); i++)//print list elements
		{
			System.out.println(schedule.get(i).substring(3));
		}
	}
	
	public Integer requiredToGraduate() { return this.requiredCredits - this.enrolledCredits - this.completedUnits; }
	


	public Integer getTuitionFee() {
		
		Integer fee = 0;
		
		
		//online courses
		for(int i = 0; i <this.onlineCourseList.size(); i++)
		{
				
				if(this.onlineCourseList.get(i).getUnits() == 3)
				{
					fee += 2000;
				}
				if(this.onlineCourseList.get(i).getUnits() == 4)
				{
					fee += 3000;
				}
				
		}
		
		
		//in person courses
		for(int i = 0 ; i < this.campusCourseList.size(); i++)
		{
			fee += 300 * this.getSchedule().get(i).getUnits();
		}
		
		
		
		
		return fee;
	}



	
	
}
