package com.circularuins.simplemercari.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.circularuins.simplemercari.R
import com.circularuins.simplemercari.app.viewdata.Item
import com.circularuins.simplemercari.app.viewdata.ListViewData
import com.circularuins.simplemercari.app.viewdata.ListViewType

class ItemsAdapter(
    private val context: Context,
    private val items: List<ListViewData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflator = LayoutInflater.from(context)

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type.rawValue

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(ListViewType.typeOf(viewType)) {
            ListViewType.Item -> {
                val view = inflator.inflate(R.layout.items_list_row, parent, false)
                return ItemViewHolder(view, context)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val item = items[position] as Item
            holder.setData(item)
        }
    }

    class ItemViewHolder(
        private val view: View,
        private val context: Context,
        private val price: TextView = view.findViewById(R.id.text_price),
        private val itemImage: ImageView = view.findViewById(R.id.image_item),
        private val soldOut: ImageView = view.findViewById(R.id.image_sold_out),
        private val name: TextView = view.findViewById(R.id.text_name),
        private val like: TextView = view.findViewById(R.id.text_like),
        private val comment: TextView = view.findViewById(R.id.text_comment)) : RecyclerView.ViewHolder(view) {

        fun setData(item: Item) {
            // テキスト
            price.text = item.price
            name.text = item.name
            like.text = item.numLikes.toString()
            comment.text = item.numComments.toString()

            // 画像
            Glide.with(context).load(item.photo).into(itemImage)
            soldOut.visibility =  if (item.isSoldOut) View.GONE else View.VISIBLE
        }
    }
}