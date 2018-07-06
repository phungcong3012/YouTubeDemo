package com.example.admin.youtubedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Giga on 12/11/2017.
 */

public class ProgramGridPresenter extends AbstractPresenter {
  private int mCoverWidth;
  private int mCoverHeight;

  public ProgramGridPresenter(Context context, boolean isAlignFirstItem) {
    super(context, isAlignFirstItem);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new ProgramItemHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_content_show, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    final ProgramItemHolder viewHolderYoutube = (ProgramItemHolder) viewHolder;

    ContentYouTube contentYouTube = (ContentYouTube) item;

    viewHolderYoutube.tvTimeSong.setText(contentYouTube.getmTimeSong());
    viewHolderYoutube.tvNameSong.setText(contentYouTube.getmNameSong());
    viewHolderYoutube.tvNameChanel.setText(contentYouTube.getmChannel());
    viewHolderYoutube.tvPostTime.setText(contentYouTube.getmPostTime() + "");
    viewHolderYoutube.tvView.setText(contentYouTube.getmView() + "");
    Picasso.with(getContext())
        .load(contentYouTube.getmUrlPicture())
        .resize(200, 100)
        .into(viewHolderYoutube.imgPicture);
    // Picasso.with(getContext()).load(contentYouTube.getmUrlPicture()).fit().into(viewHolderYoutube.imgPicture);
    viewHolderYoutube.view.setTag(contentYouTube);
  }

  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {
  }

  class ProgramItemHolder extends ViewHolder {

    @BindView(R.id.tv_time_song)
    TextView tvTimeSong;

    @BindView(R.id.tv_name_song)
    TextView tvNameSong;

    @BindView(R.id.tv_name_channel)
    TextView tvNameChanel;

    @BindView(R.id.tv_post_time)
    TextView tvPostTime;

    @BindView(R.id.tv_view)
    TextView tvView;

    @BindView(R.id.img_picture_content)
    ImageView imgPicture;

    public ProgramItemHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
