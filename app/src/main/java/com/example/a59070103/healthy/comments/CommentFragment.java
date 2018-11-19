package com.example.a59070103.healthy.comments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070103.healthy.R;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentFragment  extends Fragment {

    ArrayList<Comment> commentList;
    private ListView commentShowList;
    private OkHttpClient client;
    private CommentAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        int viewId = getArguments().getInt("postId");



        commentList = new ArrayList<>();
        adapter = new CommentAdapter(getActivity(), R.layout.fragment_comment_item, commentList);

        commentShowList = getActivity().findViewById(R.id.comment_list);
        commentShowList.setAdapter(adapter);

        commentList.clear();
        String urlPost = "https://jsonplaceholder.typicode.com/posts/"+viewId+"/comments";
        getPostview(urlPost);


        final Button backBtn = getActivity().findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }


    private void getPostview(String url){


        Request request = new Request.Builder()
                .url(url)
                .build();
        client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String res = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(res);
                                Gson gson = new Gson();

                                TypeToken<List<Comment>> token = new TypeToken<List<Comment>>(){};
                                ArrayList<Comment> commentsData = gson.fromJson(jsonArray.toString(), token.getType());
                                commentList.addAll(commentsData);
                                adapter.notifyDataSetChanged();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }

            }
        });



    }


}
