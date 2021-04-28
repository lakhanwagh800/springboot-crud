package com.springboot.rest.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.rest.config.TokenManager;
import com.springboot.rest.dao.DaoRepository;
import com.springboot.rest.dao.Mydaorepository;
import com.springboot.rest.entity.APIResponse;
import com.springboot.rest.entity.ApiResponseError;
import com.springboot.rest.entity.ChangePassword;
import com.springboot.rest.entity.CustomeException;
import com.springboot.rest.entity.ForgotPassword;
import com.springboot.rest.entity.Login;
import com.springboot.rest.entity.UserResponse;
import com.springboot.rest.model.Student;

@Service
public class Myserviceimpl implements Myservice {

	@Autowired
	Mydaorepository repository;

	@Autowired
	DaoRepository dao;
	
	@Override
	public List<Student> getAllStudent() {
		return repository.findAll();
	}

	@Override
	public Student getstudentById(int id) {
		return repository.getOne(id);
	}

	@Override
	public Student saveStudent(Student student) {

		return repository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		repository.deleteById(id);

	}
	
	
	public List<Student> getAllStudentList(Integer pageNo,Integer pageSize,String sortBy){
		PageRequest paging=PageRequest.of(pageNo, pageSize,Sort.by("email"));
		Page<Student>pageResult=dao.findAll(paging);
		
		if(pageResult.hasContent()) {
			return pageResult.getContent();
			
		}else {
			return new ArrayList<Student>();
		}
		
		
	}
	
	
	
	
	public ResponseEntity veryfyEmail(ForgotPassword login) {
		Optional<Student> res = repository.findByEmail(login.getEmail());
		System.out.println(res);
		APIResponse apiresponce = new APIResponse();
		if (res.isPresent()) {
			apiresponce.setSuccess(true);
			apiresponce.setCode(200);
			return ResponseEntity.status(apiresponce.getCode()).body(apiresponce);
			
		} else {
			
			ApiResponseError error = new ApiResponseError();
			apiresponce.setSuccess(false);
			apiresponce.setCode(500);
			error.setCode(500);
			error.setMessage("Error");
			apiresponce.setError(error);

			return ResponseEntity.status(500).body(apiresponce);

		}

	}

	@Override
	public ResponseEntity changePassword(ChangePassword login) {
		Optional<Student> emp = repository.findByEmail(login.getEmail());
		APIResponse apiresponce = new APIResponse();
		ApiResponseError error = new ApiResponseError();

		if (Objects.nonNull(emp)) {
			if (emp.get().getEmail().equals(login.getEmail())) {
				
				if (login.getNewPassword().equals(login.getConfirmPassword())) {
					emp.get().setPassword(login.getConfirmPassword());
					repository.save(emp.get());
					apiresponce.setCode(200);
					apiresponce.setSuccess(true);
				} else {

					error.setCode(500);
					error.setMessage("Error");
					apiresponce.setError(error);
				}

			} else {
				
				error.setCode(500);
				error.setMessage("Error");
				apiresponce.setError(error);
			}
		} else {
			error.setCode(500);
			error.setMessage("Error");
			apiresponce.setError(error);
		}
		return ResponseEntity.status(apiresponce.getCode()).body(apiresponce);

	}
	

	
	@Override
	public UserResponse login(Login login) throws Exception {
		UserResponse response = new UserResponse();
		Optional<Student> loginResponse= repository.findByEmail(login.getUsername());
		System.out.println(loginResponse);
		if(loginResponse.isPresent()) {
			Student logindetail=loginResponse.get();
			if(logindetail.getEmail().equals(login.getUsername()) && logindetail.getPassword().equals(login.getPassword())) {
				BeanUtils.copyProperties(logindetail, response);
				String token = TokenManager.issueToken(logindetail, 30);
				response.setToken(token);
			}else{
				 throw new CustomeException("Login Failed !");
			}
		}else {
			 throw new CustomeException("please try again !");
		}
        return response;
	}

	@Override
	public void upload(MultipartFile file) throws Exception {
		Path tmpDir=Files.createTempDirectory("");
	File tmpFile=tmpDir.resolve(file.getOriginalFilename()).toFile();
	Workbook workbook= WorkbookFactory.create(tmpFile);
	Sheet sheet=workbook.getSheetAt(0);
	Stream<Row> rowStream=StreamSupport.stream(sheet.spliterator(),false);
	
	rowStream.forEach(row ->{
		Stream<Cell> cellStream=StreamSupport.stream(row.spliterator(),false);
		List<String> cellVals=cellStream.map(cell ->{
			String cellVal=cell.getStringCellValue();
			return cellVal;
		})
				.collect(Collectors.toList());
		System.out.println(cellVals);
	});
	
	}
	
	
	
	
	
	
}