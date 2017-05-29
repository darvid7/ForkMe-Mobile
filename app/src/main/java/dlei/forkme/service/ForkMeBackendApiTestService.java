package dlei.forkme.service;

import java.util.List;

import dlei.forkme.model.Repository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ForkMeBackendApiTestService {

    @GET("/repositories")
    Call<List<Repository>> getRepositoriesLocal();

}
