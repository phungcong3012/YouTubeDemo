package com.example.admin.youtubedemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.BaseOnItemViewSelectedListener;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContenFragment extends BaseRowsFragment {

  private static final String TAG = ContenFragment.class.getSimpleName();
  private List<ContentYouTube> mContentYouTubes;

  private onClickContenItem mListener;
    BackgroundManager backgroundManager;


  public void setOnClickContenItem(onClickContenItem mListener){
    this.mListener = mListener;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setupHeaderAdapter();

    // getVerticalGridView().setColumnWidth(2);
    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override
      public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {


        if (item instanceof String){

          Intent intent = new Intent(getActivity(),ErrorActivity.class);
          startActivity(intent);

        }else if (item instanceof ContentYouTube){

          mListener.onClickContent((int)row.getId(), (ContentYouTube) item);

        }
      }
    });

    setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
      @Override
      public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        Log.e(TAG, "onItemSelected: " );
      }
    });
  }

  private void setupHeaderAdapter() {
    mContentYouTubes = new ArrayList<>();

    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve1", "Am nhac1", 2001, 3, "http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));
    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve2", "Am nhac2", 2002, 3, "http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));
    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve3", "Am nhac3",2003,3,"http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));
    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve4", "Am nhac4", 2004, 3, "http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));
    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve5", "Am nhac5", 2005, 3, "http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));
    mContentYouTubes.add(new ContentYouTube("2:30", "Noi ay con tim ve6", "Am nhac6", 2006, 3, "http://file.vforum.vn/hinh/2018/02/top-nhung-hinh-anh-hoa-dep-bong-hoa-dep-nhat-17.jpg"));

    getRowsAdapter().clear();


    for (int i = 0; i < 5; i++) {
      ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new ProgramGridPresenter(getActivity(),false));
     // Collections.shuffle(mContentYouTubes); // sao tron phan tu trong mang
      arrayObjectAdapter.addAll(0,mContentYouTubes);
      HeaderItem headerItem = new HeaderItem("Row".concat(i+""));
      getRowsAdapter().add(new ListRow(headerItem,
          arrayObjectAdapter));
    }
  }

  public interface onClickContenItem{
    void onClickContent(long id,ContentYouTube contentYouTube);
  }
}
