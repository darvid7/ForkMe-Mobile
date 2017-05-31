package dlei.forkme.model;

import com.google.gson.annotations.SerializedName;

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

    public String getUsername() {
        return this.login;
    }

    public String toString() {
        return String.format("%s - login: %s, id: %s", super.toString(), this.login, this.id);
    }
}
