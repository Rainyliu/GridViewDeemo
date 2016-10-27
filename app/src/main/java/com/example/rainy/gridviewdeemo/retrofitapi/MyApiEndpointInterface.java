package com.example.rainy.gridviewdeemo.retrofitapi;

import com.example.rainy.gridviewdeemo.beans.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/10/26 0026 上午 11:56 <br>
 *
 */

public interface  MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("/group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @POST("/users/new")
    Call<User> createUser(@Body User user);

//    @POST("https://api.github.com/api/v3")
}
