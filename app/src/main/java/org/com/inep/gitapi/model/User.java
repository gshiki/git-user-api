package org.com.inep.gitapi.model;

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

    private String bio;

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

    @SerializedName("followers_url")
    private String urlFollowers;

    @SerializedName("following_url")
    private String urlFollowing;

    @SerializedName("gists_url")
    private String urlGISTS;

    @SerializedName("organizations_url")
    private String urlSubscritions;

    @SerializedName("subscriptions_url")
    private String urlOrganizations;

    @SerializedName("repos_url")
    private String urlRepositories;

    @SerializedName("events_url")
    private String urlEvents;

    @SerializedName("received_events_url")
    private String urlReceivedEvents;

    @SerializedName("site_admin")
    private boolean isAdmin;


    /** ***************************************************** */
    /** ********************** GETTERs ********************** */
    /** ***************************************************** */
    public int getId() {
        return id;
    }

    public String getIdGravatar() {
        return idGravatar;
    }

    public String getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public int getCountRepositories() {
        return countRepositories;
    }

    public String getCountGists() {
        return countGists;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public String getUrlGIT() {
        return urlGIT;
    }

    public String getUrlHTML() {
        return urlHTML;
    }

    public String getUrlFollowers() {
        return urlFollowers;
    }

    public String getUrlFollowing() {
        return urlFollowing;
    }

    public String getUrlGISTS() {
        return urlGISTS;
    }

    public String getUrlSubscritions() {
        return urlSubscritions;
    }

    public String getUrlOrganizations() {
        return urlOrganizations;
    }

    public String getUrlRepositories() {
        return urlRepositories;
    }

    public String getUrlEvents() {
        return urlEvents;
    }

    public String getUrlReceivedEvents() {
        return urlReceivedEvents;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    /** ***************************************************** */
    /** ********************** SETTERs ********************** */
    /** ***************************************************** */
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

    public void setBio(String bio) {
        this.bio = bio;
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

    public void setUrlFollowers(String urlFollowers) {
        this.urlFollowers = urlFollowers;
    }

    public void setUrlFollowing(String urlFollowing) {
        this.urlFollowing = urlFollowing;
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

    public void setUrlEvents(String urlEvents) {
        this.urlEvents = urlEvents;
    }

    public void setUrlReceivedEvents(String urlReceivedEvents) {
        this.urlReceivedEvents = urlReceivedEvents;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
