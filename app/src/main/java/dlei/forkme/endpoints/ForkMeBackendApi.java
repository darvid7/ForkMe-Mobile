package dlei.forkme.endpoints;

import java.util.List;

import dlei.forkme.model.DeveloperContactInfo;
import dlei.forkme.model.LocationData;
import dlei.forkme.model.Repository;
import dlei.forkme.model.RepositoryResponse;
import dlei.forkme.model.json.PostLocationDataBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Endpoints for ForkMe Backend.
 * v1 is depreacted, using v2 now
 */
public interface ForkMeBackendApi {

    // Get all repositories v1.
    @GET("/all_repos")
    Call<RepositoryResponse> getAllRepositories();

    // Get single repository v1.
    @GET("/all_repos/repositories/{id}")
    Call<Repository> getRepository(@Path("id") int id);

    // Get all repositories v2.
    @GET("/repositories")
    Call<List<Repository>> getRepositoriesArray();

    // Post location data v2.
    @POST("/locations")
    Call<ResponseBody> postLocation(@Body PostLocationDataBody data);

    // Get developers v2.
    @GET("/developers")
    Call<List<DeveloperContactInfo>> getDevelopers();

}
