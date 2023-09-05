package com.hasan.foraty.cruddemo;

import com.hasan.foraty.cruddemo.dao.StudentDao;
import com.hasan.foraty.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao){
        return runner->{

            String input=null;
            while (true){
                System.out.println("Welcome to Student Command Line , for Exit please write /q");
                System.out.println("new    : For adding new Student ");
                System.out.println("update : For changing a Student ");
                System.out.println("see    : For seeing a Student ");
                System.out.println("seeAll : For seeing all of  Students");
                Scanner in = new Scanner(System.in);

                input= in.nextLine().toLowerCase();
                switch (input){
                    case "new":{
                        createStudent(studentDao);
                        break;
                    }
                    case "update":{
                        updateStudent(studentDao);
                        break;
                    }
                    case "see":{
                        readStudent(studentDao);
                        break;
                    }
                    case "seeall":{
                        queryForStudents(studentDao);
                        break;
                    }
                    case "/q":{
                        return;

                    }
                    default:{
                        System.out.println("Please choose an option from one presented to you");
                    }
                }
            }
        };
    }



    private Student getStudent(){
        Student student = new Student();
        Scanner in = new Scanner(System.in);
        System.out.println("Please Enter first name");
        String  firstName =in.nextLine();
        student.setFirstName(firstName);
        System.out.println("Please Enter last name");
        String  lastName =in.nextLine();
        student.setLastName(lastName);
        System.out.println("Please Enter email");
        String  email =in.nextLine();
        student.setEmail(email);
        return  student;
    }

    private void deleteAllStudent(StudentDao studentDao) {
        System.out.println("Deleting all Student......");
        int size = studentDao.findAll().size();
        System.out.println("size of table: "+size);
        int numberRowDeleted=studentDao.deleteAll();
        System.out.println("number of row deleted : "+numberRowDeleted);
    }

    private void deleteStudent(StudentDao studentDao) {
        int studentId = 2;
        System.out.println("Deleting student id : "+studentId);
        studentDao.delete(2);
        System.out.println("verifying .....");
        System.out.println("all of student .....");
        for (Student student:studentDao.findAll()){
            System.out.println(student);
        }
    }

    private void updateStudent(StudentDao studentDao) {
        System.out.println("Please Enter id of Student");
        String  id = new Scanner(System.in).nextLine();
        Student theStudent = studentDao.findById(Integer.parseInt(id));
        System.out.println("Updating student ......");
        Student newStudent = getStudent();
        newStudent.setId(theStudent.getId());
        studentDao.update(newStudent);
        System.out.println("checking.....");
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
        Student tempStudent = getStudent();
        System.out.println("Saving the student ...."+tempStudent);
        studentDao.save(tempStudent);
        System.out.println("Saved , student id : "+tempStudent.getId());
    }

    /**
     * get chosen student
     * @param studentDao dao of class student
     */
    private void readStudent(StudentDao studentDao){
        System.out.println("Please Enter student id");
        String  id = new Scanner(System.in).nextLine();
        System.out.println(studentDao.findById(Integer.parseInt(id)));
    }



}
