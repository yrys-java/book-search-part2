package com.example.booksearchpart2;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_RESULTS = 3;

    private ArrayList<Item> volumeInfoList;
    private ProgressDialog dialog;
    private ApiService api;
    private BooksAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = RetroClient.getApiService();

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading...");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BooksAdapter(itemClickListener);
        recyclerView.setAdapter(adapter);

    }

    BooksAdapter.OnItemClickListener itemClickListener = book -> {
        String url = book.getVolumeInfo().getPreviewLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void doSearchBook(final String query) {
        dialog.show();

        Call<BookResponse> call = api.getMyApi(query, MAX_RESULTS);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                dialog.dismiss();

                if (response.isSuccessful()) {
                    volumeInfoList = response.body().getItems();
                    recyclerView.smoothScrollToPosition(0);
                    adapter.setBookData(volumeInfoList);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("query", query);
            doSearchBook(query);
        }
    }
}
