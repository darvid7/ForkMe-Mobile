package dlei.forkme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Data model for a GitHub repository owner.
 */
public class Owner implements Parcelable {
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
    // TODO: Some attributes overlap with User, but User shouldn't strictly be a subclass of Owner
    // consider alternatives to model both.

    // Default constructor.
    public Owner() {}

    // Getters and setters.

    public String getUser() {
        return this.login;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String url) {
        this.avatarUrl = url;
    }

    // Parcelable methods.
    protected Owner(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.gravatarId = in.readString();
        this.organzationsUrl = in.readString();
        this.repositoriesUrl = in.readString();
        this.type = in.readString();
        this.followersUrl = in.readString();
        this.login = in.readString();
        this.avatarUrl = in.readString();
        this.htmlUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.gravatarId);
        dest.writeString(this.organzationsUrl);
        dest.writeString(this.repositoriesUrl);
        dest.writeString(this.type);
        dest.writeString(this.followersUrl);
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.htmlUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
