package com.example.medicationmanagementsystem;

public class CaringList {

    private String caringlistid;
    private String timeslot;
    //private String userid;

    public CaringList(String cListId, String tslot) {
        caringlistid = cListId;
        timeslot = tslot;
        //userid = uID;

    }

    public String getCaringlistid() {
        return caringlistid;
    }

    public String getTimeslot()
    {
        return timeslot;
    }

    //public String getUserid() {
        //return userid;
    //}
}


