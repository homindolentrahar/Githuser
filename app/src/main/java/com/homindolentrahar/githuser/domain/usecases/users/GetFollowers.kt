package com.homindolentrahar.githuser.domain.usecases.users

import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFollowers @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(username: String): Flow<Resource<List<UserModel>>> = flow {
        emit(Resource.Loading())

        emit(repository.getFollowers(username))
    }
}