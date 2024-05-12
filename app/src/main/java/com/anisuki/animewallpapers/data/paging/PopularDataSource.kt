package com.anisuki.animewallpapers.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.model.Popular
import com.anisuki.animewallpapers.repository.PopularRepo

class PopularDataSource (
    private val repository : PopularRepo
):PagingSource<Int, Popular>() {
    override fun getRefreshKey(state: PagingState<Int, Popular>): Int? {
        return state.anchorPosition?.let { position ->
            val  page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1)?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Popular> {
        return try {
            val page = params.key ?:1
            val response = repository.getPopular(page, Constants.ITEM_PAGE)
            Log.d("PopularWallpaper", "Data: ${response.data}")
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