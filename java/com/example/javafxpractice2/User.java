package com.example.javafxpractice2;

public class User {
    public int id;
    public String company;
    public String name;
    public int score;

    public User(int id, String company, String name, int score){
        this.id = id;
        this.company = company;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }





}
