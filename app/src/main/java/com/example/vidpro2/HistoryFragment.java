package com.example.vidpro2;

import android.accounts.NetworkErrorException;
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

import com.example.vidpro2.databinding.FragmentHistoryBinding;

import java.util.Collections;
import java.util.List;

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding historyBinding;
    List<Video> video;
    AdapterVideoHis adapterVideo;
    SqlHistory sqlHistory;

    public static HistoryFragment newInstance(){
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        historyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        sqlHistory = new SqlHistory(getContext());
        video = sqlHistory.getAllHistory();
        Collections.reverse(video);
        adapterVideo = new AdapterVideoHis(video);
        RecyclerView.LayoutManager layoutManage3 = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        historyBinding.rvListVideoHis.setLayoutManager(layoutManage3);
        historyBinding.rvListVideoHis.setAdapter(adapterVideo);
//        adapterVideoHis.setIonClickVideoHis(new IonClickVideoHis() {
//            @Override
//            public void onClickVideoHis(String name, String url, String ava) {
//                Intent intent = new Intent(getActivity().getBaseContext(), PlayVideo.class);
//                intent.putExtra("url", url);
//                intent.putExtra("name", name);
//                intent.putExtra("ava", ava);
//                //  intent.putParcelableArrayListExtra("url", (ArrayList<? extends Parcelable>) response.body());
//                startActivity(intent);
//            }
//        });

        return historyBinding.getRoot();
    }
}
