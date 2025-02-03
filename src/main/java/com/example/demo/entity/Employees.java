package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "employees", schema = "osius")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fname;
    private String lname;
    private String pan;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Integer getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPan() {
        return pan;
    }
}

