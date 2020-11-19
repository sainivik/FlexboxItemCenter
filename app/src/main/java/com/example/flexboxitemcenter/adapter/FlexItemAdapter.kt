/*
 * Copyright 2017 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.flexboxitemcenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flexboxitemcenter.R
import com.example.flexboxitemcenter.adapter.FlexItemAdapter.FlexItemViewHolder
import com.example.flexboxitemcenter.databinding.AdapterItemBinding
import com.example.flexboxitemcenter.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.FlexItemClickListener

/**
 * [RecyclerView.Adapter] implementation for [FlexItemViewHolder].
 */
internal class FlexItemAdapter(
    private val activity: AppCompatActivity,

    var clickListener: FlexItemClickListener
) : RecyclerView.Adapter<FlexItemAdapter.FlexItemViewHolder>() {

    private val layoutParams = mutableListOf<FlexboxLayoutManager.LayoutParams>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlexItemViewHolder {
        val binding = DataBindingUtil.inflate<AdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_item,
            parent,
            false
        )

        return FlexItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlexItemViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        // TODO: More optimized set the click listener inside the view holder
        holder.binding.llMain.setOnClickListener {
            clickListener.onClick(
                holder.binding.llMain,
                adapterPosition
            )
        }


    }

    fun addItem(lp: FlexboxLayoutManager.LayoutParams) {
        layoutParams.add(lp)
        notifyItemInserted(layoutParams.size - 1)
    }

    fun removeItem(position: Int) {
        if (position < 0 || position >= layoutParams.size) {
            return
        }
        layoutParams.removeAt(position)
        notifyItemRemoved(layoutParams.size)
        notifyItemRangeChanged(position, layoutParams.size)
    }

    val items get() = layoutParams

    override fun getItemCount() = layoutParams.size


    inner class FlexItemViewHolder(mBinding: AdapterItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        val binding = mBinding

    }


}
