package com.example.admin.youtubedemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LeftMenuFragment extends HeadersSupportFragment {

  private static final String TAG = LeftMenuFragment.class.getSimpleName();
  public static String KEY_MENU = "KEY";
  private OnClickItemSelectedListener mListener;

  private ArrayObjectAdapter mRowsAdapter;
  private int mCurrentPosition;

  private List<MenuDTO> mMenuDtoS;
  private List<MenuItem> mMenuItems;


  public void setOnItemSelectedListener(OnClickItemSelectedListener listener) {
    mListener = listener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null && getArguments().containsKey(KEY_MENU)) {
      mMenuDtoS = getArguments().getParcelableArrayList(KEY_MENU);
    }

    setPresenterSelector(new PresenterSelector() {
      @Override
      public Presenter getPresenter(Object item) {
        return new LeftMenuPresenter(getActivity());
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    setupHeaderAdapter();

    getVerticalGridView().setPadding(0, 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
          int position = (int) row.getId();
          mListener.onClickListener(position);
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        Log.e(TAG, "onHeaderSelected: " );
        if (row != null) {
          int position = (int) row.getId();
          if (mCurrentPosition != position && position != -1) {
            ////
//            mMenuItems.get(mCurrentPosition).isChosen = false;
//            mRowsAdapter.notifyItemRangeChanged(mCurrentPosition, 1);
            mCurrentPosition = position;
            ////
//            mMenuItems.get(mCurrentPosition).isChosen = true;
//            mRowsAdapter.notifyItemRangeChanged(mCurrentPosition, 1);

            if (mListener != null)
              mListener.onClickListener(mCurrentPosition);
          }
        }
      }
    });

//    getVerticalGridView().post(new Runnable() {
//      @Override
//      public void run() {
//        ((MainActivity) getActivity()).focus();
//      }
//    });

  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }

  private void setupHeaderAdapter() {
    mRowsAdapter = new ArrayObjectAdapter();
    mMenuItems = new ArrayList<>();

    MenuItem menuItem = new MenuItem(1, "Trang chủ");
    mMenuItems.add(menuItem);
    mRowsAdapter.add(new ListRow(menuItem, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    MenuItem menuItem1 = new MenuItem(2, "Đề xuất");
    mMenuItems.add(menuItem1);
    mRowsAdapter.add(new ListRow(menuItem1, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    MenuItem menuItem2 = new MenuItem(3, "Thịnh hành");
    mMenuItems.add(menuItem2);
    mRowsAdapter.add(new ListRow(menuItem2, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    MenuItem menuItem3 = new MenuItem(3, "Âm nhạc");
    mMenuItems.add(menuItem3);
    mRowsAdapter.add(new ListRow(menuItem3, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    MenuItem menuItem4 = new MenuItem(3, "Giải trí");
    mMenuItems.add(menuItem4);
    mRowsAdapter.add(new ListRow(menuItem4, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    setAdapter(mRowsAdapter);

    getVerticalGridView().post(new Runnable() {
      @Override
      public void run() {
        ((MainActivity)getActivity()).focus();
      }
    });
  }

  private void customSetBackground() {
    try {
      Class clazz = HeadersSupportFragment.class;
      Method m = clazz.getDeclaredMethod("setBackgroundColor", Integer.TYPE);
      m.setAccessible(true);
      m.invoke(this, Color.TRANSPARENT);
    } catch (Exception e) {

    }
  }

  public interface OnClickItemSelectedListener {
    void onClickListener(int id);
  }

}
