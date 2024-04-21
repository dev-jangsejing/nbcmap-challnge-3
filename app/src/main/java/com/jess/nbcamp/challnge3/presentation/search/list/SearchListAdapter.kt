package com.jess.nbcamp.challnge3.presentation.search.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jess.nbcamp.challnge3.databinding.SearchListImageItemBinding
import com.jess.nbcamp.challnge3.databinding.UnknownItemBinding

class SearchListAdapter(
    private val onClick: (SearchListItem) -> Unit
) : ListAdapter<SearchListItem, SearchListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<SearchListItem>() {
        override fun areItemsTheSame(
            oldItem: SearchListItem,
            newItem: SearchListItem
        ): Boolean = if (oldItem is SearchListItem.ImageItem && newItem is SearchListItem.ImageItem) {
            oldItem.title == newItem.title
        } else {
            oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchListItem,
            newItem: SearchListItem
        ): Boolean = oldItem == newItem
    }
) {

    enum class SearchItemViewType {
        IMAGE
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: SearchListItem)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchListItem.ImageItem -> SearchItemViewType.IMAGE.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            SearchItemViewType.IMAGE.ordinal ->
                ImageViewHolder(
                    SearchListImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )

            else -> UnknownViewHolder(
                UnknownItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ImageViewHolder(
        private val binding: SearchListImageItemBinding,
        private val onClick: (SearchListItem) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchListItem) = with(binding) {
            if (item is SearchListItem.ImageItem) {
                title.text = item.title
                thumbnail.load(item.thumbnail)

                container.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    class UnknownViewHolder(
        binding: UnknownItemBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchListItem) = Unit
    }
}
