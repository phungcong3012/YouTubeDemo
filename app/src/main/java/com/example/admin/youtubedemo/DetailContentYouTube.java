package com.example.admin.youtubedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailContentYouTube extends Activity {

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

  @BindView(R.id.spinner)
  Spinner spinnerLoaiPhim;

  final String loaiPhim[]={"Hanh Dong","Kiem Hiep","Tinh cam"};


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_content_you_tube);

    ButterKnife.bind(this);

    Intent intent = getIntent();
    Bundle bundle =intent.getBundleExtra("data");

    String nameSong = bundle.getString("nameSong");
    String TimeSong = bundle.getString("TimeSong");
    String nameChanel = bundle.getString("nameChannel");
    String picture = bundle.getString("picture");
    int view = bundle.getInt("view");
    long postTime = bundle.getLong("postTime");

    tvNameSong.setText(nameSong);
    tvTimeSong.setText(TimeSong);
    tvNameChanel.setText(nameChanel);
    tvView.setText(view+"");
    tvPostTime.setText(postTime+"");
    Picasso.with(getApplication()).load(picture).resize(200, 100).into(imgPicture);


    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplication(),android.R.layout.simple_spinner_item,loaiPhim);
    adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
    spinnerLoaiPhim.setAdapter(adapter1);
    spinnerLoaiPhim.setOnItemSelectedListener(new MyItemSeletect());
  }
  public class MyItemSeletect implements AdapterView.OnItemSelectedListener{
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      Toast.makeText(DetailContentYouTube.this, ""+loaiPhim[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
  }

}
