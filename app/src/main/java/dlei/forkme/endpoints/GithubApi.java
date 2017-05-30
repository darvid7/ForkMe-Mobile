package dlei.forkme.endpoints;

import dlei.forkme.model.Readme;
import dlei.forkme.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface GithubApi {
    @PUT("/user/starred/{user}/{repo}")
    Call<ResponseBody> starRepository(@Path("user") String user, @Path("repo") String repo);

    @GET("/user/{username}")
    Call<User> getUser(@Path("username")  String username);

    // GET /repos/:owner/:repo/readme
    @GET("/repos/{owner}/{repo}/readme")
    Call<Readme> getReadme(@Path("owner") String owner, @Path("repo") String repo);
}
