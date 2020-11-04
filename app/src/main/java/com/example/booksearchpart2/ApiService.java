package com.example.booksearchpart2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("volumes")
    Call<BookResponse> getMyApi(@Query("q") String query, @Query("maxResults") int maxResults);
}
