package kg.junesqo.postapp;

import android.app.Application;

import kg.junesqo.postapp.data.remote.PostApi;
import kg.junesqo.postapp.data.remote.PostApiClient;
import kg.junesqo.postapp.data.remote.RetrofitClient;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static PostApi api;
    public static PostApiClient postApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.createApi();
        postApiClient = new PostApiClient(api);
    }
}
