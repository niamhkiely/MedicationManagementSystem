package com.example.medicationmanagementsystem;
//The code below is based on Adding multiple columns to your ListView, CodingWithMitch, https://www.youtube.com/watch?v=jpt3Md9aDIQ
public class CaringList {

    private String caringlistid;
    private String timeslot;
    //private String userid;

    public CaringList(String cListId, String tslot) {
        caringlistid = cListId;
        timeslot = tslot;

    }

    public String getCaringlistid() {
        return caringlistid;
    }

    public String getTimeslot()
    {
        return timeslot;
    }

}
//END

