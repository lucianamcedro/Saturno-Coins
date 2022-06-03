package com.example.saturno_coins.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saturno_coins.databinding.ListItemFavoriteCoinBinding
import com.example.saturno_coins.domain.model.CoinItem
import java.math.RoundingMode
import java.text.DecimalFormat

class CoinFavoriteAdapter(private val onClickListener: (coin: CoinItem) -> Unit) :
    ListAdapter<CoinItem, CoinFavoriteAdapter.CoinFavoriteItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CoinFavoriteAdapter.CoinFavoriteItemViewHolder {
        val binding =
            ListItemFavoriteCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinFavoriteItemViewHolder(binding, onClickListener, parent.context)
    }

    override fun onBindViewHolder(
        holder: CoinFavoriteAdapter.CoinFavoriteItemViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class CoinFavoriteItemViewHolder(
        private val binding: ListItemFavoriteCoinBinding,
        private val onClickListener: (coin: CoinItem) -> Unit,
        private val contextViewHolder: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: CoinItem) {
            binding.coinSubNameFavorite.text = coin.asset_id

            binding.coinNameFavorite.text = coin.name

            val decimalFormat = DecimalFormat("$ ###,###.##")
            decimalFormat.roundingMode = RoundingMode.UP
            val roundingPrice = decimalFormat.format(coin.price_usd)
            binding.coinValueFavorite.text = roundingPrice.toString()

            binding.coinValueFavorite.text = coin.price_usd.toString()

            Glide.with(binding.root.context)
                .load(coin.getImageCoin())
                .centerCrop()
                .into(binding.coinIconFavorite)
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
