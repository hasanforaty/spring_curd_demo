package com.hasan.foraty.cruddemo;

import com.hasan.foraty.cruddemo.dao.StudentDao;
import com.hasan.foraty.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao){
        return runner->{
//            createStudent(studentDao);
//            readStudent(studentDao);
//            queryForStudents(studentDao);
//            queryForStudentsByLastName(studentDao);
            updateStudent(studentDao);

        };
    }

    private void updateStudent(StudentDao studentDao) {
        Student theStudent = studentDao.findById(1);
        System.out.println("Updating student ......");
        theStudent.setFirstName("Dummy");
        studentDao.update(theStudent);
        System.out.println("checking.....");
        Student changedStudent = studentDao.findById(1);
        System.out.println(changedStudent.toString().equals(theStudent.toString()));
    }

    private void queryForStudentsByLastName(StudentDao studentDao) {
        List<Student> theStudents = studentDao.findByLastName("Duck");
        for (Student student:theStudents){
            System.out.println(student);
        }
    }

    /**
     * display all student in  database
     * @param studentDao dao of class student
     */
    private void queryForStudents(StudentDao studentDao) {
        // get a list of student
        List<Student> theStudents = studentDao.findAll();
        //display list of student
        for (Student student : theStudents){
            System.out.println(student);
        }
    }


    /**
     * add student object , and display student object id
     * @param studentDao dao of class student
     *
     */
    private void createStudent(StudentDao studentDao){
        System.out.println("Creating new student object.....");
        Student tempStudent = new Student("Paul","Doe","PaulDoe@Gmail.com");
        System.out.println("Saving the student ...."+tempStudent);
        studentDao.save(tempStudent);
        System.out.println("Saved , student id : "+tempStudent.getId());
    }

    /**
     * get chosen student
     * @param studentDao dao of class student
     */
    private void readStudent(StudentDao studentDao){
        System.out.println("Creating new Student object.....");
        Student tempStudent = new Student("Daffy","Duck","Millioner@Gmail.com");
        System.out.println("Saving new Student.....");
        studentDao.save(tempStudent);
        System.out.println("Retrieve student with id : "+tempStudent.getId());
        System.out.println("Found the student "+studentDao.findById(tempStudent.getId()));
    }



}
