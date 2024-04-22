package com.apptesting.test.utils

import com.apptesting.test.model.AllData
import io.reactivex.Single
import retrofit2.http.POST

interface RetrofitService {
    @POST(Const.ApiParams.fetchAllData)
    fun fetchAllData(): Single<AllData?>
}