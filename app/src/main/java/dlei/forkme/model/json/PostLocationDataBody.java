package dlei.forkme.model.json;

/**
 * Class representing Json body in a POST method.
 */
public class PostLocationDataBody {
    String login;
    double latitude;
    double longitude;
    String message;
    String email;
    String name;
    String avatarUrl;

    public PostLocationDataBody(String login, double latitude, double longitude, String message,
                                String email, String name, String avatarUrl) {
        this.login = login;
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }
}
