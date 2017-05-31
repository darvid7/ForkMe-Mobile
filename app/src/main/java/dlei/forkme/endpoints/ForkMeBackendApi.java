package dlei.forkme.endpoints;

import java.util.List;

import dlei.forkme.model.LocationData;
import dlei.forkme.model.Repository;
import dlei.forkme.model.RepositoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ForkMeBackendApi {
    @GET("/all_repos")
    Call<RepositoryResponse> getAllRepositories();

    @GET("/all_repos/repositories/{id}")
    Call<Repository> getRepository(@Path("id") int id);

    @GET("/repositories")
    Call<List<Repository>> getRepositoriesArray();

    @POST("/locations")
    Call<LocationData> postLocation();

}
