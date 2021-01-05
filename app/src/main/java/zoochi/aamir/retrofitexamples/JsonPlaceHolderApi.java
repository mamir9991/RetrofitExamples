package zoochi.aamir.retrofitexamples;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {


    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") int UserID,
            @Query("_sort") String ID,
            @Query("_order") String Order
    );


    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int id);

    @POST("posts")
    Call<Post> CreatePost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> CreatePost(@Field("userId") int userid,
                          @Field("title") String Title,
                          @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> CreatePost(@FieldMap Map<String, String> fields);

    //replace field to null is we cannot pass it through updating time
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int Id,@Body Post post);

    //cannot replace it to null. while updating time
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int Id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);


}
