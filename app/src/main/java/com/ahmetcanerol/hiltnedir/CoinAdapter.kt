package com.ahmetcanerol.hiltnedir
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmetcanerol.hiltnedir.model.Datum
import javax.inject.Inject

class CoinAdapter @Inject constructor():RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    var coins: List<Datum> = emptyList()
    var onItemClick: ((Datum) -> Unit)? = null
    fun setCoin(coin: ArrayList<Datum>) {
        this.coins = coin.toMutableList() as ArrayList<Datum>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.coins_items,parent,false);
        return CoinAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: CoinAdapter.ViewHolder, position: Int) {
       holder.coinName.text=coins?.get(position).name
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(coins[position])
        }

    }
    override fun getItemCount(): Int {
        return coins.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
         var coinName:TextView=view.findViewById(R.id.coinName)
    }
}