package com.ibik.academicservices.academicservices.courses;

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

@RestController
@RequestMapping("/api/course")
public class CoursesController {
    
    @Autowired
    private CoursesServices coursesServices;

    @PostMapping
    public ResponseEntity<ResponseData<Courses>> postCourses(@Valid @RequestBody Courses courses, Errors errors) {
        
        ResponseData<Courses> responseData = new ResponseData<>();
        
        if (errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            
            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        
        responseData.setResult(true);
        List<Courses> value = new ArrayList<>();
        value.add(coursesServices.save(courses));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Courses>> fetchCourses() {
        ResponseData<Courses> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Courses> value = (List<Courses>) coursesServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Courses>> fetchCoursesById(@PathVariable("id") int id) {
        ResponseData<Courses> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Courses> value = new ArrayList<>();
            value.add(coursesServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Courses>> updateCourses(@Valid @RequestBody Courses courses, Errors errors) {

        ResponseData<Courses> responseData = new ResponseData<>();
        if(courses.getId() != 0){
            if (errors.hasErrors()) {
                for (ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                responseData.setResult(false);
                responseData.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setResult(true);
            List<Courses> value = new ArrayList<>();
            value.add(coursesServices.save(courses));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.getMessage().add("ID is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deletesCoursesById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            coursesServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
