package com.hasan.foraty.cruddemo.dao;

import com.hasan.foraty.cruddemo.entity.Student;

import java.util.List;

public interface  StudentDao {
    void save(Student thStudent);
    Student findById(int id);


    List<Student> findAll();


    List<Student> findByLastName(String lastName);

    void update(Student theStudent);

    void delete(int id);

    int deleteAll();


}
