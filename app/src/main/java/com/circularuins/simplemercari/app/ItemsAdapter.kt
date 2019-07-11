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

class ItemsAdapter(
    private val context: Context,
    private val items: List<Item>
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {
    private val inflator = LayoutInflater.from(context)

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = inflator.inflate(R.layout.items_list_row, parent, false)

        val viewHolder = ItemViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        // テキスト
        holder.price.text = item.price
        holder.name.text = item.name
        holder.like.text = item.numLikes.toString()
        holder.comment.text = item.numComments.toString()

        // 画像
        Glide.with(context).load(item.photo).into(holder.itemImage)
        holder.soldOut.visibility =  if (item.isSoldOut) View.GONE else View.VISIBLE
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView = view.findViewById(R.id.text_price)
        val itemImage: ImageView = view.findViewById(R.id.image_item)
        val soldOut: ImageView = view.findViewById(R.id.image_sold_out)
        val name: TextView = view.findViewById(R.id.text_name)
        val like: TextView = view.findViewById(R.id.text_like)
        val comment: TextView = view.findViewById(R.id.text_comment)
    }
}