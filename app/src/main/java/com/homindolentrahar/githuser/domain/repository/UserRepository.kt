package com.homindolentrahar.githuser.domain.repository

import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.common.Resource

interface UserRepository {
    suspend fun getUsers(): Resource<List<UserModel>>
    suspend fun getSingleUser(username: String): Resource<UserDetailModel>
    suspend fun getFollowers(username: String): Resource<List<UserModel>>
    suspend fun searchUser(
        query: String,
        sort: String,
        count: Int,
        page: Int,
    ): Resource<List<UserModel>>
}