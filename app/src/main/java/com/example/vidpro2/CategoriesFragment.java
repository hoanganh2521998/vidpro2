package com.example.vidpro2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vidpro2.databinding.FragmentCategoriesBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesFragment extends Fragment {
    FragmentCategoriesBinding categoriesBinding;
    List<Categories> categoriesList;
    AdapterCategories adapterCategories;

    public static CategoriesFragment newInstance(){
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        categoriesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);
        APIManage service =RetrofitClient.getClient(Url.urlCategories).create(APIManage.class);
        service.getCate().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                adapterCategories = new AdapterCategories(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);
                categoriesBinding.rvListCate.setLayoutManager(layoutManager);
                categoriesBinding.rvListCate.setAdapter(adapterCategories);
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });
        return categoriesBinding.getRoot();
    }
}
