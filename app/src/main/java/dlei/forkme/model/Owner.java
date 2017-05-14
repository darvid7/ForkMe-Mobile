package dlei.forkme.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by David on 15/05/2017.
 */

public class Owner {
    private int id;
    private String url;
    @SerializedName("gravatar_id")
    private int gravatarId;
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

    public String getUser() {
        return this.login;
    }


}
