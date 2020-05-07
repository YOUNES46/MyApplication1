package com.example.myapplication;


public class Student {
    private String id;
    private String name;
    private String mnemonic;
    private int test1,test2,absence,assiduite;
    private String email;
    private String wilaya;
    public Student(){}

    public Student(String name, String mnemonic,int test1,int test2,int absence,int assiduite){
        this.name=name;
        this.mnemonic=mnemonic;
        this.test1=test1;
        this.test2=test2;
        this.absence=absence;
        this.assiduite=assiduite;
    }


    public String getName() {
        return name;
    }

    public int getTest1() {
        return test1;
    }

    public int getTest2() {
        return test2;
    }

    public int getAbsence() {
        return absence;
    }

    public int getAssiduite() {
        return assiduite;
    }

    public String getMnemonic() {
        return mnemonic;
    }
    public  String getId(){return id;}
    public String getEmail(){return email;}
    public String getWilaya(){return wilaya;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public void setId(String id1){this.id = id1;}
}
