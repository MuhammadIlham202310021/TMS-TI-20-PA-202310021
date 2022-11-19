package com.ibik.academicservices.academicservices.student;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.academicservices.academicservices.dto.ResponseData;
import com.ibik.academicservices.academicservices.dto.SearchData;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentServices studentServices;

    @PostMapping
    public ResponseEntity<ResponseData<Student>> postStudent(@Valid @RequestBody Student student, Errors errors) {
        
        ResponseData<Student> responseData = new ResponseData<>();
        
        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        responseData.setResult(true);
        List<Student> value = new ArrayList<>();
        value.add(studentServices.save(student));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Student>> fetchStudents() {
        ResponseData<Student> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Student> value = (List<Student>) studentServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Student>> fetchStudentsById(@PathVariable("id") int id) {
        ResponseData<Student> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Student> value = new ArrayList<>();
            value.add(studentServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Student>> updateStudent(@Valid @RequestBody Student students, Errors errors) {

        ResponseData<Student> responseData = new ResponseData<>();
        if(students.getId() != 0){
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setResult(true);
            List<Student> value = new ArrayList<>();
            value.add(studentServices.save(students));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("ID is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteStudentsById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            studentServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseData<Student>> getStudentByName(@RequestBody SearchData searchData) {
        ResponseData<Student> responseData = new ResponseData<>();
        try {
            Iterable<Student> values = studentServices.findByName(searchData.getSearchKey());
            responseData.setResult(true);
            responseData.getMessage();
            responseData.setData(values);
            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.getMessage().add(ex.getMessage());
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
