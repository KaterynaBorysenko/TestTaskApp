package com.example.testtaskapp.gif_list

import androidx.annotation.ColorRes
import com.example.testtaskapp.common.BaseItem

data class GifItem(
    override val id: String,
    val webp: String,
    val width: Int,
    val height: Int,
    @ColorRes val backgroundColor: Int
) : BaseItem {
    val aspectRatio = width.toFloat() / height
}