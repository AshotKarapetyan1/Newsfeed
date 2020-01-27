package com.grupy.newsfeed.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.grupy.newsfeed.R
import com.grupy.newsfeed.databinding.AdapterNewsListItemBinding
import com.grupy.newsfeed.model.base.ItemModel
import com.grupy.newsfeed.shared.utils.convertDateToString
import java.util.*

class NewsListAdapter(private val mItems: ArrayList<ItemModel>, val listener: (ItemModel) -> Unit,
                      val favListener: (ItemModel) -> Unit) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = DataBindingUtil.inflate<AdapterNewsListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_news_list_item, parent, false
        )
        return ViewHolder(viewHolder)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position], listener, favListener)
    }

    fun setData(items: List<ItemModel>?) {
        mItems.clear()
        items?.let { mItems.addAll(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: ItemModel, listener: (ItemModel) -> Unit, favListener:(ItemModel)-> Unit) =
            with(itemView) {
                binding.viewModel = item
                binding.root.setOnClickListener { listener(item) }

                val date = convertDateToString(item.webPublicationDate)
                date?.let {  binding.webPublicationDate.text = date }

                binding.fav.setOnClickListener {
                    item.isFav = binding.fav.isChecked
                    favListener(item)
                }
            }
    }
}