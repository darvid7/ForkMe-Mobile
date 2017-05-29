package dlei.forkme.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface GithubApiService {
    @PUT("/user/starred/{user}/{repo}")
    public Call<ResponseBody> starRepository(@Path("user") String user, @Path("repo") String repo);
}
