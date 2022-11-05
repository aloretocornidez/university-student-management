package org.university.software;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

import org.university.people.*;
import org.university.hardware.*;
import org.university.software.*;

import java.awt.Container;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.*;

public class UniversityGUI extends JFrame
{

	final int WINDOW_HEIGHT = 480;
	final int WINDOW_WIDTH = 720;

	// University Variable
	University university;

	// Top Menu variable
	JMenuBar menu = new JMenuBar();

	/**
	 * Student Menu Variables
	 */
	JMenu studentMenu = new JMenu("Students");
	JMenuItem addCourse = new JMenuItem("Add Course");
	JMenuItem dropCourse = new JMenuItem("Drop Course");
	JMenuItem printSchedule = new JMenuItem("Print Schedule");

	/**
	 * End Student Menu Variables
	 */

	/**
	 * File Menu Variables
	 */

	JMenu fileMenu = new JMenu("File");

	// Items inside the file.
	JMenuItem save = new JMenuItem("Save");
	JMenuItem load = new JMenuItem("Load");
	JMenuItem exit = new JMenuItem("Exit");

	/**
	 * End File Menu Variables
	 */

	/*
	 * Administrator Menu Variables
	 */
	JMenu administratorMenu = new JMenu("Administrators");
	JMenuItem printAllInfo = new JMenuItem("Print All Info");
	JMenuItem newCampusCourse = new JMenuItem("New Campus Course");
	JMenuItem newOnlineCourse = new JMenuItem("New Online Course");
	JMenuItem assignCourseClassroom = new JMenuItem("Assign Course Classroom");

	/**
	 * End Administrator Menu Variables
	 */

	/**
	 * Constructor Constructor Constructor Constructor Constructor Constructor
	 */
	UniversityGUI(String s, University u)
	{
		super(s);
		this.university = u;
		// Sets the window title.

		// Sets window spawn size.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// Specifies operation on close of window.
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Sets the starting position of the window in the center.
		setLocationRelativeTo(null);

		/**
		 * Begin building the window
		 */
		// Menu
		// Set the main bar at the top to the menu bar.
		setJMenuBar(menu);

		/**
		 * Begin File Menu
		 */
		// Add the save an load buttons to the file.
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(exit);

		save.addActionListener(new MenuListener());
		load.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());

		// Add the "file" box to the top menu.
		menu.add(fileMenu);
		/**
		 * End File Menu
		 */

		/**
		 * Begin Student Menu
		 */
		// Add the menu items to the student menu
		studentMenu.add(addCourse);
		studentMenu.add(dropCourse);
		studentMenu.add(printSchedule);

		// Add a listener to the button to allow an action to occur.
		addCourse.addActionListener(new MenuListener());
		dropCourse.addActionListener(new MenuListener());
		printSchedule.addActionListener(new MenuListener());

		// Add the student menu to the top bar.
		menu.add(studentMenu);

		/**
		 * End Student Menu
		 */

		/**
		 * Begin Administrator Menu
		 */
		administratorMenu.add(printAllInfo);
		administratorMenu.add(newCampusCourse);
		administratorMenu.add(newOnlineCourse);
		administratorMenu.add(assignCourseClassroom);

		printAllInfo.addActionListener(new MenuListener());
		newCampusCourse.addActionListener(new MenuListener());
		newOnlineCourse.addActionListener(new MenuListener());
		assignCourseClassroom.addActionListener(new MenuListener());

		// Add the administrator menu to the top bar.
		menu.add(administratorMenu);
		/**
		 * End Administrator Menu
		 */

