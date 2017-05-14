package dlei.forkme.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by David on 15/05/2017.
 */

// TODO: Should they be private?
// Annoyance of getters and setters.

public class Repository {
    private int size;
    @SerializedName("full_name")
    private String fullname;
    @SerializedName("stargazers_count")
    private int stargazerCount;
    private String description;
    private int id;
    private String language;
    private String homepage;
    private int score;
    @SerializedName("created_at")
    private String createdAt; // TODO: Parse date time.
    @SerializedName("html_url")
    private String htmlUrl;
    private Owner owner;
    @SerializedName("has_wiki")
    private boolean hasWiki;
    @SerializedName("open_issues_count")
    private int openIssuesCount;
    private String url;
    @SerializedName("forks_count")
    private int forkCount;
    @SerializedName("updated_at")
    private String updatedAt; // TODO: Parse date time.


    public int getSize() {
        return size;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStargazerCount() {
        return stargazerCount;
    }

    public void setStargazerCount(int count) {
        this.stargazerCount = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }



    public String toString() {
        return "Repository: " + fullname + ", description: " + description + ", stars: " + stargazerCount;
    }
}
