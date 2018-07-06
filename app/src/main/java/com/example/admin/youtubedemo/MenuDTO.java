package com.example.admin.youtubedemo;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuDTO implements Parcelable {
  private int mId;
  private String mName;


  protected MenuDTO(Parcel in) {
    mId = in.readInt();
    mName = in.readString();
  }

  public static final Creator<MenuDTO> CREATOR = new Creator<MenuDTO>() {
    @Override
    public MenuDTO createFromParcel(Parcel in) {
      return new MenuDTO(in);
    }

    @Override
    public MenuDTO[] newArray(int size) {
      return new MenuDTO[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(mId);
    parcel.writeString(mName);
  }

  public int getmId() {
    return mId;
  }

  public void setmId(int mId) {
    this.mId = mId;
  }

  public String getmName() {
    return mName;
  }

  public void setmName(String mName) {
    this.mName = mName;
  }
}
