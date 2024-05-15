package com.example.kedatechapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kedatechapp.R
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.databinding.ItemProductBinding
import com.example.kedatechapp.utils.load
import com.example.kedatechapp.utils.toIndonesiaRupiahFormat
import java.text.DecimalFormat

class ProductAdapter(private val onItemClicked: (product: Product) -> Unit = {}) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var items: List<Product> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Product) = with(itemBinding) {
            llContainer.setOnClickListener { onItemClicked.invoke(item) }
            ivProduct.load(item.picUrl)
            tvProduct.text = item.name
            tvDesc.text = item.desc
            tvPrice.text =
                root.context.getString(
                    R.string.text_indo_currency_symbol,
                    item.price.toIndonesiaRupiahFormat()
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}