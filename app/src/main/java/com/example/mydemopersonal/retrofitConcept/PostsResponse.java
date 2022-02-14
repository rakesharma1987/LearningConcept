package com.example.mydemopersonal.retrofitConcept;

import java.util.ArrayList;
import java.util.List;

public class PostsResponse {
    private List<Posts> postsList = new ArrayList<>();

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }
}
