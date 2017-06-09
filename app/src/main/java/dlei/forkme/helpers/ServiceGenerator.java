package dlei.forkme.helpers;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792
/*
  OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                // Manipulate request to add headers.
                // Can't mutate the request but can make a new one.
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        // Add in user access token.
                        .addHeader("Authorization", "token " + mOAuthToken);
                // Pass on our request to execute.
                return chain.proceed(newRequest.build());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
 */

public class ServiceGenerator {
    public static String apiBaseUrl = "http://futurestud.io/api";

    private static Retrofit retrofit;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(apiBaseUrl);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }

    public static <S> S createService(Class<S> serviceClass) {
//        if (!httpClient.interceptors().contains(logging)) {
//            httpClient.addInterceptor(logging);
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
        return retrofit.create(serviceClass);
    }

}
