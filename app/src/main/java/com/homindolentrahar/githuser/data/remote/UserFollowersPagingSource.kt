package com.homindolentrahar.githuser.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.homindolentrahar.githuser.domain.model.UserModel
import com.homindolentrahar.githuser.domain.repository.UserRepository

class UserFollowersPagingSource(
    private val repository: UserRepository,
    private val username: String,
) : PagingSource<Int, UserModel>() {

    companion object {
        const val followersCount = 20
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            val anchorPage = state.closestPageToPosition(anchorPos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val page = params.key ?: 1
            val response = repository.getFollowers(
                username = username,
                count = followersCount,
                page = page,
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = page.plus(1)
            )
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }
}