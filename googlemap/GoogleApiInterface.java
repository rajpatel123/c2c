package com.camtrack.tracker.googlemap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GoogleApiInterface{
    @GET("maps/api/place/autocomplete/json")
    Call<GooglePlace> getPlaceList(@Query(value = "input",encoded = true) String input,
                                   @Query("components") String components,
                                   @Query("key") String key);
}
