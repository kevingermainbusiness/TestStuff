package com.kevincodes.teststuff

import androidx.recyclerview.widget.DiffUtil

data class Dummy(val title: String, val subtitle: String, val image: String) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Dummy>() {

            override fun areItemsTheSame(oldItem: Dummy, newItem: Dummy): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Dummy,
                newItem: Dummy
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}