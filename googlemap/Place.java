package com.camtrack.tracker.googlemap;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vrajput on 12/19/2018.
 */

public class Place implements Parcelable {
    private String placeId;
    private String placeText;
    private String primaryName;
    private String secondaryName;

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceText() {
        return placeText;
    }

    public void setPlaceText(String placeText) {
        this.placeText = placeText;
    }

    public String toString(){
        return placeText;
    }

    public Place(){}

    protected Place(Parcel in) {
        placeId = in.readString();
        placeText = in.readString();
        primaryName = in.readString();
        secondaryName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeId);
        dest.writeString(placeText);
        dest.writeString(primaryName);
        dest.writeString(secondaryName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}