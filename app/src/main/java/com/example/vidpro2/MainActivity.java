package com.example.vidpro2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView btnHot, btnCate, btnHome, btnSearch, btnHis, btnHotRed, btnCateRed, btnHomeRed, btnHisRed;
    AutoCompleteTextView imSea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnCate = findViewById(R.id.btnCate);
        btnHot = findViewById(R.id.btnHot);
        btnHome = findViewById(R.id.btnHome);
        btnHis = findViewById(R.id.btnHistory);
        btnCateRed = findViewById(R.id.btnCateRed);
        btnHisRed = findViewById(R.id.btnHistoryRed);
        btnHotRed = findViewById(R.id.btnHotRed);
        btnHomeRed = findViewById(R.id.btnHomeRed);
        //btnSearch = findViewById(R.id.imSearch);
        imSea = findViewById(R.id.tvSearch);

        APIManage service = RetrofitClient.getClient(Url.urlVideoHot).create(APIManage.class);
        service.getHot().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, final Response<List<Video>> response) {
                SearchAdapter searchAdapter = new SearchAdapter(getBaseContext(), response.body());
                imSea.setAdapter(searchAdapter);
                imSea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getBaseContext(), PlayVideo.class);
                        intent.putExtra("url",response.body().get(i).getFileMp4());
                        intent.putExtra("name", response.body().get(i).getTitle());
                        //  intent.putParcelableArrayListExtra("url", (ArrayList<? extends Parcelable>) response.body());
                        startActivity(intent);
                        imSea.setText("");
                    }
                });
            }


            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
    });


        getFragment(HomeFragment.newInstance());
        btnCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCateRed.setVisibility(View.VISIBLE);
                btnCate.setVisibility(View.GONE);
                btnHis.setVisibility(View.VISIBLE);
                btnHisRed.setVisibility(View.GONE);
                btnHomeRed.setVisibility(View.GONE);
                btnHome.setVisibility(View.VISIBLE);
                btnHotRed.setVisibility(View.GONE);
                btnHot.setVisibility(View.VISIBLE);
                getFragment(CategoriesFragment.newInstance());
            }
        });
        btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHotRed.setVisibility(View.VISIBLE);
                btnHot.setVisibility(View.GONE);
                btnHome.setVisibility(View.VISIBLE);
                btnHomeRed.setVisibility(View.GONE);
                btnCate.setVisibility(View.VISIBLE);
                btnCateRed.setVisibility(View.GONE);
                btnHis.setVisibility(View.VISIBLE);
                btnHisRed.setVisibility(View.GONE);
                getFragment(VideoFragment.newInstance());
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHomeRed.setVisibility(View.VISIBLE);
                btnHome.setVisibility(View.GONE);
                btnHis.setVisibility(View.VISIBLE);
                btnHisRed.setVisibility(View.GONE);
                btnCate.setVisibility(View.VISIBLE);
                btnCateRed.setVisibility(View.GONE);
                btnHot.setVisibility(View.VISIBLE);
                btnHotRed.setVisibility(View.GONE);
                getFragment(HomeFragment.newInstance());
            }
        });
        btnHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHisRed.setVisibility(View.VISIBLE);
                btnHis.setVisibility(View.GONE);
                btnHot.setVisibility(View.VISIBLE);
                btnHotRed.setVisibility(View.GONE);
                btnHome.setVisibility(View.VISIBLE);
                btnHomeRed.setVisibility(View.GONE);
                btnCate.setVisibility(View.VISIBLE);
                btnCateRed.setVisibility(View.GONE);
                getFragment(HistoryFragment.newInstance());
            }
        });

    }
    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
