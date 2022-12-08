package com.example.smartwe.Model;

public class PostsDashboardModel {
    int profile_img, post_img, bookmark_img;
    String userName_tv;
    String about_tv;
    String like_tv;
    String comment_tv;
    String share_tv;

    public PostsDashboardModel(int profile_img, int post_img, int bookmark_img, String userName_tv, String about_tv, String like_tv, String comment_tv, String share_tv) {
        this.profile_img = profile_img;
        this.post_img = post_img;
        this.bookmark_img = bookmark_img;
        this.userName_tv = userName_tv;
        this.about_tv = about_tv;
        this.like_tv = like_tv;
        this.comment_tv = comment_tv;
        this.share_tv = share_tv;
    }

    public int getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(int profile_img) {
        this.profile_img = profile_img;
    }

    public int getPost_img() {
        return post_img;
    }

    public void setPost_img(int post_img) {
        this.post_img = post_img;
    }

    public int getBookmark_img() {
        return bookmark_img;
    }

    public void setBookmark_img(int bookmark_img) {
        this.bookmark_img = bookmark_img;
    }

    public String getUserName_tv() {
        return userName_tv;
    }

    public void setUserName_tv(String userName_tv) {
        this.userName_tv = userName_tv;
    }

    public String getAbout_tv() {
        return about_tv;
    }

    public void setAbout_tv(String about_tv) {
        this.about_tv = about_tv;
    }

    public String getLike_tv() {
        return like_tv;
    }

    public void setLike_tv(String like_tv) {
        this.like_tv = like_tv;
    }

    public String getComment_tv() {
        return comment_tv;
    }

    public void setComment_tv(String comment_tv) {
        this.comment_tv = comment_tv;
    }

    public String getShare_tv() {
        return share_tv;
    }

    public void setShare_tv(String share_tv) {
        this.share_tv = share_tv;
    }

}
