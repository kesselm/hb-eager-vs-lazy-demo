package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args){
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 1;

            // now get a new session and start transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student .... ");
            myStudent.setFirstName("Scooby");

            // commit the transaction
            session.getTransaction().commit();

            // New CODE

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students.");

            session.createQuery("update Student set email='foo@gmail.com'")
                    .executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");


        } finally {
            sessionFactory.close();
        }
    }
}