		setVisible(true);
	}

	private class MenuListener implements ActionListener
	{

		// Save old output (console)
		PrintStream old = System.out;

		// Create a bytearray output so that the system prints to the scrollbar window.
		ByteArrayOutputStream printArray = new ByteArrayOutputStream();

		public void actionPerformed(ActionEvent e)
		{
			JMenuItem source = (JMenuItem) e.getSource();

			if (source.equals(save))
			{
				handleSave();

			} else if (source.equals(load))
			{
				handleLoad();

			} else if (source.equals(exit))
			{
				handleExit();

			} else if (source.equals(addCourse))
			{
				handleAddCourse();

			} else if (source.equals(dropCourse))
			{
				handleDropCourse();

			} else if (source.equals(printSchedule))
			{
				handlePrintSchedule();

			} else if (source.equals(addCourse))
			{
				handleAddCourse();

			} else if (source.equals(printAllInfo))
			{
				handlePrintAllInfo();

			} else if (source.equals(newCampusCourse))
			{
				handleNewCampusCourse();

			} else if (source.equals(newOnlineCourse))
			{
				handleNewOnlineCourse();

			} else if (source.equals(assignCourseClassroom))
			{
				handleAssignCourseClassroom();

			}

		}

		private void handleSave()
		{
			University.saveData(university);
			System.out.println("Saved University");
		}

		private void handleLoad()
		{
			university = University.loadData();
		}

		private void handleExit()
		{
			System.exit(0);
		}

		private void handleAddCourse()
		{
			JFrame temp = new JFrame("Add Course");

			JLabel studentNameLabel = new JLabel("Student Name:");
			JLabel departmentLabel = new JLabel("Department:");
			JLabel courseNumberLabel = new JLabel("Course #:");

			JTextField studentNameJ = new JTextField();
			JTextField departmentJ = new JTextField();
			JTextField courseNumberJ = new JTextField();

			Integer departmentNumber = null;// A number used to get to the index of the department inside the university
											// arraylist

			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ studentNameLabel, studentNameJ, departmentLabel, departmentJ, courseNumberLabel, courseNumberJ },
					"Add Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			String studentName = studentNameJ.getText();
			System.out.println("Student Name: " + studentName);

			String departmentName = departmentJ.getText();
			System.out.println("Departent: " + departmentName);

			Integer courseNumber = Integer.parseInt(courseNumberJ.getText());
			System.out.println("Course Number: " + courseNumber);

			boolean studentExists = university.studentExists(studentName);
			boolean departmentExists = university.departmentExists(departmentName);
			boolean courseExists = false;

			if (studentExists == false)
			{
				printMessage(studentName + " does not exist");
			}

			if (departmentExists)
			{

				// Get the index of the array list for the department.
				departmentNumber = university.getDepartmentNumber(departmentName);

				// test if the course exists inside the apartment
				courseExists = university.courseExists(departmentNumber, courseNumber);

			} else
			{
				printMessage(departmentName + " does not exist");
			}

			if (courseExists == false)
			{
				printMessage("Course number: " + courseNumber + " does not exist within your entered department.");
			}

			if (studentExists && departmentExists && courseExists)
			{
				/*
				 * Get the student
				 */
				Integer studentIndex = university.getStudentIndex(studentName);
				Integer studentDepartmentIndex = university.getStudentDepartmentIndex(studentName);

				Integer courseDepartmentIndex = university.getDepartmentNumber(departmentName);
				Integer courseIndex = university.getCourseIndex(courseDepartmentIndex, courseNumber);
				/*
				 * Add the course
				 */

				// Sets the student that was entered
				Student student = university.departmentList.get(studentDepartmentIndex).getStudentList()
						.get(studentIndex);

				Course course = university.departmentList.get(courseDepartmentIndex).getCourseList().get(courseIndex);
				// this addresses the student

				this.setOutputToErrorWindow();

				student.addCourse(course);

				if (printArray.toString().equals(""))
				{
					if (course.getIsOnline())
					{

						System.out.println(studentName + " has added the online course "
								+ course.getDepartment().getDepartmentName() + course.getCourseNumber() + " "
								+ course.getName() + " successfully.");

					}

					else
					{
						System.out.println(

								studentName + " has added the campus course: "
										+ course.getDepartment().getDepartmentName() + course.getCourseNumber() + " "
										+ course.getName() + " successfully.");
					}
				}
				this.setOutputToErrorConsole();

			}

		}

		private void handleDropCourse()
		{
			JFrame temp = new JFrame("Drop Course");

			JLabel studentNameLabel = new JLabel("Student Name:");
			JLabel departmentLabel = new JLabel("Department:");
			JLabel courseNumberLabel = new JLabel("Course #:");

			JTextField studentNameJ = new JTextField();
			JTextField departmentJ = new JTextField();
			JTextField courseNumberJ = new JTextField();

			Integer departmentNumber = null;// A number used to get to the index of the department inside the university
											// arraylist

			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ studentNameLabel, studentNameJ, departmentLabel, departmentJ, courseNumberLabel, courseNumberJ },
					"Drop Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			String studentName = studentNameJ.getText();
			System.out.println("Student Name: " + studentName);

			String departmentName = departmentJ.getText();
			System.out.println("Departent: " + departmentName);

			Integer courseNumber = Integer.parseInt(courseNumberJ.getText());
			System.out.println("Course Number: " + courseNumber);

			boolean studentExists = university.studentExists(studentName);
			boolean departmentExists = university.departmentExists(departmentName);
			boolean courseExists = false;

			if (studentExists == false)
			{
				printMessage(studentName + " does not exist");
			}

			if (departmentExists)
			{

				// Get the index of the array list for the department.
				departmentNumber = university.getDepartmentNumber(departmentName);

				// test if the course exists inside the apartment
				courseExists = university.courseExists(departmentNumber, courseNumber);

			} else
			{
				printMessage(departmentName + " does not exist");
			}

			if (courseExists == false)
			{
				printMessage("Course number: " + courseNumber + " does not exist within your entered department.");
			}

			if (studentExists && departmentExists && courseExists)
			{
				/*
				 * Get the student
				 */
				Integer studentIndex = university.getStudentIndex(studentName);
				Integer studentDepartmentIndex = university.getStudentDepartmentIndex(studentName);

				Integer courseDepartmentIndex = university.getDepartmentNumber(departmentName);
				Integer courseIndex = university.getCourseIndex(courseDepartmentIndex, courseNumber);
				/*
				 * Drop the course
				 */

				// Sets the student that was entered
				Student student = university.departmentList.get(studentDepartmentIndex).getStudentList()
						.get(studentIndex);

				Course course = university.departmentList.get(courseDepartmentIndex).getCourseList().get(courseIndex);
				// this addresses the student

				this.setOutputToErrorWindow();

				student.dropCourse(course);
				if (printArray.toString().equals(""))
				{
					System.out.println("The course " + course.getDepartment().getDepartmentName()
							+ course.getCourseNumber() + " has been successfully dropped by " + studentName + ".");
				}
				this.setOutputToErrorConsole();
			}

		}

		private void handlePrintSchedule()
		{

			JFrame temp = new JFrame("Print Schedule");

			JLabel studentNameLabel = new JLabel("Student Name:");

			JTextField studentNameJ = new JTextField();

			// Integer departmentNumber = null;// A number used to get to the index of the
			// department inside the university
			// arraylist

			@SuppressWarnings("unused")
			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ studentNameLabel, studentNameJ }, "Print Schedule", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);

			String studentName = studentNameJ.getText();
			System.out.println("Student Name: " + studentName);

			boolean studentExists = university.studentExists(studentName);

			if (studentExists == false)
			{
				printMessage(studentName + " is not an enrolled student.");
			} else if (studentExists)
			{
				/*
				 * Get the student
				 */
				Integer studentIndex = university.getStudentIndex(studentName);
				Integer studentDepartmentIndex = university.getStudentDepartmentIndex(studentName);

				/*
				 * Drop the course
				 */

				// Sets the student that was entered
				Student student = university.departmentList.get(studentDepartmentIndex).getStudentList()
						.get(studentIndex);

				// this addresses the student

				this.setOutputToErrorWindow();

				student.printSchedule();

				this.setOutputToErrorConsole();
			}

		}

		private void handlePrintAllInfo()
		{

			this.setOutputToScrollBarWindow();

			// Run the print function.
			university.printAll();

			this.setOutputToScrollBarConsole("scrollBars");

		}

		private void handleNewCampusCourse()
		{

			JFrame temp = new JFrame("New Campus Course Course");

			JLabel courseNameLabel = new JLabel("Course Name:");
			JLabel departmentLabel = new JLabel("Department:");
			JLabel courseNumberLabel = new JLabel("Course #:");
			JLabel maxStudentsLabel = new JLabel("Max Students:");
			JLabel courseCreditLabel = new JLabel("Course Credit:");

			JTextField courseNameJ = new JTextField();
			JTextField departmentJ = new JTextField();
			JTextField courseNumberJ = new JTextField();
			JTextField maxStudentsJ = new JTextField();
			JTextField courseCreditJ = new JTextField();

			@SuppressWarnings("unused")
			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ courseNameLabel, courseNameJ, departmentLabel, departmentJ, courseNumberLabel, courseNumberJ,
					maxStudentsLabel, maxStudentsJ, courseCreditLabel, courseCreditJ }, "New Campus Course",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			String courseName = courseNameJ.getText();
			System.out.println("Student Name: " + courseName);

			String department = departmentJ.getText();
			System.out.println("Department Name: " + department);

			String courseNumber = courseNumberJ.getText();
			System.out.println("Course Number Name: " + courseNumber);

			String maxStudents = maxStudentsJ.getText();
			System.out.println("Max Students Name: " + maxStudents);

			String courseCredit = courseCreditJ.getText();
			System.out.println("Course Credit Name: " + courseCredit);

			/*
			 * Check that department exists.
			 */
			if (university.departmentExists(department))
			{
				Department courseDepartment = university.departmentList.get(university.getDepartmentNumber(department));

				CampusCourse newCourse = new CampusCourse();
				newCourse.setName(courseName);
				newCourse.setDepartment(courseDepartment);
				newCourse.setCourseNumber(Integer.parseInt(courseNumber));
				newCourse.setMaxCourseLimit(Integer.parseInt(maxStudents));

				this.setOutputToErrorWindow();
				courseDepartment.addCourse(newCourse);
				System.out.println(courseName + " has been sucessfully created.");

				this.setOutputToErrorConsole();
			} else
			{
				printMessage("Department: " + department + " does not exist.");
			}

		}

		private void handleNewOnlineCourse()
		{
			JFrame temp = new JFrame("New Online Course Course");

			JLabel courseNameLabel = new JLabel("Course Name:");
			JLabel departmentLabel = new JLabel("Department:");
			JLabel courseNumberLabel = new JLabel("Course #:");
			JLabel courseCreditLabel = new JLabel("Course Credit:");

			JTextField courseNameJ = new JTextField();
			JTextField departmentJ = new JTextField();
			JTextField courseNumberJ = new JTextField();
			JTextField courseCreditJ = new JTextField();

			@SuppressWarnings("unused")
			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ courseNameLabel, courseNameJ, departmentLabel, departmentJ, courseNumberLabel, courseNumberJ,
					courseCreditLabel, courseCreditJ }, "New Online Course", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);

			String courseName = courseNameJ.getText();
			System.out.println("Student Name: " + courseName);

			String department = departmentJ.getText();
			System.out.println("Department Name: " + department);

			String courseNumber = courseNumberJ.getText();
			System.out.println("Course Number Name: " + courseNumber);

			String courseCredit = courseCreditJ.getText();
			System.out.println("Course Credit Name: " + courseCredit);

			/*
			 * Check that department exists.
			 */
			if (university.departmentExists(department))
			{
				Department courseDepartment = university.departmentList.get(university.getDepartmentNumber(department));

				OnlineCourse newCourse = new OnlineCourse();
				newCourse.setName(courseName);
				newCourse.setDepartment(courseDepartment);
				newCourse.setCourseNumber(Integer.parseInt(courseNumber));

				this.setOutputToErrorWindow();
				courseDepartment.addCourse(newCourse);
				System.out.println(newCourse.getName() + " has been sucessfully created.");

				this.setOutputToErrorConsole();
			} else
			{
				printMessage("Department: " + department + " does not exist.");
			}
		}

		private void handleAssignCourseClassroom()
		{
			JFrame temp = new JFrame("New Online Course Course");

			JLabel courseDepartmentLabel = new JLabel("Course Department:");
			JLabel courseNumberLabel = new JLabel("Course Number:");
			JLabel roomNumberLabel = new JLabel("Room Number:");

			JTextField courseDepartmentJ = new JTextField();
			JTextField courseNumberJ = new JTextField();
			JTextField roomNumberJ = new JTextField();

			@SuppressWarnings("unused")
			int result = JOptionPane.showOptionDialog(temp, new Object[]
			{ courseDepartmentLabel, courseDepartmentJ, courseNumberLabel, courseNumberJ, roomNumberLabel,
					roomNumberJ }, "Assign Course Classroom", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);

			String departmentName = courseDepartmentJ.getText();
			System.out.println("Course Department: " + departmentName);

			String courseNumber = courseNumberJ.getText();
			System.out.println("Course Number: " + courseNumber);

			String roomNumber = roomNumberJ.getText();
			System.out.println("Room Number: " + roomNumber);

			if (university.departmentExists(departmentName))
			{
				Integer departmentIndex = university.getDepartmentNumber(departmentName);

				CampusCourse course = university.departmentList.get(university.getDepartmentNumber(departmentName))
						.getCampusCourseList()
						.get(university.getCourseIndex(departmentIndex, Integer.parseInt(courseNumber)));

				Classroom classroom = university.getClassroom(roomNumber);

				this.setOutputToErrorWindow();
				course.setRoomAssigned(classroom);

				if (printArray.toString().equals(""))
				{
					System.out.println("Classroom: " + classroom.getRoomNumber() + " sucessfully assigned to "
							+ departmentName + " " + courseNumber);

				}

				this.setOutputToErrorConsole();

			} else
			{
				printMessage("Department: " + departmentName + " does not exist.");
			}

		}

		private void setOutputToScrollBarWindow()
		{

			printArray = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(printArray);

			// Setting the byte array as the output.
			System.setOut(out);
		}

		private void setOutputToScrollBarConsole(String windowName)
		{
			// Open a JFrame that contains the window
			JFrame temp = new JFrame(windowName);

			@SuppressWarnings("unused")
			Container tempContainer = temp.getContentPane();
			JTextPane pane = new JTextPane();
			SimpleAttributeSet set = new SimpleAttributeSet();
			pane.setCharacterAttributes(set, true);

			// Appends the text from the byte array to the window pane.
			pane.setText(printArray.toString());

			JScrollPane scrollPane = new JScrollPane(pane);

			// Spawns the window pane
			temp.add(scrollPane, BorderLayout.CENTER);
			temp.setSize(375, 750);
			temp.setLocationRelativeTo(null);
			temp.setVisible(true);

			System.setOut(old);
		}

		private void setOutputToErrorWindow()
		{

			printArray = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(printArray);

			// Setting the byte array as the output.
			System.setOut(out);
		}

		private void setOutputToErrorConsole()
		{

			JOptionPane.showMessageDialog(null, printArray.toString());

			System.setOut(old);

		}

		private void printMessage(String s)
		{
			printArray = new ByteArrayOutputStream();
			PrintStream out = new PrintStream(printArray);

			// Setting the byte array as the output.
			System.setOut(out);

			System.out.println(s);

			JOptionPane.showMessageDialog(null, printArray.toString());

			System.setOut(old);
		}

	}
}
