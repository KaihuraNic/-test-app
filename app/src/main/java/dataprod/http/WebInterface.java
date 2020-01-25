package dataprod.http;


import dataprod.http.request.SyncData;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebInterface {


    @POST("/vol2/sync")
    Call<String> syncData(
            @Query("data") SyncData data
    );
}
