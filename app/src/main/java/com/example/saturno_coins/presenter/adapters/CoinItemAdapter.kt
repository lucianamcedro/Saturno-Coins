package com.example.saturno_coins.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saturno_coins.databinding.CoinListItemBinding
import com.example.saturno_coins.domain.model.CoinItem

class CoinItemAdapter(
    private val onClickListener: (coin: CoinItem) -> Unit
) : ListAdapter<CoinItem, CoinItemAdapter.CoinItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = CoinListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CoinItemViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CoinItemViewHolder(
        private val binding: CoinListItemBinding,
        private val onClickListener: (coin: CoinItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: CoinItem) {

            Glide
                .with(binding.root.context)
                .load(coin.getImageCoin())
                .centerCrop()
                .into(binding.imgcoin)

            binding.nameCoin.text = coin.name
            binding.priceUsd.text = coin.price_usd.toString()
            binding.idCoin.text = coin.asset_id

            binding.root.setOnClickListener {
                onClickListener.invoke(coin)
            }
        }
    }
}
private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinItem>() {
    override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
        return oldItem.asset_id == newItem.asset_id
    }

    override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
        return oldItem == newItem
    }
}
