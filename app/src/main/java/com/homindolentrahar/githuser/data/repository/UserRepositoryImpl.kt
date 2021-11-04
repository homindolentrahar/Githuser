package com.homindolentrahar.githuser.data.repository

import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import com.homindolentrahar.githuser.data.remote.UserApiService

class UserRepositoryImpl(
    private val apiService: UserApiService,
) : UserRepository {
    override suspend fun getUsers(since: Int, count: Int): List<UserModel> {
        return try {
            apiService.getUsers(since = since, count = count).map { dto -> dto.toModel() }
        } catch (err: Exception) {
            throw err
        }
    }

    override suspend fun getSingleUser(username: String): Resource<UserDetailModel> {
        return try {
            val result = apiService.getSingleUser(username = username).toModel()
            Resource.Success(result)
        } catch (err: Exception) {
            Resource.Error(message = err.message.toString())
        }
    }

    override suspend fun getFollowers(
        username: String,
        count: Int,
        page: Int,
    ): List<UserModel> {
        return try {
            apiService.getFollowers(
                username = username,
                count = count,
                page = page,
            ).map { dto -> dto.toModel() }
        } catch (err: Exception) {
            throw err
        }
    }

    override suspend fun searchUser(
        query: String,
        sort: String,
        order: String,
        count: Int,
        page: Int
    ): List<UserModel> {
        return try {
            apiService.searchUser(
                query = query,
                sort = sort,
                order = order,
                count = count,
                page = page
            ).items.map { dto -> dto.toModel() }
        } catch (err: Exception) {
            throw err
        }
    }
}