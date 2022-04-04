package com.example.mydemopersonal.retrofitConcept.retrofitWithMVVM.model.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mydemopersonal.BR;
import com.google.gson.annotations.SerializedName;

public class PostData extends BaseObservable {
    @SerializedName("usedId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @Bindable
    public int getUserId() {
        return userId;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getBody() {
        return body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setBody(String body) {
        this.body = body;
        notifyPropertyChanged(BR.body);
    }
}
