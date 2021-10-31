package com.homindolentrahar.githuser.data.remote

import com.homindolentrahar.githuser.BuildConfig
import com.homindolentrahar.githuser.data.remote.dto.UserDetailDto
import com.homindolentrahar.githuser.data.remote.dto.SearchUserResponse
import com.homindolentrahar.githuser.data.remote.dto.UserDto
import retrofit2.http.*

interface UserApiService {
    @GET("/users")
    suspend fun getUsers(@Header("Authorization") authorizationToken: String = BuildConfig.GITHUB_API_TOKEN): List<UserDto>

    @GET("/users/{username}")
    suspend fun getSingleUser(
        @Header("Authorization") authorizationToken: String = BuildConfig.GITHUB_API_TOKEN,
        @Path("username") username: String
    ): UserDetailDto

    @GET("/users/{username}/followers")
    suspend fun getFollowers(
        @Header("Authorization") authorizationToken: String = BuildConfig.GITHUB_API_TOKEN,
        @Path("username") username: String
    ): List<UserDto>

    @GET("/search/users")
    suspend fun searchUser(
        @Header("Authorization") authorizationToken: String = BuildConfig.GITHUB_API_TOKEN,
        @Header("Accept") accept: String = "application/vnd.github.v3+json",
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") count: Int,
        @Query("page") page: Int,
    ): SearchUserResponse
}