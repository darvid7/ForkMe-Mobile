package dlei.forkme.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RepositoryResponse {

    @SerializedName("repositories")
    private List<Repository> repositoryList;

    // Constructor needed for collections, avoid null pointers.
    public RepositoryResponse() {
        repositoryList= new ArrayList<Repository>();
    }

    public static RepositoryResponse parseJson(String response) {
        Gson gson = new GsonBuilder().create();
        RepositoryResponse repositoryResponse = gson.fromJson(response, RepositoryResponse.class);
        return repositoryResponse;
    }


    public String toString() {
        // TODO: More efficient string concat.
        String s = "";
        for (int i = 0; i < repositoryList.size(); i++) {
            Repository r = repositoryList.get(i);
            if (r == null) {
                return s;
            }
            String name = r.getFullName();
            s = s + name + "\n";
        }
        return s;
    }

    public Repository getRepository(int i) {
        return repositoryList.get(i);
    }

    public int getSize() {
        return repositoryList.size();
    }
}
