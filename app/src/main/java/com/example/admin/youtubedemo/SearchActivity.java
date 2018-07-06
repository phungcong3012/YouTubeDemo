package com.example.admin.youtubedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class SearchActivity extends Activity{
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.acitivity_search);

    SearchFragment searchFragment = new SearchFragment();
    getFragmentManager().beginTransaction().add(R.id.search_fragment,searchFragment);
  }
}
