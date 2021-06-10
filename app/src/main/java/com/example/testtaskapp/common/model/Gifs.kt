package com.example.testtaskapp.common.model


import com.squareup.moshi.Json

data class Gifs(
    @field:Json(name = "data") val data: List<Gif>,
    @field:Json(name = "pagination") val pagination: Pagination
)