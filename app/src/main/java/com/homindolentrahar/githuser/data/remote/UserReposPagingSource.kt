package com.homindolentrahar.githuser.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.homindolentrahar.githuser.domain.model.RepoModel
import com.homindolentrahar.githuser.domain.repository.UserRepository

class UserReposPagingSource(
    private val repository: UserRepository,
    private val username: String,
    private val sort: String,
    private val direction: String,
) : PagingSource<Int, RepoModel>() {
    companion object {
        const val reposCount = 20
    }

    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        return try {
            val page = params.key ?: 1
            val response = repository.getUserRepos(
                username = username,
                sort = sort,
                direction = direction,
                count = reposCount,
                page = page
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page < 5) page.plus(1) else null,
            )
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }
}