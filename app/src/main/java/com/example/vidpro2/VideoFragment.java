package com.example.vidpro2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vidpro2.databinding.FragmentVideoBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends Fragment {
    FragmentVideoBinding videoBinding;
    List<Video> videoList;
    AdapterVideo adapterVideo;
    SqlHistory sqlHistory;


    public static VideoFragment newInstance(){
        return new VideoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        videoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        APIManage service = RetrofitClient.getClient(Url.urlVideoHot).create(APIManage.class);
        service.getHot().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, final Response<List<Video>> response) {
                adapterVideo = new AdapterVideo(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);
                videoBinding.rvListVideo.setLayoutManager(layoutManager);
                videoBinding.rvListVideo.setAdapter(adapterVideo);
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
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return videoBinding.getRoot();
    }
}
