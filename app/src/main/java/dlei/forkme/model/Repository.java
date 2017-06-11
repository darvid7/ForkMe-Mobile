package dlei.forkme.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;


/**
 * Data model class for a GitHub Repositoriy.
 * */
public class Repository implements Parcelable {
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
    private String createdAt; // TODO: Parse date time? Or keep as string (easier for parcelable)
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
    @SerializedName("subscribers_count")
    private int subscribersCount;
    @SerializedName("updated_at")
    private String updatedAt; // TODO: Parse date time.


    // Default constructor.
    public Repository() {}

    // Getters and Setters.

    public int getSize() {
        return size;
    }

    public String getFullName() {
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

    public int getForkCount() {
        return this.forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    // This is the same as watcher count on the web interface, watcher is the same as star in the API.
    public int getSubscribersCount() {
        Log.w("Subscriber count: ", "" + this.subscribersCount);
        return this.subscribersCount;
    }

    public void setSubScribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public int getWatchCount() {
        return this.getSubscribersCount();
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;

    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    // Log.i("tokens: ", "index 0: " + tokens[0] + ", index 1: " + tokens[1]);
    //  index 0: Microsoft, index 1: TypeScript-React-Starter
    public String getRepoName() {
        String tokens[] = this.fullname.split("/");
        return tokens[1];
    }

    public String getUserName() {
        return this.owner.getUser();
    }

    public String toString() {
        return "Repository: " + fullname + ", description: " + description + ", stars: " + stargazerCount + ", watchers: " + subscribersCount;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public String getOwnerName() {
        return this.owner.getUser();
    }

    public String getAvatarUrl() {
        if (this.owner == null) {
            return null;
        }
        return this.owner.getAvatarUrl();
    }

    // Parcelable methods.
    @Override
    public int describeContents() {
        return 0;
    }

    // Constructor that accepts type Parcel (intent) and builds a Monster.
    protected Repository(Parcel in) {
        this.size = in.readInt();
        this.fullname = in.readString();
        this.stargazerCount = in.readInt();
        this.description = in.readString();
        this.id = in.readInt();
        this.language = in.readString();
        this.homepage = in.readString();
        this.score = in.readInt();
        this.createdAt = in.readString();
        this.htmlUrl = in.readString();
        this.owner = (Owner) in.readValue(Owner.class.getClassLoader());
        this.hasWiki = in.readByte() != 0;  // Stored boolean as 0 and 1.
        this.openIssuesCount = in.readInt();
        this.url = in.readString();
        this.forkCount = in.readInt();
        this.subscribersCount = in.readInt();
        this.updatedAt = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.size);
        dest.writeString(this.fullname);
        dest.writeInt(this.stargazerCount);
        dest.writeString(this.description);
        dest.writeInt(this.id);
        dest.writeString(this.language);
        dest.writeString(this.homepage);
        dest.writeInt(this.score);
        dest.writeString(this.createdAt);
        dest.writeString(this.htmlUrl);
        dest.writeValue(this.owner);
        dest.writeByte((byte) (this.hasWiki ? 1 : 0));  // Represent bool in a byte using 1 or 0.
        dest.writeInt(this.openIssuesCount);
        dest.writeString(this.url);
        dest.writeInt(this.forkCount);
        dest.writeInt(this.subscribersCount);
        dest.writeString(this.updatedAt);

    }

    @SuppressWarnings("unused")
    public static final Creator<Repository> CREATOR = new Creator<Repository>() {

        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

}
