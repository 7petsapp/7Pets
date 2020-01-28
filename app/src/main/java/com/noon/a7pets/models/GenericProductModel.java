package com.noon.a7pets.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by kshitij on 16/1/18.
 */

public class GenericProductModel implements Parcelable{

    public Long cardid;
    public String cardname;
    public String cardimage;
    public String carddiscription;
    public Long cardprice;

    public GenericProductModel() {
    }

    public GenericProductModel(Long id, String name, String img, String description) {
    }

    public GenericProductModel(Long cardid, String cardname, String cardimage, String carddiscription, Long cardprice) {
        this.cardid = cardid;
        this.cardname = cardname;
        this.cardimage = cardimage;
        this.carddiscription = carddiscription;
        this.cardprice = cardprice;
    }

    protected GenericProductModel(Parcel in) {
        if (in.readByte() == 0) {
            cardid = null;
        } else {
            cardid = in.readLong();
        }
        cardname = in.readString();
        cardimage = in.readString();
        carddiscription = in.readString();
        if (in.readByte() == 0) {
            cardprice = null;
        } else {
            cardprice = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (cardid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cardid);
        }
        dest.writeString(cardname);
        dest.writeString(cardimage);
        dest.writeString(carddiscription);
        if (cardprice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(cardprice);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GenericProductModel> CREATOR = new Creator<GenericProductModel>() {
        @Override
        public GenericProductModel createFromParcel(Parcel in) {
            return new GenericProductModel(in);
        }

        @Override
        public GenericProductModel[] newArray(int size) {
            return new GenericProductModel[size];
        }
    };

    public Long getCardid() {
        return cardid;
    }

    public void setCardid(Long cardid) {
        this.cardid = cardid;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardimage() {
        return cardimage;
    }

    public void setCardimage(String cardimage) {
        this.cardimage = cardimage;
    }

    public String getCarddiscription() {
        return carddiscription;
    }

    public void setCarddiscription(String carddiscription) {
        this.carddiscription = carddiscription;
    }

    public Long getCardprice() {
        return cardprice;
    }

    public void setCardprice(Long cardprice) {
        this.cardprice = cardprice;
    }
}
