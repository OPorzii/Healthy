package com.example.a59070103.healthy.postview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070103.healthy.R;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    ArrayList<Post> posts;
    Context context;

    public PostAdapter(Context context, int resouce, ArrayList<Post> objects){
        super(context, resouce, objects);
        this.posts = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.fragment_post_item,
                parent,
                false);


        TextView textId = view.findViewById(R.id.txt_postid);
        TextView textTitle = view.findViewById(R.id.txt_title);
        TextView textBody = view.findViewById(R.id.txt_boy);

        Post row = posts.get(position);
        textId.setText(""+row.getId());
        textTitle.setText(""+ row.getTitle());
        textBody.setText(""+ row.getBody());
        Log.d("ADTEST",row.getTitle()+"\n");
        return view;
    }
}
