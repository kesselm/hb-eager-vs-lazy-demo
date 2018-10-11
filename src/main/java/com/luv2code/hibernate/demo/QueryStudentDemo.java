package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args){
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").list();

            // dislplay students
            for (Student tmpStudent : theStudents){
                System.out.println(tmpStudent);
            }

            // query students: lastName='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Duck'").list();
            System.out.println("\n\nStudents who have lastname of Duck");
            System.out.println(theStudents);

            // query students: lastName='Doe' OR firstName='Daffy'
            theStudents = session.createQuery("from Student s where s.lastName='Doe' or s.firstName='Daffy'").list();
            System.out.println("\n\nStudents who have last name of Doe OR Daffy:");
            System.out.println(theStudents);

            // query students where email LIKE '%luv2code.com'.
            theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list();

            System.out.println("Student who email ends withluv2code.com");
            System.out.println(theStudents);


            // commit transaction
            System.out.println("Starting the transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
