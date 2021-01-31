package com.kevincodes.teststuff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kevincodes.teststuff.Dummy.Companion.diffCallback
import com.kevincodes.teststuff.databinding.DummyRowBinding

class DummyAdapter : RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {
    private var onItemClickListener: ((Dummy) -> Unit)? = null

    fun setItemClickListener(listener: (Dummy) -> Unit) {
        onItemClickListener = listener
    }

    private val differ: AsyncListDiffer<Dummy> = AsyncListDiffer(this, diffCallback)

    var dummyList: List<Dummy>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    inner class DummyViewHolder(private val itemBinding: DummyRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val glide = Glide.with(itemBinding.root).setDefaultRequestOptions(
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_baseline_error_outline_24)
        )

        fun bind(dummy: Dummy) {
            itemBinding.apply {
                glide.load(dummy.image).into(dummyImage)
                dummyTitle.text = dummy.title
                dummySubtitle.text = dummy.subtitle
                root.setOnClickListener {
                    onItemClickListener?.let { clickEvent -> clickEvent(dummy) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DummyRowBinding.inflate(inflater, parent, false)
        return DummyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.bind(dummyList[position])
    }

    override fun getItemCount(): Int = dummyList.size
}