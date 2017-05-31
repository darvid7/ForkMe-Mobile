package dlei.forkme.endpoints;

import java.util.List;

import dlei.forkme.model.Readme;
import dlei.forkme.model.Repository;
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


    // GET /user/starred for an authenticated user, returns list of repositories they have starred.
    @GET("/user/starred")
    Call<List<Repository>> getStarredRepositories();

    // GET /user/repos for an authenticated user, returns list of repositories. Default is all.
    @GET("/user/repos")
    Call<List<Repository>> getMyRepositories();


}
