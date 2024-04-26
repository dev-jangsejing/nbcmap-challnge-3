package com.jess.nbcamp.challnge3.presentation.search.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jess.nbcamp.challnge3.databinding.BookmarkListItemBinding

class BookmarkListAdapter(
    private val onBookmark: (BookmarkListItem) -> Unit,
) : ListAdapter<BookmarkListItem, BookmarkListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<BookmarkListItem>() {

        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템이 서로 같은지 비교한다. 보통 고유한 ID 값을 체크한다.
        override fun areItemsTheSame(
            oldItem: BookmarkListItem,
            newItem: BookmarkListItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
        override fun areContentsTheSame(
            oldItem: BookmarkListItem,
            newItem: BookmarkListItem
        ): Boolean = oldItem == newItem
    }
) {

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: BookmarkListItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        BookmarkViewHolder(
            BookmarkListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class BookmarkViewHolder(
        private val binding: BookmarkListItemBinding,
    ) : ViewHolder(binding.root) {

        override fun onBind(item: BookmarkListItem) = with(binding) {
            title.text = item.title
            bookmark.isChecked = item.bookmarked
        }
    }
}
