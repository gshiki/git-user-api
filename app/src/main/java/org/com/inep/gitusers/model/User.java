package org.com.inep.gitusers.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shiki on 27/12/2017.
 */
public class User {

    private int id;

    private String type;

    private String login;

    private String name;

    private String company;

    private String location;

    private String email;

    @SerializedName("bio")
    private String biography;

    @SerializedName("public_repos")
    private int countRepositories;

    @SerializedName("public_gists")
    private String countGists;

    @SerializedName("gravatar_id")
    private String idGravatar;

    @SerializedName("avatar_url")
    private String urlAvatar;

    @SerializedName("url")
    private String urlGIT;

    @SerializedName("html_url")
    private String urlHTML;

    @SerializedName("gists_url")
    private String urlGISTS;

    @SerializedName("organizations_url")
    private String urlSubscritions;

    @SerializedName("subscriptions_url")
    private String urlOrganizations;

    @SerializedName("repos_url")
    private String urlRepositories;

    @SerializedName("site_admin")
    private boolean isAdmin;

    public User(String login) {
        this.login = login;
    }


    /** ********************** GETTERs ********************** */
    public int getId() {
        return id;
    }

    public String getIdGravatar() {
        if (idGravatar == null) return "";
        return idGravatar;
    }

    public String getType() {
        if (type == null) return "";
        return type;
    }

    public String getLogin() {
        if (login == null) return "";
        return login;
    }

    public String getName() {
        if (name == null) return "";
        return name;
    }

    public String getCompany() {
        if (company == null) return "";
        return company;
    }

    public String getLocation() {
        if (location == null) return "";
        return location;
    }

    public String getEmail() {
        if (email == null) return "";
        return email;
    }

    public String getBiography() {
        if (biography == null) return "";
        return biography;
    }

    public int getCountRepositories() {
        return countRepositories;
    }

    public String getCountGists() {
        if (countGists == null) return "0";
        return countGists;
    }

    public String getUrlAvatar() {
        if (urlAvatar == null) return "";
        return urlAvatar;
    }

    public String getUrlGIT() {
        if (urlGIT == null) return "";
        return urlGIT;
    }

    public String getUrlHTML() {
        if (urlHTML == null) return "";
        return urlHTML;
    }

    public String getUrlGISTS() {
        if (urlGISTS == null) return "";
        return urlGISTS;
    }

    public String getUrlSubscritions() {
        if (urlSubscritions == null) return "";
        return urlSubscritions;
    }

    public String getUrlOrganizations() {
        if (urlOrganizations == null) return "";
        return urlOrganizations;
    }

    public String getUrlRepositories() {
        if (urlRepositories == null) return "";
        return urlRepositories;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    /** ********************** SETTERs ********************** */
    public void setId(int id) {
        this.id = id;
    }

    public void setIdGravatar(String idGravatar) {
        this.idGravatar = idGravatar;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setCountRepositories(int countRepositories) {
        this.countRepositories = countRepositories;
    }

    public void setCountGists(String countGists) {
        this.countGists = countGists;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public void setUrlGIT(String urlGIT) {
        this.urlGIT = urlGIT;
    }

    public void setUrlHTML(String urlHTML) {
        this.urlHTML = urlHTML;
    }

    public void setUrlGISTS(String urlGISTS) {
        this.urlGISTS = urlGISTS;
    }

    public void setUrlSubscritions(String urlSubscritions) {
        this.urlSubscritions = urlSubscritions;
    }

    public void setUrlOrganizations(String urlOrganizations) {
        this.urlOrganizations = urlOrganizations;
    }

    public void setUrlRepositories(String urlRepositories) {
        this.urlRepositories = urlRepositories;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
