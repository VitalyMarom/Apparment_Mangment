package com.example.apparment_mangment;

public class Resident {

    private String name;
    private String lastName;
    private int monthsToPay;
    private int monthsPayed;

    public Resident(String name, String lastName, int monthsToPay, int monthsPayed) {
        this.name = name;
        this.lastName = lastName;
        this.monthsToPay = monthsToPay;
        this.monthsPayed = monthsPayed;
    }

    public String getName() {
        return name;
    }

    public String getlastName() {
        return lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMonthsToPay() {
        return monthsToPay;
    }

    public void setMonthsToPay(int monthsToPay) {
        this.monthsToPay = monthsToPay;
    }

    public int getMonthsPayed() {
        return monthsPayed;
    }

    public void setMonthsPayed(int monthsPayed) {
        this.monthsPayed = monthsPayed;
    }

    @Override
    public String toString()
    {
        return String.format("Name: %s \nLast Name: %s \nMonths To Pay: %d \nMonths Payed: %d\n",name,lastName,monthsToPay,monthsPayed);
    }
}
