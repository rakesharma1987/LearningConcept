package com.example.mydemopersonal.paginationConcept;

import com.google.gson.annotations.SerializedName;

public class PicsumListData {
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String authorName;

    @SerializedName("url")
    private String url;

    @SerializedName("download_url")
    private String downloadUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
