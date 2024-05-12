package com.jess.nbcamp.challenge3.presentation.search.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jess.nbcamp.challenge3.databinding.SearchListImageItemBinding
import com.jess.nbcamp.challenge3.databinding.SearchListVideoItemBinding
import com.jess.nbcamp.challenge3.databinding.UnknownItemBinding

class SearchListAdapter(
    private val onClick: (SearchListItem) -> Unit,
    private val onBookmark: (SearchListItem) -> Unit,
) : ListAdapter<SearchListItem, SearchListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<SearchListItem>() {

        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템이 서로 같은지 비교한다. 보통 고유한 ID 값을 체크한다.
        override fun areItemsTheSame(
            oldItem: SearchListItem,
            newItem: SearchListItem
        ): Boolean = oldItem.id == newItem.id


        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
        override fun areContentsTheSame(
            oldItem: SearchListItem,
            newItem: SearchListItem
        ): Boolean = oldItem == newItem
    }
) {

    enum class SearchItemViewType {
        IMAGE, VIDEO
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: SearchListItem)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchListItem.ImageItem -> SearchItemViewType.IMAGE.ordinal
        is SearchListItem.VideoItem -> SearchItemViewType.VIDEO.ordinal
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
                    onClick,
                    onBookmark,
                )

            SearchItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    SearchListVideoItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick,
                    onBookmark,
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
        private val onClick: (SearchListItem) -> Unit,
        private val onBookmark: (SearchListItem) -> Unit,
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchListItem) = with(binding) {
            if (item is SearchListItem.ImageItem) {
                title.text = item.title
                thumbnail.load(item.thumbnail)
                bookmark.isChecked = item.bookmarked

                container.setOnClickListener {
                    onClick(item)
                }

                bookmark.setOnClickListener {
                    if (item.bookmarked != bookmark.isChecked) {
                        onBookmark(item)
                    }
                }
            }
        }
    }

    class VideoViewHolder(
        private val binding: SearchListVideoItemBinding,
        private val onClick: (SearchListItem) -> Unit,
        private val onBookmark: (SearchListItem) -> Unit,
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchListItem) = with(binding) {
            if (item is SearchListItem.VideoItem) {
                title.text = item.title
                thumbnail.load(item.thumbnail)
                bookmark.isChecked = item.bookmarked

                container.setOnClickListener {
                    onClick(item)
                }

                bookmark.setOnClickListener {
                    if (item.bookmarked != bookmark.isChecked) {
                        onBookmark(item)
                    }
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
