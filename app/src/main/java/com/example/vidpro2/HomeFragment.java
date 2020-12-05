package com.example.vidpro2;

import android.content.Intent;
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

import com.example.vidpro2.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    FragmentHomeBinding homeBinding;
    AdapterVideo adapterVideo;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        APIManage service = RetrofitClient.getClient(Url.urlItemOne).create(APIManage.class);
        service.getCateOne().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                adapterVideo = new AdapterVideo(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);
                homeBinding.rvListVideo.setLayoutManager(layoutManager);
                homeBinding.rvListVideo.setAdapter(adapterVideo);
                adapterVideo.setIonClickVideo(new IonClickVideo() {
                    @Override
                    public void onClickVideo(String name, String ava, String url, String des) {
                        Intent intent = new Intent(getActivity().getBaseContext(), PlayVideo.class);
                        intent.putExtra("url", url);
                        intent.putExtra("name", name);
                        intent.putExtra("ava", ava);
                        //  intent.putParcelableArrayListExtra("url", (ArrayList<? extends Parcelable>) response.body());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
        return homeBinding.getRoot();
    }
}
