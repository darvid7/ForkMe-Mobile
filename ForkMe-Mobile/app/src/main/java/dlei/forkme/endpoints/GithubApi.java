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
import retrofit2.http.Query;

/**
 * Endpoints for GitHub's API v3.
 */
public interface GithubApi {

    // Star a repository.
    @PUT("/user/starred/{user}/{repo}")
    Call<ResponseBody> starRepository(@Path("user") String user, @Path("repo") String repo);

    // Get user information for userLogin.
    @GET("/users/{userLogin}")
    Call<User> getUser(@Path("userLogin")  String userLogin);

    // Get README for a repository.
    @GET("/repos/{owner}/{repo}/readme")
    Call<Readme> getReadme(@Path("owner") String owner, @Path("repo") String repo);

    // Get starred repositories for user.
    @GET("/users/{user}/starred")
    Call<List<Repository>> getStarredRepositories(@Path("user") String user);

    // GET repositories for an authenticated user, returns list of repositories. Default is all.
    @GET("/users/{user}/repos")
    Call<List<Repository>> getMyRepositories(@Path("user") String user);

    // Get user details (for the user who is logged in and supplies the OAuth Token.
    @GET("/user")
    Call<User> getAuthenticatedUser();

    // Search API.
    // /search/repositories?q=created:>2017-06-04+language:python&sort=stars&order=desc
    // Note: all works if no language is to be specified.
    @GET("/search/repositories?q=created:>{stringDate}+language:{language}&sort={sortBy}&order=desc")
    Call<List<Repository>> searchGitHubTrendingRepositories(
            @Query("stringDate") String stringDate,
            @Query("language") String language,
            @Query("sortBy") String sortBy);


}
