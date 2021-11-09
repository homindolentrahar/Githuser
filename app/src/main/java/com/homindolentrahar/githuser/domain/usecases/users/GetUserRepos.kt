package com.homindolentrahar.githuser.domain.usecases.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.homindolentrahar.githuser.data.remote.UserReposPagingSource
import com.homindolentrahar.githuser.domain.model.RepoModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserRepos @Inject constructor(
    private val repository: UserRepository,
    private val pagingConfig: PagingConfig
) {
    operator fun invoke(
        username: String,
        sort: String = "star",
        direction: String = "desc"
    ): Flow<PagingData<RepoModel>> {
        val pagingSource = UserReposPagingSource(repository, username, sort, direction)
        val pager = Pager(pagingConfig) { pagingSource }

        return pager.flow
    }
}