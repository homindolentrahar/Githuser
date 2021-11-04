package com.homindolentrahar.githuser.domain.repository

import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.common.Resource

interface UserRepository {
    suspend fun getUsers(since: Int, count: Int): List<UserModel>
    suspend fun getSingleUser(username: String): Resource<UserDetailModel>
    suspend fun getFollowers(
        username: String,
        count: Int,
        page: Int,
    ): List<UserModel>

    suspend fun searchUser(
        query: String,
        sort: String,
        order: String,
        count: Int,
        page: Int
    ): List<UserModel>
}