package com.badri.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.badri.hibernate.entity.Student;

@Component
public class StudentValidator implements Validator {
	@Autowired
	EmailValidator emailValidator;
	public static final String PNG_MIME_TYPE = "image/png";
	public static final String JPG_MIME_TYPE = "image/jpeg";
	public static final long ONE_MB_IN_BYTES = 1000000;

	public boolean supports(Class clazz) {
		return Student.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors error) {
		Student student = (Student) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "name", "student.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "student.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "mobile", "student.mobile.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "gender", "student.gender.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "address", "student.address.empty");
		if (student.getDistrict().equalsIgnoreCase("Select")) {
			error.rejectValue("district", "student.district.empty");
		}
		if (student.getPhotoname().isEmpty()) {
			error.rejectValue("photo", "student.photo.empty");
		}
		if (!student.getEmail().isEmpty() && !emailValidator.valid(student.getEmail())) {
			error.rejectValue("email", "student.email.mismatch");
		}
		if (!student.getName().isEmpty() && !student.getName().matches("[A-Za-z ]*")) {
			error.rejectValue("name", "student.name.mismatch");
		}
		if (!student.getMobile().isEmpty() && !student.getMobile().matches("[0-9]{10}")) {
			error.rejectValue("mobile", "student.mobile.mismatch");
		}
		if (!student.getPhotoname().isEmpty()) {
			if (!PNG_MIME_TYPE.equalsIgnoreCase(student.getPhototype())
					&& !JPG_MIME_TYPE.equalsIgnoreCase(student.getPhototype())) {
				error.rejectValue("photo", "student.phototype.mismatch");
			}
		}
		if (student.getSubjects() == null || student.getSubjects().length() == 0) {
			error.rejectValue("subjects", "student.subjects.empty");
		}

	}

}
