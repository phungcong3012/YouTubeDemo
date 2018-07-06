package com.example.admin.youtubedemo;

import android.support.v17.leanback.widget.HeaderItem;
import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;

public class ContentYouTube{

  public ContentYouTube() {
  }

  private static final String TAG = ContentYouTube.class.getSimpleName();
  private String mTimeSong;
  private String mNameSong;
  private String mChannel;
  private int mView;
  private long mPostTime;
  private String mUrlPicture;

  public ContentYouTube(String mTimeSong, String mNameSong, String mChannel, int mView, long mPostTime, String mUrlPicture) {
    this.mTimeSong = mTimeSong;
    this.mNameSong = mNameSong;
    this.mChannel = mChannel;
    this.mView = mView;
    this.mPostTime = mPostTime;
    this.mUrlPicture = mUrlPicture;
  }

  public String getmTimeSong() {
    return mTimeSong;
  }

  public void setmTimeSong(String mTimeSong) {
    this.mTimeSong = mTimeSong;
  }

  public String getmNameSong() {
    return mNameSong;
  }

  public void setmNameSong(String mNameSong) {
    this.mNameSong = mNameSong;
  }

  public String getmChannel() {
    return mChannel;
  }

  public void setmChannel(String mChannel) {
    this.mChannel = mChannel;
  }

  public int getmView() {
    Log.e(TAG, "getmView: " );
    return mView;
  }

  public void setmView(int mView) {
    this.mView = mView;
  }

  public long getmPostTime() {
    return mPostTime;
  }

  public void setmPostTime(long mPostTime) {
    this.mPostTime = mPostTime;
  }

  public String getmUrlPicture() {
    return mUrlPicture;
  }

  public void setmUrlPicture(String mUrlPicture) {
    this.mUrlPicture = mUrlPicture;
  }

  public URI getImageURI(){
    try {
      return new URI(getmUrlPicture());
    }catch (URISyntaxException e){
      return null;
    }
  }
}
