package com.example.testtaskapp.common

import com.example.testtaskapp.common.model.Gifs
import com.example.testtaskapp.common.model.Resource
import com.example.testtaskapp.common.network.ApiException
import com.example.testtaskapp.common.network.NetworkException
import com.example.testtaskapp.common.network.Service
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result
import java.io.IOException

class Repository(
    private val service: Service,
    private val apiKey: String
) {
    fun getTrendingGifs(offset: Int): Single<Resource<Gifs>> =
        service.getTrendingGifs(apiKey, offset)
            .subscribeOn(Schedulers.io())
            .map { toResource(it) }

    fun getSearchGifs(query: String, offset: Int): Single<Resource<Gifs>> =
        service.getSearchGifs(apiKey, query, offset)
            .subscribeOn(Schedulers.io())
            .map { toResource(it) }

    private fun <T> toResource(result: Result<T>): Resource<T> {
        return if (result.isError) { // Network error
            // Network error
            if (result.error() is IOException) {
                return Resource.Error(NetworkException(result.error()!!))
            }

            throw RuntimeException(result.error())
        } else {
            val response: Response<T> = result.response()!!
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {

                Resource.Error(ApiException(response.message()))
            }
        }
    }
}