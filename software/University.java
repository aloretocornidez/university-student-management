package org.university.software;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.university.hardware.Classroom;
import org.university.hardware.Department;
import org.university.people.Staff;

public class University implements Serializable
{
	protected ArrayList<Department> departmentList;
	protected ArrayList<Classroom> classroomList;
	ArrayList<Staff> staffList;

	public University()
	{
		this.departmentList = new ArrayList<Department>();
		this.classroomList = new ArrayList<Classroom>();
		this.staffList = new ArrayList<Staff>();
	}

	// getters and setters

	public ArrayList<Staff> getStaff()
	{
		return this.staffList;
	}

	// Methods
	public void printDepartmentList()
	{

		for (int i = 0; i < departmentList.size(); i++)
		{
			System.out.println((departmentList.get(i).getDepartmentName()));
		}

	}

	public void printCourseList()
	{
		for (int i = 0; i < departmentList.size(); i++)
		{
			for (int j = 0; j < departmentList.get(i).getCourseList().size(); j++)
			{
				System.out.println(departmentList.get(i).getDepartmentName()
						+ departmentList.get(i).getCourseList().get(j).getCourseNumber() + " "
						+ departmentList.get(i).getCourseList().get(j).getName()

				);
			}

		}

	}

	public void printStudentList()
	{

		for (int i = 0; i < departmentList.size(); i++)
		{

			for (int j = 0; j < departmentList.get(i).getStudentList().size(); j++)
			{

				System.out.println(departmentList.get(i).getStudentList().get(j).getName());
			}

		}

	}

	public void printProfessorList()
	{
		// iterates through every department
		for (int i = 0; i < departmentList.size(); i++)
		{
			for (int j = 0; j < departmentList.get(i).getProfessorList().size(); j++)
			{
				System.out.println(departmentList.get(i).getProfessorList().get(j).getName());

			}
		}
	}

	public void printStaffList()
	{
		for (int i = 0; i < this.departmentList.size(); i++)
		{
			for (int j = 0; j < departmentList.get(i).getStaffList().size(); j++)
			{
				System.out.println(departmentList.get(i).getStaffList().get(j).getName());

			}
		}
	}

	public void printClassroomList()
	{
		for (Classroom classroom : this.classroomList)
		{
			System.out.println(classroom.getRoomNumber());
		}
	}

	public void printAll()
	{

		/*
		 * Printing Department list.
		 */
		System.out.println("\nDepartment list:");
		this.printDepartmentList();

		/*
		 * Printing Classroom List.
		 */
		System.out.println("\nClassroom list:");
		this.printClassroomList();

		/*
		 * Printing every professor in a department.
		 */
		System.out.println();
		// iterates through the departments in the university.
		for (int i = 0; i < this.departmentList.size(); i++)
		{
			System.out.println("The professor list for department " + this.departmentList.get(i).getDepartmentName());
			this.departmentList.get(i).printProfessorList();
			System.out.println();
		}

		/*
		 * Printing the course list for every department
		 */
		for (int i = 0; i < this.departmentList.size(); i++)
		{
			System.out.println("The course list for department " + this.departmentList.get(i).getDepartmentName());
			this.departmentList.get(i).printCampusCourseList();
			this.departmentList.get(i).printOnlineCourseList();
			System.out.println();
		}

		/*
		 * Printing the schedule for every classroom
		 */
		for (int i = 0; i < this.classroomList.size(); i++)
		{
			System.out.println("The schedule for classroom " + this.classroomList.get(i).getRoomNumber());
			this.classroomList.get(i).printSchedule();
			System.out.println();
		}

		/*
		 * Going by department and Printing professor schedules Printing Staff Schedules
		 * Printing student schedules
		 */

		for (int i = 0; i < this.departmentList.size(); i++)
		{

			// printing department name
			System.out.println("Department " + this.departmentList.get(i).getDepartmentName());

			/*
			 * Professor Schedules
			 */
			System.out.println("\nPrinting professor schedules:\n");

			for (int j = 0; j < this.departmentList.get(i).getProfessorList().size(); j++)
			{

				System.out.println("The schedule for Prof. "
						+ this.departmentList.get(i).getProfessorList().get(j).getName() + ":");
				this.departmentList.get(i).getProfessorList().get(j).printSchedule();
				System.out.println();
			}

			/*
			 * Student schedules
			 */
			System.out.println("Printing student schedules:");
			System.out.println();
			for (int j = 0; j < this.departmentList.get(i).getStudentList().size(); j++)
			{
				System.out.println("The schedule for student "
						+ this.departmentList.get(i).getStudentList().get(j).getName() + ":");
				this.departmentList.get(i).getStudentList().get(j).printSchedule();
				System.out.println();
			}

			/*
			 * Staff schedules
			 */
			System.out.println("Printing staff schedules:\n");
			for (int j = 0; j < this.departmentList.get(i).getStaffList().size(); j++)
			{
				System.out.println("The schedule for employee "
						+ this.departmentList.get(i).getStaffList().get(j).getName() + ":");

				this.departmentList.get(i).getStaffList().get(j).printSchedule();

				System.out.println();

				System.out.println("Staff: " + this.departmentList.get(i).getStaffList().get(j).getName() + " earns "
						+ this.departmentList.get(i).getStaffList().get(j).earns() + " this month");

			}

			/*
			 * The rosters offered by the department.
			 */
			System.out.println();
			System.out.println("The rosters for courses offered by " + this.departmentList.get(i).getDepartmentName());

			System.out.println();

			for (int j = 0; j < this.departmentList.get(i).getCampusCourseList().size(); j++)
			{

				if (this.departmentList.get(i).getCampusCourseList().get(j).getIsOnline() == false)
				{
					System.out.println("The roster for course " + this.departmentList.get(i).getDepartmentName()
							+ this.departmentList.get(i).getCampusCourseList().get(j).getCourseNumber());

					// Print the class roster.
					departmentList.get(i).getCampusCourseList().get(j).printRoster();

				}
				System.out.println();
			}
		}

	}

