package dlei.forkme.model;

import com.google.gson.annotations.SerializedName;

/**
* Data model a GitHub user.
 * */
public class User {
    private int id;
    private String url;
    @SerializedName("gravatar_id")  // Can be empty "".
    private String gravatarId;
    @SerializedName("organizations_url")
    private String organzationsUrl;
    @SerializedName("repos_url")
    private String repositoriesUrl;
    private String type;
    @SerializedName("followers_url")
    private String followersUrl;
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("following__url")
    private String followingUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    private String name;
    private String company;
    private String location;
    private String email;  // Nullable.
    // TODO: Confirm nullable won't break things.
    private String bio;
    @SerializedName("public_repos")
    private int publicRepositories;
    private int followers;
    private int following;

    // Default constructor.
    public User() {}

    // Getters and Setters.

    public String getLogin() {
        return this.login;
    }

    public String toString() {
        return String.format("%s - login: %s, id: %s", super.toString(), this.login, this.id);
    }

    public String getEmail() { return this.email; }

    public String getName() {
        return this.name;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public String getBio() {
        return this.bio;
    }

    public int getFollowers() {
        return this.followers;
    }

    public int getFollowing() {
        return this.following;
    }

    public int getPublicRepositories() {
        return this.publicRepositories;
    }
}
