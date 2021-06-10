package com.example.testtaskapp.common.model

import com.squareup.moshi.Json

data class Images(
    @field:Json(name = "fixed_width") val fixedWidth: FixedWidth
)