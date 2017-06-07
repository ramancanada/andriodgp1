package com.example.a123.buildapc;

/**
 * Created by 123 on 6/5/2017.
 */
public class Datas {

    //private variables
    int _id;
    String _item;
    String _comment;
    String _date;
    String _time;

    // Empty constructor
    public Datas(){

    }
    // constructor
    public Datas(int id, String item, String comment, String date, String time){
        this._id = id;
        this._item = item;
        this._comment = comment;
        this._date = date;
        this._time = time;
    }

    // constructor
    public Datas(String item, String comment, String date, String time){
        this._item = item;
        this._comment = comment;
        this._date = date;
        this._time = time;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getItem(){
        return this._item;
    }

    // setting name
    public void setItem(String item){
        this._item = item;
    }

    // getting phone number
    public String getComment(){
        return this._comment;
    }

    // setting phone number
    public void setComment(String comment){
        this._comment = comment;
    }

    // getting phone number
    public String getDate(){
        return this._date;
    }

    // setting phone number
    public void setDate(String date){
        this._date = date;
    }

    // getting phone number
    public String getTime(){
        return this._time;
    }

    // setting phone number
    public void setTime(String time){
        this._time = time;
    }
}