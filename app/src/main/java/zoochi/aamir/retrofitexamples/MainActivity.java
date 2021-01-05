package zoochi.aamir.retrofitexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    TextView result;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.tvresult);
        //http://jsonplaceholder.typicode.com/posts


        //for logcate http header data
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

       // getPost();
        //getComments();
       // createPost();
        updatePost();
       // deletePost();
    }



    private void getComments() {
        Call<List<Comments>> call=jsonPlaceHolderApi.getComments(3);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                List<Comments> comments=response.body();
                for(Comments c: comments){

                    String content = "";
                    content += "ID: " + c.getId() + "\n";
                    content += "post ID: " + c.getPostId() + "\n";
                    content += "Email: " + c.getEmail() + "\n";
                    content += "name: " + c.getName() + "\n";
                    content += "Text: " + c.getText() + "\n\n";
                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }

    private void getPost(){
        Map<String, String> parameters=new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","dese");


        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {

//                    Log.e(TAG, "onResponse: ",);

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    result.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                result.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        //for
//        @POST("posts")
//        Call<Post> CreatePost(@Body Post post);
        Post post=new Post(101,"New myTitle","new myText");

        //for
//        @FormUrlEncoded
//        @POST
//        Call<Post> CreatePost(@FieldMap Map<String, String> fields);

        Map<String,String> map=new HashMap<>();
        map.put("userId","25");
        map.put("body","abc Body");
        map.put("title","xyz title");

        Call<Post> call=jsonPlaceHolderApi.CreatePost(map);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post post1=response.body();

                String content = "";
                content+="Code .."+response.code() +"\n";
                content += "ID: " + post1.getId() + "\n";
                content += "User ID: " + post1.getUserId() + "\n";
                content += "Title: " + post1.getTitle() + "\n";
                content += "Text: " + post1.getText() + "\n\n";
                result.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });


    }
    private void updatePost(){
        Post post=new Post(45,"","abc");
        Call<Post> postCall=jsonPlaceHolderApi.putPost(4,post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                Post post1=response.body();

                String content = "";
                content+="Code .."+response.code() +"\n";
                content += "ID: " + post1.getId() + "\n";
                content += "User ID: " + post1.getUserId() + "\n";
                content += "Title: " + post1.getTitle() + "\n";
                content += "Text: " + post1.getText() + "\n\n";
                result.append(content);

            }


            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }
    private void deletePost(){
        Call<Void> call=jsonPlaceHolderApi.deletePost(3);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    result.setText("Code: " + response.code());
                    return;
                }
                result.setText(" "+response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }
}