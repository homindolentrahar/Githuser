package com.homindolentrahar.githuser.domain.usecases.users

import com.homindolentrahar.githuser.domain.model.UserDetailModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSingleUser @Inject constructor (
    private val repository: UserRepository
) {
    operator fun invoke(username: String): Flow<Resource<UserDetailModel>> = flow {
        emit(Resource.Loading())

        emit(repository.getSingleUser(username = username))
    }
}