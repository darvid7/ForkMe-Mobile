package dlei.forkme.service;

import dlei.forkme.model.Repository;
import dlei.forkme.model.RepositoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by David on 15/05/2017.
 */

public interface ForkMeBackendApiService {
    @GET("/all_repos")
    public Call<RepositoryResponse> getAllRepositories();

    @GET("/all_repos/repositories/{id}")
    Call<Repository> getRepository(@Path("id") int id);
}
