package org.university.hardware;

import java.util.ArrayList;

import org.university.people.Professor;
import org.university.people.Staff;
import org.university.people.Student;
import org.university.software.CampusCourse;
import org.university.software.Course;
import org.university.software.OnlineCourse;
import java.io.Serializable;

public class Department implements Serializable
{
	private String departmentName;
	private ArrayList<Student> studentList;
	private ArrayList<Course> courseList;
	private ArrayList<Professor> professorList;
	private ArrayList<Staff> staffList;
	private ArrayList<CampusCourse> campusCourseList;
	private ArrayList<OnlineCourse> onlineCourseList;

	public Department()
	{
		this.courseList = new ArrayList<Course>();
		this.studentList = new ArrayList<Student>();
		this.professorList = new ArrayList<Professor>();
		this.staffList = new ArrayList<Staff>();
		this.campusCourseList = new ArrayList<CampusCourse>();
		this.onlineCourseList = new ArrayList<OnlineCourse>();
	}

	// getters
	public String getDepartmentName()
	{
		return this.departmentName;
	}

	public ArrayList<Student> getStudentList()
	{
		return this.studentList;
	}

	public ArrayList<Course> getCourseList()
	{
		return this.courseList;
	}

	public ArrayList<CampusCourse> getCampusCourseList()
	{
		return this.campusCourseList;
	}

	public ArrayList<OnlineCourse> getOnlineCourseList()
	{
		return this.onlineCourseList;
	}

	public ArrayList<Professor> getProfessorList()
	{
		return this.professorList;
	}

	public ArrayList<Staff> getStaffList()
	{
		return this.staffList;
	}

	// setters
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public void addStudent(Student student)
	{
		this.studentList.add(student);
		student.setDepartment(this);
	}

	public void addCourse(Course course)
	{
		this.courseList.add(course);
		course.setDepartment(this);
	}

	public void addCourse(CampusCourse course)
	{
		this.campusCourseList.add(course);
		this.courseList.add(course);
		course.setDepartment(this);
	}

	public void addCourse(OnlineCourse course)
	{
		this.onlineCourseList.add(course);
		this.courseList.add(course);
		course.setDepartment(this);
		}

	public void addProfessor(Professor professor)
	{
		this.professorList.add(professor);
		professor.setDepartment(this);
	}

	public void printStudentList()
	{
		for (int i = 0; i < this.studentList.size(); i++)
		{
			System.out.println(this.studentList.get(i).getName());
		}
	}

	public void printProfessorList()
	{
		for (int i = 0; i < this.professorList.size(); i++)
		{
			System.out.println(this.professorList.get(i).getName());
		}
	}

	public void printCourseList()
	{
		for (int i = 0; i < this.courseList.size(); i++)
		{
			System.out.println(this.getDepartmentName() + this.courseList.get(i).getCourseNumber() + " "
					+ this.courseList.get(i).getName());
		}
	}

	public void addStaff(Staff staff)
	{
		this.staffList.add(staff);
		staff.setDepartment(this);
	}

	public void printCampusCourseList()
	{
		for (int i = 0; i < this.campusCourseList.size(); i++)
		{
			System.out.println(this.getDepartmentName() + this.campusCourseList.get(i).getCourseNumber() + " "
					+ this.campusCourseList.get(i).getName());
		}

	}

	public void printOnlineCourseList()
	{
		for (int i = 0; i < this.onlineCourseList.size(); i++)
		{
			System.out.println(this.getDepartmentName() + this.onlineCourseList.get(i).getCourseNumber() + " "
					+ this.onlineCourseList.get(i).getName());
		}

	}

}
