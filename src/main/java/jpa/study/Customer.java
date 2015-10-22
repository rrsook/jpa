package jpa.study;

import jpa.study.constants.GRADE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pillsoonpark on 2015. 10. 22..
 */
@Entity
@Table(name = "PCROOM_CUSTOMER")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GRADE grade;

    private int age;

    private String phoneNo;

    private List<CheckIn> checkInList = new ArrayList<CheckIn>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date registTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GRADE getGrade() {
        if (grade == null) {
            return GRADE.NEW;
        }
        return grade;
    }

    public void setGrade(GRADE grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}
