package com.example.admin.youtubedemo;

import android.support.v17.leanback.widget.HeaderItem;
import android.text.BoringLayout;

public class MenuItem extends HeaderItem {
  public static final boolean IS_CHOSEN = false;
  public boolean isChosen = IS_CHOSEN;

  public MenuItem(long id, String name, boolean isChosen) {
    super(id, name);
    this.isChosen = isChosen;
  }

  public MenuItem(long id, String name) {
    this(id, name, IS_CHOSEN);
  }

  public MenuItem(String name) {
    super(name);
  }

}
