package com.sprybit.app.adpater

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sprybit.app.Quote
import com.sprybit.app.R
import com.sprybit.app.adpater.ContentAdapter.ViewHolder
import com.sprybit.app.util.inflate
import kotlin.properties.Delegates

/**
 * Created by master on 19-Jun-17.
 */
class ContentAdapter(var lists: List<Quote>) : RecyclerView.Adapter<ViewHolder>() {

    var items: List<Quote> by Delegates.observable(emptyList()) {
        prop, old, new ->
    }

    override fun getItemCount() = lists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lists.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(parent!!.inflate(R.layout.view_item))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(content: Quote) = with(itemView) {
            val text = findViewById(R.id.text) as TextView
            text.text = content.quoteText
        }
    }

}


