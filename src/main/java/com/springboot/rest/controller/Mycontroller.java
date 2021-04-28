package com.springboot.rest.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.rest.dao.Mydaorepository;
import com.springboot.rest.entity.APIResponse;
import com.springboot.rest.entity.ApiResponseError;
import com.springboot.rest.entity.ChangePassword;
import com.springboot.rest.entity.ForgotPassword;
import com.springboot.rest.entity.Login;
import com.springboot.rest.entity.UserResponse;
import com.springboot.rest.model.Student;
import com.springboot.rest.service.Myservice;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class Mycontroller {

	@Autowired
	Myservice service;

	@Autowired
	Mydaorepository repository;

	private Object password;
	private Object username;

	@PostMapping(value = "/saveStudent")
	public Student saveStudent(@RequestBody Student student) {
		System.out.println(student);
		return service.saveStudent(student);

	}

	@GetMapping(value = "/getAllStudent")
	public List<Student> getAllStudent() {
		return service.getAllStudent();

	}

	@DeleteMapping(value = "/deletestudent/{id}")
	public void deleteStudent(@PathVariable(value = "id") int id) {
		service.deleteStudent(id);
	}

	@PutMapping(value = "/updateStudent/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") int id,

			@Valid @RequestBody Student studentDetails) {

		Student student = service.getstudentById(id);
		student.setName(studentDetails.getName());
		student.setBranch(studentDetails.getBranch());
		student.setEmail(studentDetails.getEmail());
		student.setUsername(studentDetails.getUsername());
		student.setPassword(studentDetails.getPassword());
		final Student updatedStudent = service.saveStudent(student);
		return ResponseEntity.ok(updatedStudent);

	}

	@GetMapping(value = "/getStudent/{id}")
	public Student getStudentById(@PathVariable int id) {
		return service.getstudentById(id);
	}

	@PostMapping("/veryfyEmail")
	public ResponseEntity verifyMail(@RequestBody ForgotPassword login) {
		return service.veryfyEmail(login);
	}

	@PostMapping("/ChangePassword")
	public ResponseEntity changePassword(@RequestBody ChangePassword login) {
		return service.changePassword(login);
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Login login) {
		APIResponse<UserResponse> apiResponse = null;
		try {
			
			apiResponse = new APIResponse<>();
			UserResponse user;
			user = service.login(login);
			apiResponse.setData(user);
			apiResponse.setCode(200);
		} catch (Exception e) {
			ApiResponseError error=new ApiResponseError();
			error.setMessage("username and password not match");
			error.setCode(401);
			return ResponseEntity.status(error.getCode()).body(error);
		}
		return ResponseEntity.status(200).body(apiResponse);

	}
	
	@GetMapping("/SortByEmail")
	public ResponseEntity<List<Student>>getAllStudents(@RequestParam Integer pageNo,@RequestParam Integer pageSize, @RequestParam String sortBy){
		List<Student> list=service.getAllStudentList(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<Student>>(list,new HttpHeaders(),HttpStatus.OK);
		
	}
	
	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) throws Exception {
		service.upload(file);
	}
}