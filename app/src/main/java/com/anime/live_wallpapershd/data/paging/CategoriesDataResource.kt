package com.anime.live_wallpapershd.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.model.Category
import com.anime.live_wallpapershd.repository.CategoriesRepo

class CategoriesDataResource(
    private val repository : CategoriesRepo
):PagingSource<Int, Category> () {
    override fun getRefreshKey(state: PagingState<Int, Category>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1)?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        return try {
            val page = params.key ?:1
            val response = repository.getCategories(page, Constants.ITEM_PAGE)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.isNotEmpty()) page + 1 else null
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}