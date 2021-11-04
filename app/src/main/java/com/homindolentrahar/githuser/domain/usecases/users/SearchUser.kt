package com.homindolentrahar.githuser.domain.usecases.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository
import com.homindolentrahar.githuser.common.Resource
import com.homindolentrahar.githuser.data.remote.SearchUserPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUser @Inject constructor(
    private val repository: UserRepository,
    private val pagingConfig: PagingConfig
) {
    operator fun invoke(
        query: String,
        sort: String,
        order: String,
    ): Flow<PagingData<UserModel>> {
        val pagingSource = SearchUserPagingSource(repository, query, sort, order)
        val pager = Pager(pagingConfig) { pagingSource }

        return pager.flow
    }
}