package com.badri.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badri.hibernate.dao.StudentDao;
import com.badri.hibernate.entity.Student;
import com.badri.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentDao studentDao;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void save(Student student) {
		studentDao.saveStudent(student);
	}

	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	public List<Student> getStudents() {
		return studentDao.getStudents();
	}

	public void updateStudent(Student student) {
		studentDao.updateStudent(student);
	}

	public void delete(int id) {
		studentDao.delete(id);
	}

}