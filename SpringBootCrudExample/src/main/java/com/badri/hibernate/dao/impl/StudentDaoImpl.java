package com.badri.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.badri.hibernate.dao.StudentDao;
import com.badri.hibernate.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveStudent(Student student) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(student);
		session.beginTransaction().commit();
	}

	public List<Student> getStudents() {
		Session session = sessionFactory.openSession();
		String hql = "from Student";
		Query query = session.createQuery(hql);
		List<Student> students = query.list();
		return students;
	}

	public Student getStudentById(int id) {
		Session session = sessionFactory.openSession();
		return (Student) session.get(Student.class, id);
	}

	public void updateStudent(Student student) {
		Session session = sessionFactory.openSession();
		if (!student.getPhotoname().isEmpty()) {
			String hql = "update Student set name=:name,email=:email,mobile=:mobile,gender=:gender,district=:district,address=:address,subjects=:subjects,photo=:photo,photoname=:photoname,phototype=:phototype where student_id=:student_id";
			Query query = session.createQuery(hql);
			query.setParameter("name", student.getName());
			query.setParameter("email", student.getEmail());
			query.setParameter("mobile", student.getMobile());
			query.setParameter("gender", student.getGender());
			query.setParameter("district", student.getDistrict());
			query.setParameter("address", student.getAddress());
			query.setParameter("subjects", student.getSubjects());
			query.setParameter("photo", student.getPhoto());
			query.setParameter("photoname", student.getPhotoname());
			query.setParameter("phototype", student.getPhototype());
			query.setParameter("student_id", student.getStudent_id());
			query.executeUpdate();
		} else {
			String hql = "update Student set name=:name,email=:email,mobile=:mobile,gender=:gender,district=:district,address=:address,subjects=:subjects where student_id=:student_id";
			Query query = session.createQuery(hql);
			query.setParameter("name", student.getName());
			query.setParameter("email", student.getEmail());
			query.setParameter("mobile", student.getMobile());
			query.setParameter("gender", student.getGender());
			query.setParameter("district", student.getDistrict());
			query.setParameter("address", student.getAddress());
			query.setParameter("subjects", student.getSubjects());
			query.setParameter("student_id", student.getStudent_id());
			query.executeUpdate();
		}
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Student student = (Student) session.get(Student.class, id);
		session.delete(student);
		session.beginTransaction().commit();
	}
}
