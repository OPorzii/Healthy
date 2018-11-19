package com.example.a59070103.healthy.postview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.a59070103.healthy.R;
import com.example.a59070103.healthy.comments.CommentFragment;
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

public class PostFragment extends Fragment {

    ArrayList<Post> postList;
    private ListView postShowList;
    private OkHttpClient client;
    private PostAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        postList = new ArrayList<>();
        adapter = new PostAdapter(getActivity(), R.layout.fragment_post_item, postList);



        postShowList = getActivity().findViewById(R.id.post_list);
        postShowList.setAdapter(adapter);

        postList.clear();



        postShowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post current = postList.get(position);

                CommentFragment fragment = new CommentFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("postId", current.getId());
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        final Button backBtn = getActivity().findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        String urlPost = "https://jsonplaceholder.typicode.com/posts";
        getPostview(urlPost);


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

                                TypeToken<List<Post>> token = new TypeToken<List<Post>>(){};
                                ArrayList<Post> postData = gson.fromJson(jsonArray.toString(), token.getType());
                                postList.addAll(postData);
                                adapter.notifyDataSetChanged();
                                for(Post post : postList){
                                    Log.d("TEST22",post.getTitle()+"\n");
                                }

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
