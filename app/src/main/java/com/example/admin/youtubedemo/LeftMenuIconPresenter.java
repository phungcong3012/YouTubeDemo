package com.example.admin.youtubedemo;

import android.content.Context;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftMenuIconPresenter extends RowHeaderPresenter {
  private Context context;

  public LeftMenuIconPresenter(Context context) {
    this.context = context;
  }

  @Override
  public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.item_left_menu_icon, parent, false);
    return new ViewHolderMenuIcon(view);
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    super.onBindViewHolder(viewHolder, item);
    LeftMenuIcon leftMenuIcon = (LeftMenuIcon) ((ListRow) item).getHeaderItem();

    final ViewHolderMenuIcon viewHolderMenuIcon = (ViewHolderMenuIcon) viewHolder;

    viewHolderMenuIcon.ivIcon.setImageDrawable(context.getResources().getDrawable(leftMenuIcon.getmIcon()));

    viewHolderMenuIcon.view.setTag(leftMenuIcon.getmIcon());

    //
  }

  class ViewHolderMenuIcon extends ViewHolder {

    @BindView(R.id.img_icon)
    ImageView ivIcon;

    public ViewHolderMenuIcon(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
