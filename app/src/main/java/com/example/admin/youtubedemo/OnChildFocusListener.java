package com.example.admin.youtubedemo;

import android.graphics.Rect;
import android.view.View;

public interface OnChildFocusListener {
  boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect);

  void onRequestChildFocus(View child, View focused);
}
