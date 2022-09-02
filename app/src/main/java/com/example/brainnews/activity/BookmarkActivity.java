package com.example.brainnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainnews.R;

public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    View kolomback;
    TextView text_bookmark;
    ImageButton arrow_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        recyclerView = findViewById(R.id.bookmark_rv);
        kolomback = findViewById(R.id.kolomback);
        text_bookmark = findViewById(R.id.text_bookmark);
        arrow_back = findViewById(R.id.arrow_back);

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BookmarkActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
