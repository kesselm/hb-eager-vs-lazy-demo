package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.Entity.Instructor;
import com.luv2code.hibernate.demo.Entity.InstructorDetail;
import com.luv2code.hibernate.demo.Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args){
        // create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = sessionFactory.getCurrentSession();

        try {

            // create the objects
            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            // start a transaction
            session.beginTransaction();

            // save the instructor
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            System.out.println("Saving instructor details: " + tempInstructorDetail);
            session.save(tempInstructor);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
