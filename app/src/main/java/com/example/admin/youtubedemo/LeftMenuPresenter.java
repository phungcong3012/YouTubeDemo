package com.example.admin.youtubedemo;

import android.content.Context;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftMenuPresenter extends RowHeaderPresenter {
  private Context context;

  public LeftMenuPresenter(Context context) {
    this.context = context;
  }

  @Override
  public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new MenuItemHolder(LayoutInflater.from(context)
        .inflate(R.layout.item_left_menu, parent, false));
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    super.onBindViewHolder(viewHolder, item);

    MenuItem menuItem = (MenuItem) ((ListRow) item).getHeaderItem();
    final MenuItemHolder menuItemHolder = (MenuItemHolder) viewHolder;

    menuItemHolder.tvContentMenu.setText(menuItem.getName());

    menuItemHolder.view.setTag(menuItem);
    menuItemHolder.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
        MenuItem data = (MenuItem) view.getTag();
        if (data.isChosen) {
          menuItemHolder.tvContentMenu.setSelected(true);
        } else {
          menuItemHolder.tvContentMenu.setSelected(b);
        }
      }
    });

  }

  class MenuItemHolder extends ViewHolder {
    TextView tvContentMenu;

    public MenuItemHolder(View view) {
      super(view);
      tvContentMenu = (TextView) view.findViewById(R.id.tv_content_menu);
      tvContentMenu.setPivotX(0);
    }

    public void zoom(boolean zoomed) {
      if (zoomed) {
        tvContentMenu.animate().scaleX(1f).scaleY(1f).setDuration(150).start();
      } else {
        tvContentMenu.animate().scaleX(0.8f).scaleY(0.8f).setDuration(150).start();
      }
    }
  }
}
