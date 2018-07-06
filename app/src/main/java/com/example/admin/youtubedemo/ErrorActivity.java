package com.example.admin.youtubedemo;

import android.app.Activity;
import android.os.Bundle;


public class ErrorActivity extends Activity{

  private ErrorFragment mErrorFragment1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_error);

    testError();
  }

  private void testError() {
    mErrorFragment1 = new ErrorFragment();
    getFragmentManager().beginTransaction().add(R.id.main_browse_fragment, mErrorFragment1).commit();
  }
}
