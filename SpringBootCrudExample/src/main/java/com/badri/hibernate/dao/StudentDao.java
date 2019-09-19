package com.badri.hibernate.dao;

import java.util.List;

import com.badri.hibernate.entity.Student;

public interface StudentDao {
	public void saveStudent(Student student);

	public List<Student> getStudents();

	public Student getStudentById(int id);

	public void updateStudent(Student student);

	public void delete(int id);
}
