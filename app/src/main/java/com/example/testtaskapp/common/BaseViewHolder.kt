package com.example.testtaskapp.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : BaseItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int, item: T)
}