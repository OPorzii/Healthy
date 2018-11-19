package com.example.a59070103.healthy.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a59070103.healthy.R;

import java.util.ArrayList;

public class CommentAdapter extends ArrayAdapter<Comment> {

    ArrayList<Comment> comments;
    Context context;

    public CommentAdapter(Context context, int resouce, ArrayList<Comment> objects){
        super(context, resouce, objects);
        this.comments = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.fragment_comment_item,
                parent,
                false);


        TextView textPostid = view.findViewById(R.id.txt_postid);
        TextView textId = view.findViewById(R.id.txt_id);
        TextView textBody = view.findViewById(R.id.txt_boy);
        TextView textName = view.findViewById(R.id.txt_name);
        TextView textEmail = view.findViewById(R.id.txt_email);

        Comment row = comments.get(position);
        textPostid.setText(""+row.getPostId());
        textId.setText(""+ row.getId());
        textBody.setText(""+ row.getBody());
        textName.setText(""+row.getName());
        textEmail.setText(""+row.getEmail());
        return view;
    }
}
