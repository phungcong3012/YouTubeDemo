package com.example.admin.youtubedemo;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.BaseOnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.security.PublicKey;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

  public static final String LEFT_MENU = "left_menu";
  public static final String CONTENT_SHOW = "content_show";
  public static final String ICON_LEFT_MENU = "icon_left_menu";
  private static final String TAG = MainActivity.class.getSimpleName();

  @BindView(R.id.fragment_menu_icon)
  FrameLayout fragment_menu_icon;

  @BindView(R.id.fragment_menu_left)
  FrameLayout fragment_left_menu;

  @BindView(R.id.fragment_content)
  FrameLayout fragment_content;

  @BindView(R.id.container)
  CustomConstraintLayout mContainer;

  FragmentManager fragmentManager = getSupportFragmentManager();
  private LeftMenuIconFragment leftMenuIconFragment = null;
  private LeftMenuFragment leftMenuFragment = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    showLeft();

  }

  public void focus() {
    leftMenuIconFragment.getSelectedView().requestFocus();
    leftMenuIconFragment.setSelectedPosition(1);
  }

  private void showLeft() {

    leftMenuIconFragment = new LeftMenuIconFragment();
    leftMenuFragment = new LeftMenuFragment();
    final ContenFragment contenFragment = new ContenFragment();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_menu_icon, leftMenuIconFragment, ICON_LEFT_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_menu_left, leftMenuFragment, LEFT_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_content, contenFragment, CONTENT_SHOW)
        .commit();


    leftMenuIconFragment.setLeftMenuIconFragment(new LeftMenuIconFragment.OnClickItemListener() {
      @Override
      public void OnClickView(int id) {

        switch (id) {
          case 1:
            Intent intent = new Intent(getApplication(), SearchActivity.class);
            startActivity(intent);
            break;
        }
      }
    });


    contenFragment.setOnClickContenItem(new ContenFragment.onClickContenItem() {
      @Override
      public void onClickContent(long id, ContentYouTube contentYouTube) {

        String mTimeSong = contentYouTube.getmTimeSong();
        String mNameSong = contentYouTube.getmNameSong();
        String nameChannel = contentYouTube.getmChannel();
        int mView = contentYouTube.getmView();
        long mPostTime = contentYouTube.getmPostTime();
        String mUrlPicture = contentYouTube.getmUrlPicture();

        Intent intent = new Intent(getApplicationContext(), DetailContentYouTube.class);

        Bundle dataSong = new Bundle();
        dataSong.putString("nameSong", mNameSong);
        dataSong.putString("TimeSong", mTimeSong);
        dataSong.putString("nameChannel", nameChannel);
        dataSong.putInt("view", mView);
        dataSong.putLong("postTime", mPostTime);
        dataSong.putString("picture", mUrlPicture);
        intent.putExtra("data", dataSong);

        startActivity(intent);
      }
    });


    mContainer.setOnChildFocusListener(new OnChildFocusListener() {
      @Override
      public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return false;
      }

      @Override
      public void onRequestChildFocus(View child, View focused) {
        if (child.getId() == R.id.fragment_menu_icon) {
//          toggleMiddleMenu(false);
        }
      }
    });

    mContainer.setOnFocusSearchListener(new OnFocusSearchListener() {
      @Override
      public View onFocusSearch(View focused, int direction) {
        if (focused.getId() == R.id.item_left_menu_icon && direction == View.FOCUS_RIGHT) {
          toggleMiddleMenu(true);
          return leftMenuFragment.getSelectedView();
        } else if (focused.getId() == R.id.item_content_show && direction == View.FOCUS_LEFT) {
           toggleMiddleMenu(true);
          return leftMenuFragment.getSelectedView();
        }
        else if (focused.getId() == R.id.item_left_menu_icon && (int) focused.getTag() == R.drawable.search && direction == View.FOCUS_UP){
          return focused;
        }else if (focused.getId() == R.id.item_left_menu_icon && (int) focused.getTag() == R.drawable.setting && direction == View.FOCUS_DOWN){
          return focused;
        }
        return null;
      }
    });
  }

  private void toggleMiddleMenu(boolean show) {
    final int currentMargin = ((ViewGroup.MarginLayoutParams) fragment_left_menu.getLayoutParams()).rightMargin;
    final int slideDestination = show ? 0 : getResources().getDimensionPixelSize(R.dimen.margin_right);
    final int slideDelta = slideDestination - currentMargin;

    Animation toggleAnimation = new Animation() {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) fragment_left_menu.getLayoutParams();
        layoutParams.rightMargin = (int) (currentMargin + slideDelta * interpolatedTime);
        fragment_left_menu.setLayoutParams(layoutParams);
      }
    };
    toggleAnimation.setDuration(300);
    mContainer.startAnimation(toggleAnimation);
  }
}