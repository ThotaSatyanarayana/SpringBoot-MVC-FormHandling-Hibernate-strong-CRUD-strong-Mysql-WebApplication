package com.badri.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.badri.hibernate.entity.Student;
import com.badri.service.StudentService;
import com.badri.spring.validator.StudentValidator;
import com.google.gson.Gson;

@Controller
public class StudentController {
	@Autowired
	StudentValidator studentValidator;
	@Autowired
	StudentService studentService;

	@RequestMapping("/")
	public ModelAndView showRegisterPage() {
		ModelAndView modelandview = new ModelAndView("Register");
		populateData(modelandview);
		return modelandview.addObject("student", new Student());
	}

	@RequestMapping("/Register")
	public ModelAndView RegisterPage() {
		ModelAndView modelandview = new ModelAndView("Register");
		populateData(modelandview);
		return modelandview.addObject("student", new Student());
	}

	public void populateData(ModelAndView modelandview) {
		List<String> subjects = new ArrayList<String>();
		subjects.add("CoreJava");
		subjects.add("Hibernate");
		subjects.add("Spring");
		modelandview.addObject("subjects", subjects);
		List<String> districts = new ArrayList<String>();
		districts.add("Guntur");
		districts.add("Krishna");
		modelandview.addObject("districts", districts);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("student") Student student, BindingResult result,
			@RequestParam("file") MultipartFile file) throws IOException {
		ModelAndView modelAndView = new ModelAndView("Register");
		ModelAndView listmodelAndView = new ModelAndView("redirect:List");
		if (file != null) {
			student.setPhotoname(file.getOriginalFilename());
			student.setPhoto(file.getBytes());
			student.setPhototype(file.getContentType());
		}
		studentValidator.validate(student, result);
		if (result.hasErrors()) {
			populateData(modelAndView);
			return modelAndView;
		}
		try {
			studentService.save(student);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listmodelAndView;
	}

	@RequestMapping(value = "/imagedisplay")
	public void productDisplay(@RequestParam("student_id") int student_id, HttpServletResponse response)
			throws IOException {
		Student student = studentService.getStudentById(student_id);
		OutputStream out = response.getOutputStream();
		response.setContentType(student.getPhototype());
		IOUtils.copy(new ByteArrayInputStream(student.getPhoto()), out);
		out.flush();
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestParam("student_id") int student_id, HttpServletResponse response)
			throws IOException {
		ModelAndView modelAndView = new ModelAndView("Update");
		Student student = studentService.getStudentById(student_id);
		populateData(modelAndView);
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	@RequestMapping("/List")
	public ModelAndView showListPage() {
		ModelAndView modelandview = new ModelAndView("List");
		List<Student> students = studentService.getStudents();
		modelandview.addObject("students", students);
		return modelandview;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("student") Student student, BindingResult result,
			@RequestParam("file") MultipartFile file) throws IOException {
		ModelAndView listmodelAndView = new ModelAndView("redirect:List");
		if (file != null) {
			student.setPhotoname(file.getOriginalFilename());
			student.setPhoto(file.getBytes());
			student.setPhototype(file.getContentType());
		}
		try {
			studentService.updateStudent(student);
		} catch (Exception e) {
			System.out.println(e);
		}
		return listmodelAndView;
	}

	@RequestMapping(value = "/chekedsubjects")
	public @ResponseBody String getChekedSubjects(HttpServletRequest request,
			@RequestParam("student_id") int student_id) {
		Gson json = new Gson();
		Student student = studentService.getStudentById(student_id);
		String[] subjects = student.getSubjects().split(",");
		String chekedsubjects = "";
		if (subjects != null) {
			chekedsubjects = json.toJson(subjects);
		}
		return chekedsubjects;
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(@RequestParam("student_id") int student_id, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("redirect:List");
		studentService.delete(student_id);
		return modelAndView;
	}

}
