package com.example.a59070103.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.a59070103.healthy.postview.Post;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestRun  extends AppCompatActivity {

    TextView textRes;
    private OkHttpClient client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);


        textRes = findViewById(R.id.textRes);

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
                    TestRun.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            textRes.setText(res);
                            try {
                                JSONArray jsonArray = new JSONArray(res);
                                Gson gson = new Gson();

                                TypeToken<List<Post>> token = new TypeToken<List<Post>>(){};
                                List<Post> postList = gson.fromJson(jsonArray.toString(), token.getType());
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
