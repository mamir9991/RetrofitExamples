package zoochi.aamir.retrofitexamples;

import com.google.gson.annotations.SerializedName;

public class Comments {

    private int postId;
    private int Id;
    private String name;
    @SerializedName("Body")
    private String text;
    private String email;

    public String getEmail() {
        return email;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
