package com.example.admin.youtubedemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ErrorFragment extends android.support.v17.leanback.app.ErrorFragment {
  private static final String TAG = ErrorFragment.class.getSimpleName();
  private static final boolean TRANSLUCENT = true;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.d(TAG, "onCreate");
    super.onCreate(savedInstanceState);

    setTitle(getResources().getString(R.string.content_show_channel));
    setErrorContent();
  }

  void setErrorContent() {
    setImageDrawable(getActivity().getDrawable(R.drawable.anh1));
    setMessage(getResources().getString(R.string.app_name));
    setDefaultBackground(TRANSLUCENT);

    setButtonText(getResources().getString(R.string.app_name));
    setButtonClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        getFragmentManager().beginTransaction().remove(ErrorFragment.this).commit();
      }
    });
  }
}
