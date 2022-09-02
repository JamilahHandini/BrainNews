package com.example.brainnews.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainnews.adapter.MainArticleAdapter;
import com.example.brainnews.model.NewsViewModel;
import com.example.brainnews.utils.OnRecyclerViewItemClickListener;

import com.example.brainnews.R;
import com.example.brainnews.adapter.MainArticleAdapter;
import com.example.brainnews.model.Article;
import com.example.brainnews.model.NewsViewModel;
import com.example.brainnews.model.ResponseModel;
import com.example.brainnews.utils.OnRecyclerViewItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener, AdapterView.OnItemSelectedListener {

    private NewsViewModel newsViewModel;
    private MainArticleAdapter mainArticleAdapter;
    private Spinner spinnerSearch;
    private TextView head_line;
    private RelativeLayout relativeSpinner;
    private ImageButton open_bookmark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_beranda);
        relativeSpinner = findViewById(R.id.relative_spinner);
        head_line = findViewById(R.id.head_line_n);
        open_bookmark = findViewById(R.id.ic_bookmark);
        mainArticleAdapter = new MainArticleAdapter();
        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
        newsViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getResponseModelLiveData().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if(responseModel != null){
                    mainArticleAdapter.setResults(responseModel.getArticles());
                }
            }
        });

        RecyclerView mainRecycler = findViewById(R.id.activity_main_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainRecycler.setLayoutManager(linearLayoutManager);

        mainRecycler.setAdapter(mainArticleAdapter);


        newsViewModel.topHeadlines("general", "id");

        spinnerSearch = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(this,R.array.categories,android.R.layout.simple_spinner_item);
        spinnerSearch.setAdapter(spinnerAdapter);
        spinnerSearch.setOnItemSelectedListener(this);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2){
                    head_line.setText("Latest News");
                    spinnerSearch.setVisibility(View.GONE);
                    newsViewModel.getNewsSearch(query);
                    relativeSpinner.setVisibility(View.GONE);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                head_line.setText("Latest News");
//                spinnerSearch.setVisibility(View.GONE);
//                relativeSpinner.setVisibility(View.GONE);
                newsViewModel.getNewsSearch(newText);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                head_line.setText("Top Headlines");
                spinnerSearch.setVisibility(View.VISIBLE);
                relativeSpinner.setVisibility(View.VISIBLE);
                int i = spinnerSearch.getSelectedItemPosition();
                switch(i){
                    case 0:
                        newsViewModel.topHeadlines("general", "id");
                        break;
                    case 1:
                        newsViewModel.topHeadlines("business", "id");
                        break;
                    case 2:
                        newsViewModel.topHeadlines("entertainment", "id");
                        break;
                    case 3:
                        newsViewModel.topHeadlines("health", "id");
                        break;
                    case 4:
                        newsViewModel.topHeadlines("science", "id");
                        break;
                    case 5:
                        newsViewModel.topHeadlines("sports", "id");
                        break;
                    case 6:
                        newsViewModel.topHeadlines("technology", "id");
                        break;
                }
                return false;
            }
        });

        open_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.relative_layout:
                Article article = (Article) view.getTag();
                if (!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent detailActivity = new Intent(this, DetailActivity.class);
                    detailActivity.putExtra("url", article.getUrl());
                    detailActivity.putExtra("title", article.getTitle());
                    detailActivity.putExtra("logo",article.getUrlToImage());
                    detailActivity.putExtra("publisher",article.getSource().getName());
                    detailActivity.putExtra("waktu",article.getPublishedAt());
                    detailActivity.putExtra("deskripsi",article.getDescription());
                    detailActivity.putExtra("author",article.getAuthor());
                    startActivity(detailActivity);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                newsViewModel.topHeadlines("general", "id");
                break;
            case 1:
                newsViewModel.topHeadlines("business", "id");
                break;
            case 2:
                newsViewModel.topHeadlines("entertainment", "id");
                break;
            case 3:
                newsViewModel.topHeadlines("health", "id");
                break;
            case 4:
                newsViewModel.topHeadlines("science", "id");
                break;
            case 5:
                newsViewModel.topHeadlines("sports", "id");
                break;
            case 6:
                newsViewModel.topHeadlines("technology", "id");
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public boolean onCreateOptionsMenu (Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        final SearchView searchView = (SearchView) menu. findItem(R.id.search_news).getActionView();
//        MenuItem searchMenuItem = menu.findItem(R.id.search_news);
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setQueryHint("Cari Berita");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if (query.length() > 2){
//                    textView.setText("Latest News");
//                    spinnerSearch.setVisibility(View.GONE);
//                    newsViewModel.getNewsSearch(query);
//                    relativeSpinner.setVisibility(View.GONE);
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                textView.setText("Latest News");
//                spinnerSearch.setVisibility(View.GONE);
//                relativeSpinner.setVisibility(View.GONE);
//                newsViewModel.getNewsSearch(newText);
//                return false;
//            }
//        });
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                textView.setText("Top Headlines");
//                spinnerSearch.setVisibility(View.VISIBLE);
//                relativeSpinner.setVisibility(View.VISIBLE);
//                int i = spinnerSearch.getSelectedItemPosition();
//                switch(i){
//                    case 0:
//                        newsViewModel.topHeadlines("id");
//                        break;
//                    case 1:
//                        newsViewModel.topHeadlines("jp");
//                        break;
//                    case 2:
//                        newsViewModel.topHeadlines("kr");
//                        break;
//                    case 3:
//                        newsViewModel.topHeadlines("us");
//                        break;
//                    case 4:
//                        newsViewModel.topHeadlines("sg");
//                        break;
//                }
//                return false;
//            }
//        });
//        searchMenuItem.getIcon().setVisible(false, false);
//        return true;
//    }


}