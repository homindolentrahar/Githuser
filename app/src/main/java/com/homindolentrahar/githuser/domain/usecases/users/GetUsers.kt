package com.homindolentrahar.githuser.domain.usecases.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.homindolentrahar.githuser.common.Resource
import com.homindolentrahar.githuser.data.remote.UsersPagingSource
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository: UserRepository,
    private val pagingConfig: PagingConfig
) {
    operator fun invoke(): Flow<PagingData<UserModel>> {
        val pagingSource = UsersPagingSource(repository)
        val pager = Pager(pagingConfig) { pagingSource }

        return pager.flow
    }
}