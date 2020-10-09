package com.example.trend;


import com.example.trend.bean.ListBean;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("/repositories")
    Observable<List<ListBean>> getList(@Query("language") String language, @Query("since") String since
            , @Query("spoken_language_code")String spoken_language_code);
}
