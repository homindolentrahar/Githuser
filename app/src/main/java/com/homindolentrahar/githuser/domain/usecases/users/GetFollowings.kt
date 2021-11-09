package com.homindolentrahar.githuser.domain.usecases.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.homindolentrahar.githuser.data.remote.UserFollowingsPagingSource
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowings @Inject constructor(
    private val repository: UserRepository,
    private val pagingConfig: PagingConfig,
) {
    operator fun invoke(username: String): Flow<PagingData<UserModel>> {
        val pagingSource = UserFollowingsPagingSource(repository, username)
        val pager = Pager(pagingConfig) { pagingSource }

        return pager.flow
    }
}