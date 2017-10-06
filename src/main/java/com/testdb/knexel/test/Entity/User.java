package com.testdb.knexel.test.Entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "user")

public class User {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "parent_name")
    private String parentname;

//    @DateTimeFormat(pattern="yyyy-MM-dd")


    private Date birthday;

//    public String getDatehelper() {
//        return datehelper;
//    }
//
//    public void setDatehelper(String datehelper) {
//        this.datehelper = datehelper;
//    }
//
//    private String datehelper;

    @Column(name = "number")
    private String number;

    @Column(name = "address")
    private String address;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    private String operationType;

    public User(Integer id, String name, String lastName, String parentname, Date birthday, String number, String address,String OperationType)
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.parentname = parentname;
        this.birthday = birthday;
        this.number = number;
        this.address = address;
        this.operationType=OperationType;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }




    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
