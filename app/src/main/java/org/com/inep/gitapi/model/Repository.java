package org.com.inep.gitapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Shiki on 28/12/2017.
 */
public class Repository {

    private int id;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("private")
    private boolean isPrivate;

    @SerializedName("html_url")
    private String htmlURL;

    private String description;

    private String language;

    @SerializedName("pushed_at")
    private Date lastUpdate;

    private int size;


    /** ********************** GETTERs ********************** */
    public int getId() {
        return id;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public String getFullName() {
        if (fullName == null) return "";
        return fullName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getHtmlURL() {
        if (htmlURL == null) return "";
        return htmlURL;
    }

    public String getDescription() {
        if (description == null) return "";
        return description;
    }

    public String getLanguage() {
        if (language == null) return "";
        return language;
    }

    public Date getLastUpdate() {
        if (lastUpdate == null) return new Date();
        return lastUpdate;
    }

    public int getSize() {
        return size;
    }


    /** ********************** SETTERs ********************** */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
