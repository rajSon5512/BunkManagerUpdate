package com.knoxpo.rajivsonawala.simplerecycler;

import java.util.Date;
import java.util.UUID;

public class Bunk {



    private UUID mUUID;
    private Date mDate;
    private boolean mIsChecked;

    Bunk(Date mDate){
        this(mDate,false);
    }


    Bunk(Date date, boolean isChecked){
        mUUID=UUID.randomUUID();
        mDate=date;
        mIsChecked=isChecked;
    }

    public Date getDate() {
        return mDate;
    }


    public boolean isChecked() {
        return mIsChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
    }


    public UUID getUUID() {
        return mUUID;
    }
}
