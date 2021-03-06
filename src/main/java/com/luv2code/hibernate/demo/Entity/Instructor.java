package com.luv2code.hibernate.demo.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// annotate the class as an entity and map to db table
@Entity
@Table(name="instructor")
public class Instructor {

    // define the fields
    // annotate the fields with db column names
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String eMail;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch=FetchType.EAGER,
            mappedBy = "instructor",
            cascade={CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.DETACH,
                CascadeType.REFRESH})
    private List<Course> courses;


    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    // add convenience methods for bi-directional relationship

    public void add(Course tempCourse){
        if(courses == null){
            courses = new ArrayList<Course>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }
}
