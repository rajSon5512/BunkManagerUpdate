package com.knoxpo.rajivsonawala.simplerecycler;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Bunk {



    private UUID mUUID;
    private Date mDate;
    private String mDay;


    private boolean isChecked;

    Bunk(Date mDate){

        this(mDate,false);
    }


    Bunk(Date mydate, boolean Check){

        mUUID=UUID.randomUUID();
        mDate=mydate;
        mDay= (String) DateFormat.format("EEEE",mDate);
    }

    public Date getDate() {
        return mDate;
    }


    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }


    public UUID getUUID() {
        return mUUID;
    }


}
