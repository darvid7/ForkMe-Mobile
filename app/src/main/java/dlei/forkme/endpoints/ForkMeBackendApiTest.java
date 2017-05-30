package dlei.forkme.endpoints;

import java.util.List;

import dlei.forkme.model.Repository;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ForkMeBackendApiTest {

    @GET("/repositories")
    Call<List<Repository>> getRepositoriesLocal();

}
