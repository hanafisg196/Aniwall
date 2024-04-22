package com.anisuki.animewallpapers.domain.usecases

import com.anisuki.animewallpapers.common.Resource
import com.anisuki.animewallpapers.domain.model.RandomWallpaper
import com.anisuki.animewallpapers.domain.repository.RandomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RandomUseCases @Inject constructor(
    private val repository: RandomRepository
){
    operator fun  invoke(): Flow<Resource<List<RandomWallpaper>>> = flow {
        try {
            emit(Resource.Loading())
            val randoms = repository.getRandom().data
            emit(Resource.Success(randoms))
        }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "And unexpected error"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server, Check your internet Connection"))
        }
    }

}