package com.example.admin.youtubedemo;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v17.leanback.widget.SpeechRecognitionCallback;

import butterknife.internal.Utils;

public class SearchFragment extends android.support.v17.leanback.app.SearchFragment {

  private static final String TAG = SearchFragment.class.getSimpleName();

  private static final int REQUEST_SPEECH = 0x00000010;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    if (!new Utils().hasPermission(getActivity(), Manifest.permission.RECORD_AUDIO)) {
      setSpeechRecognitionCallback(new SpeechRecognitionCallback() {
        @Override
        public void recognizeSpeech() {
          // Log.v(TAG, "recognizeSpeech");
          try {
            startActivityForResult(getRecognizerIntent(), REQUEST_SPEECH);
          } catch (ActivityNotFoundException e) {
            //  Log.e(TAG, "Cannot find activity for speech recognizer", e);
          }
        }
      });
    }

  }
  public class Utils{
    public  boolean hasPermission(final Context context, final String permission) {
      return PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(
          permission, context.getPackageName());
    }
  }
}
