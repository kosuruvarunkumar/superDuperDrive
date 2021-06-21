package com.udacity.jwdnd.course1.cloudstorage.models;

public class CredentialForm {
    private String url;
    private String username;
    private String password;
    private String id;

    public CredentialForm(String url, String username, String password, String id) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
