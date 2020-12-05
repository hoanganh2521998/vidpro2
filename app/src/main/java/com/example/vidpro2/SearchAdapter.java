package com.example.vidpro2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<Video> {
    private List<Video> list;

    public SearchAdapter(@NonNull Context context, @NonNull List<Video> listVideo) {
        super(context, 0,listVideo);
        list = new ArrayList<>(listVideo);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return videoFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null )
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_layout,parent,false);

        }
        TextView title = convertView.findViewById(R.id.tv_name_searh);
        ImageView img = convertView.findViewById(R.id.img_list_searh);

        Video videoDescription = getItem(position);
        if(convertView!= null)
        {
            title.setText(videoDescription.getTitle());
            Picasso.get().load(videoDescription.getAvatar()).into(img);
        }
        return convertView;
    }

    private Filter videoFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Video> suggest = new ArrayList<>();
            if(constraint == null || constraint.length()==0 )
            {
                suggest.addAll(list);
            }
            else
            {
                String filer = constraint.toString().toLowerCase().trim();
                for (Video item :list)
                {
                    if(item.getTitle().toLowerCase().contains(filer))
                    {
                        suggest.add(item);
                    }
                }
            }
            results.values = suggest;
            results.count = suggest.size();
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Video)resultValue).getTitle();
        }
    };
}
