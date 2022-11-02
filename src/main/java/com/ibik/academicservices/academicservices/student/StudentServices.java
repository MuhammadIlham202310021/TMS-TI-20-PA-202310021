package com.ibik.academicservices.academicservices.student;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentServices {
    
    @Autowired
    private StudentRepo studentRepo;

    public Student save(Student student){
        return studentRepo.save(student);
    }

    public Student findOne(int id){
        return studentRepo.findById(id).get();
    }    

    public Iterable<Student> findAll(){
        return studentRepo.findAll();
    }

    public void removoOne(int id){
        studentRepo.deleteById(id);
    }
}
