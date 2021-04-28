package com.springboot.rest.service;



import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest.entity.ChangePassword;
import com.springboot.rest.entity.ForgotPassword;
import com.springboot.rest.entity.Login;
import com.springboot.rest.entity.UserResponse;
import com.springboot.rest.model.Student;

public interface Myservice {

	public List<Student> getAllStudent();
	public Student getstudentById(int id);
	public Student saveStudent(Student student);
	public Student updateStudent(Student student);
	public void deleteStudent(int id);
	public UserResponse login(Login login) throws Exception;
	public ResponseEntity veryfyEmail(ForgotPassword login);
	public ResponseEntity changePassword(ChangePassword login);
	
	public List<Student> getAllStudentList(Integer pageNo,Integer pageSize,String sortBy);
	public void upload(MultipartFile file) throws Exception;
}