	public static void saveData(University u)
	{

		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;

		try
		{
			fileOut = new FileOutputStream("University.ser"); 
																
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(u);
			objOut.close();
			fileOut.close();
		}

		catch (IOException i)
		{
			i.printStackTrace();
		}
	}

	public static University loadData()
	{
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		University university = null;

		try
		{
			fileIn = new FileInputStream("University.ser");
			objIn = new ObjectInputStream(fileIn);
			university = (University) objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (IOException i)
		{
			i.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return university;
	}

	
	/*
	 * Methods used to interface with the GUI
	 */
	
	public boolean studentExists(String name)
	{
		
		//iterate through all departments
		for(int i = 0; i < this.departmentList.size(); i++)
		{
			//iterate through all of the students in the departments.
			for(int j = 0; j < this.departmentList.get(i).getStudentList().size(); j++)
			{
				if(this.departmentList.get(i).getStudentList().get(j).getName().equals(name))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean departmentExists(String name)
	{
		
		for(int i = 0; i < this.departmentList.size(); i++)
		{
			
			if(this.departmentList.get(i).getDepartmentName().equals(name))
			{
				return true;
			}
			
		}
		
		return false;
	}

	public boolean courseExists(Integer departmentNum, Integer courseNumber)
	{

		Department department = this.departmentList.get(departmentNum);
		
		for(int i = 0; i < department.getCourseList().size(); i++)
		{
			if(department.getCourseList().get(i).getCourseNumber().equals(courseNumber))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Integer getDepartmentNumber(String s)
	{

		for(int i = 0; i < this.departmentList.size(); i++)
		{
			if(this.departmentList.get(i).getDepartmentName().equals(s))
			{
				return i;
			}
		}
		

		System.out.println(s + " department does not exist.");
		return null;
	}

	public Integer getStudentIndex(String studentName)
	{
		//iterate the departments
		for(int i = 0; i < this.departmentList.size(); i++)
		{
		
			for(int j = 0; j < this.departmentList.get(i).getStudentList().size(); j++)
			{
				if(this.departmentList.get(i).getStudentList().get(j).getName().equals(studentName))
				{
					return j;
				}
			}
		}
		
		return null;
	}

	public Integer getCourseIndex(Integer courseDepartmentIndex, Integer courseNumber)
	{
		//Send in the index of the department, and a course number
		//If the course number is found in the sent department, return the index of that course.
		
		for(int i = 0; i < this.departmentList.get(courseDepartmentIndex).getCourseList().size(); i++)
		{
			if(this.departmentList.get(courseDepartmentIndex).getCourseList().get(i).getCourseNumber().equals(courseNumber))
			{
				return i;
			}
		}
		
		return null;
	}

	public Integer getStudentDepartmentIndex(String studentName)
	{
		//iterate the departments
		for(int i = 0; i < this.departmentList.size(); i++)
		{
		
			for(int j = 0; j < this.departmentList.get(i).getStudentList().size(); j++)
			{
				if(this.departmentList.get(i).getStudentList().get(j).getName().equals(studentName))
				{
					return i;
				}
			}
		}
		
		return null;
	}

	public Classroom getClassroom(String roomNumber)
	{
		for(int i = 0 ; i < this.classroomList.size(); i++)
		{
			if(this.classroomList.get(i).getRoomNumber().equals(roomNumber))
			{
				return this.classroomList.get(i);
			}
		}
		
		
		
		return null;
	}
	
	
}
