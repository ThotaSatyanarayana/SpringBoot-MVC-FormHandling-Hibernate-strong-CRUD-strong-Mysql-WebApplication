package com.badri.service;

import java.util.List;

import com.badri.hibernate.entity.Student;

public interface StudentService {

	public void save(Student student);

	public Student getStudentById(int id);

	public List<Student> getStudents();

	public void updateStudent(Student student);

	public void delete(int id);
}
