package com.example.listview2b;

public class InfoPlayer {
    private String name;
    private int age;
    private int image;
    private String cLUB;
    private String country;

    public InfoPlayer(String name, int age, int image, String cLUB, String country) {
        this.name = name;
        this.age = age;
        this.image = image;
        this.cLUB = cLUB;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getcLUB() {
        return cLUB;
    }

    public void setcLUB(String cLUB) {
        this.cLUB = cLUB;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
