package com.homindolentrahar.githuser.data.repository

import com.homindolentrahar.githuser.data.remote.UserApiService
import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository {
    override suspend fun getUsers(): Resource<List<UserModel>> {
        return try {
            val result = apiService.getUsers().map { user -> user.toModel() }
            Resource.Success(result)
        } catch (err: Exception) {
            Resource.Error(message = err.message.toString())
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

    override suspend fun getFollowers(username: String): Resource<List<UserModel>> {
        return try {
            val result = apiService.getFollowers(username = username).map { item -> item.toModel() }
            Resource.Success(result)
        } catch (err: Exception) {
            Resource.Error(message = err.message.toString())
        }
    }

    override suspend fun searchUser(
        query: String,
        sort: String,
        count: Int,
        page: Int
    ): Resource<List<UserModel>> {
        return try {
            val result = apiService.searchUser(
                query = query,
                sort = sort,
                count = count,
                page = page,
            ).items.map { item -> item.toModel() }
            Resource.Success(result)
        } catch (err: Exception) {
            Resource.Error(message = err.message.toString())
        }
    }
}