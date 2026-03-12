package model;

public class Student {

    private String ID;
    private String name;
    private String email;
    private String contact;

    public Student(String ID, String name, String phone) {
        this.ID = ID;
        this.name = name;
        this.contact = phone;
        this.email = "None";
    }

    public Student(String ID, String name, String email, String phone) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.contact = phone;
    }


}
