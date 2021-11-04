package com.homindolentrahar.githuser.domain.usecases.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import com.homindolentrahar.githuser.data.remote.UserFollowersPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFollowers @Inject constructor(
    private val repository: UserRepository,
    private val pagingConfig: PagingConfig
) {

    operator fun invoke(username: String): Flow<PagingData<UserModel>> {
        val pagingSource = UserFollowersPagingSource(repository, username)
        val pager = Pager(pagingConfig) { pagingSource }

        return pager.flow
    }
}