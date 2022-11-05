package org.university.people;

import org.university.hardware.Department;
import java.io.Serializable;

public abstract class Employee extends Person implements Serializable
{
	protected Department department;

	public Department getDepartment()
	{
		return this.department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	public abstract double earns();

	public abstract void raise(double percent);

}
