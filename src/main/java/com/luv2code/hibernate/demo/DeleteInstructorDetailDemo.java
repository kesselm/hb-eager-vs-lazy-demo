package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.Entity.Instructor;
import com.luv2code.hibernate.demo.Entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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

            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 4;
            InstructorDetail tmpInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tmpInstructorDetail);

            // remove the associated object reference
            // break bi-directional link
            tmpInstructorDetail.getInstructor().setInstructorDetail(null);

            // print the associated instructor
            System.out.println("the associated instructor: " + tmpInstructorDetail.getInstructor());

            // now let's delete the instructor detail
            System.out.println("Deleting tmpInstructorDetail: " + tmpInstructorDetail);
            session.delete(tmpInstructorDetail);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();
            sessionFactory.close();
        }
    }
}
