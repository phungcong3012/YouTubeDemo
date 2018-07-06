package com.example.admin.youtubedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LeftMenuIconFragment extends HeadersSupportFragment {
  private static final String TAG = LeftMenuIconFragment.class.getSimpleName();
  private ArrayObjectAdapter arrayObjectAdapter;
  private List<LeftMenuIcon> leftMenuIcons;

  private OnClickItemListener itemListener;

  public void setLeftMenuIconFragment(OnClickItemListener itemListener) {
    this.itemListener = itemListener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setPresenterSelector(new PresenterSelector() {
      @Override
      public Presenter getPresenter(Object item) {
        return new LeftMenuIconPresenter(getActivity());
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setUpData();
    getVerticalGridView().setPadding(0, 0, 0, 0);

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        LeftMenuIcon icon = (LeftMenuIcon)row.getHeaderItem();

        itemListener.OnClickView(icon.getIdLeftMenuIcon());

        if(arrayObjectAdapter.indexOf(row) == arrayObjectAdapter.size() -3) {
          UpdateAcount();
        }else if (arrayObjectAdapter.indexOf(row) == arrayObjectAdapter.size() -1){
          updateData();
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        Log.e(TAG, "onHeaderSelected: " );
      }
    });

//    getVerticalGridView().post(new Runnable() {
//      @Override
//      public void run() {
//        ((MainActivity)getActivity()).focus();
//      }
//    });
  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }

  private void setUpData() {
    arrayObjectAdapter = new ArrayObjectAdapter();
    leftMenuIcons = new ArrayList<>();

    LeftMenuIcon iconSearch = new LeftMenuIcon(1,"Search", R.drawable.search);
    leftMenuIcons.add(iconSearch);
    arrayObjectAdapter.add(new ListRow(iconSearch, new ArrayObjectAdapter(new LeftMenuIconPresenter(getActivity()))));

    LeftMenuIcon iconHome = new LeftMenuIcon(2,"Home", R.drawable.home);
    leftMenuIcons.add(iconHome);
    arrayObjectAdapter.add(new ListRow(iconHome, new ArrayObjectAdapter(new LeftMenuIconPresenter(getActivity()))));

    LeftMenuIcon iconAccount = new LeftMenuIcon(3,"Account", R.drawable.account);
    leftMenuIcons.add(iconAccount);
    arrayObjectAdapter.add(new ListRow(iconAccount, new ArrayObjectAdapter(new LeftMenuIconPresenter(getActivity()))));

    LeftMenuIcon iconMessger = new LeftMenuIcon(4,"Messger", R.drawable.message);
    leftMenuIcons.add(iconMessger);
    arrayObjectAdapter.add(new ListRow(iconMessger, new ArrayObjectAdapter(new LeftMenuIconPresenter(getActivity()))));

    LeftMenuIcon iconSetting = new LeftMenuIcon(5,"Setting", R.drawable.setting);
    leftMenuIcons.add(iconSetting);
    arrayObjectAdapter.add(new ListRow(iconSetting, new ArrayObjectAdapter(new LeftMenuIconPresenter(getActivity()))));

    setAdapter(arrayObjectAdapter);
  }

  private void updateData(){
    int lastIndex = arrayObjectAdapter.size() - 1;
    Row lastRow = (ListRow)arrayObjectAdapter.get(lastIndex);
    LeftMenuIcon icon = (LeftMenuIcon) lastRow.getHeaderItem();
    icon.setmIcon(R.drawable.home);
    arrayObjectAdapter.notifyItemRangeChanged(lastIndex,1);
  }

  private void UpdateAcount(){
    int lastIndex = arrayObjectAdapter.size() -3;
    Row row = (Row) arrayObjectAdapter.get(lastIndex);
    LeftMenuIcon icon = (LeftMenuIcon) row.getHeaderItem();
    icon.setmIcon(R.drawable.search);
    arrayObjectAdapter.notifyItemRangeChanged(lastIndex,1);
  }

  public interface OnClickItemListener{
    void OnClickView(int id);
  }
}
