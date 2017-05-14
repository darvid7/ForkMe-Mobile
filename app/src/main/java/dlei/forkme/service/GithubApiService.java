package dlei.forkme.service;

import dlei.forkme.model.RepositoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * Created by David on 15/05/2017.
 */

public interface GithubApiService {
    @PUT("/user/starred/manan057/coding-interview-university")
    public Call<ResponseBody> starRepository();
}
