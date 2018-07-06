package com.example.admin.youtubedemo;

import android.support.v17.leanback.widget.HeaderItem;

public class LeftMenuIcon extends HeaderItem {
  private int idLeftMenuIcon;
  private int mIcon;


  public int getIdLeftMenuIcon() {
    return idLeftMenuIcon;
  }

  public void setIdLeftMenuIcon(int idLeftMenuIcon) {
    this.idLeftMenuIcon = idLeftMenuIcon;
  }

  public int getmIcon() {
    return mIcon;
  }

  public void setmIcon(int mIcon) {
    this.mIcon = mIcon;
  }

  public LeftMenuIcon(int idLeftMenuIcon, String name, int mIcon) {
    super(name);
    this.idLeftMenuIcon = idLeftMenuIcon;
    this.mIcon = mIcon;
  }

  public LeftMenuIcon(long id, String name) {
    super(id, name);
  }
}
