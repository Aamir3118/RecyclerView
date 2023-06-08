package com.example.recyclerexample;

public class ContactModel {
    int img,phn_icon;
    String name,number;

    public ContactModel(int img,String name,String number,int phn_icon)
    {
        this.img=img;
        this.name=name;
        this.number=number;
        this.phn_icon=phn_icon;
    }
    public ContactModel(String name,String number)
    {
        this.name=name;
        this.number=number;
    }
}
