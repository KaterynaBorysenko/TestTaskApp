package com.example.testtaskapp

import android.app.Application

import com.facebook.drawee.backends.pipeline.Fresco
import com.example.testtaskapp.common.Repository
import com.example.testtaskapp.common.network.Service
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber


class GiphyApp : Application() {
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        RxJavaPlugins.setErrorHandler { throwable: Throwable ->
            if (throwable is UndeliverableException) {
                Timber.w(throwable, "Ignoring uncaught Rx exception")
            } else {
                throw throwable
            }
        }
        val service = Retrofit.Builder()
                .baseUrl("https://api.giphy.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Service::class.java)
        repository = Repository(service, "RLllGWmu2v0QmqYnxPnkKGdwikuQWeap")
    }

}