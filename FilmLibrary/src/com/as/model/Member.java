package com.as.model;

import java.io.Serializable;

public class Member implements Serializable {

    public int idMember;
    public String name;
    public String permanentAddress;
    public String telephone;

    public Member(int idMember, String name, String permanentAddress, String telephone) {
        this.idMember = idMember;
        this.name = name;
        this.permanentAddress = permanentAddress;
        this.telephone = telephone;
    }

    public Member() {
    }

    public Member(int idMember, String name) {
        this.idMember = idMember;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
