package com.example.brainnews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brainnews.R;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {


    private String tittle,logo,publisher,waktu,url,author,deskripsi;
    private TextView tittleText,publisherText,waktuText,urlText,authorText,deskripsiText;
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_beranda);
        tittle = getIntent().getStringExtra("title");
        logo = getIntent().getStringExtra("logo");
        publisher = getIntent().getStringExtra("publisher");
        waktu = getIntent().getStringExtra("waktu");
        url = getIntent().getStringExtra("url");
        author = getIntent().getStringExtra("author");
        deskripsi = getIntent().getStringExtra("deskripsi");

        tittleText = findViewById(R.id.judul_berita);
        publisherText = findViewById(R.id.nama_publisher);
        waktuText = findViewById(R.id.tanggal);
        urlText = findViewById(R.id.url_berita);
        authorText = findViewById(R.id.author);
        deskripsiText = findViewById(R.id.isi_konten);
        logoImage = findViewById(R.id.foto_konten);
        //Jika logo kosong
        if(TextUtils.isEmpty(logo)){
            logoImage.setImageResource(R.drawable.noimage);
        }else {
            Picasso.get().load(logo).into(logoImage);
        }
        tittleText.setText(tittle);

        publisherText.setText(publisher);
        waktuText.setText(waktu);
        urlText.setText(url);
        authorText.setText(author);
        deskripsiText.setText(deskripsi);
    }

    public void openInBrowser(View view) {
        Intent webActivity = new Intent(this, WebActivity.class);
        webActivity.putExtra("url", url);
        startActivity(webActivity);
    }
}
