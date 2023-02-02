package com.shira.testforpango.adpter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shira.testforpango.model.Crypto
import com.shira.testforpango.R
import com.shira.testforpango.databinding.ItemBinding
import java.text.DecimalFormat

class CryptoAdapter(val cryptoList:MutableList<Crypto>):RecyclerView.Adapter<CryptoAdapter.ViewHolder>(){
    lateinit var context: Context

    class ViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val binding = ItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = cryptoList[position]

        with(holder){
            binding.tvName.text = crypto.name
            binding.tvSymbol.text = crypto.symbol
            val priceFormt = DecimalFormat("##,###.#####").format(crypto.price)
            binding.tvPrice.text = "$ ${priceFormt}... "
            if (crypto.tag)
                binding.ivMineable.setImageResource(R.drawable.ic_baseline_check_24)
            else
                binding.ivMineable.setImageResource(R.drawable.ic_baseline_clear_24)

            itemView.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle(crypto.name)
                    .setMessage("$ ${crypto.price}")
                    .create().show()
            }
        }
    }

    override fun getItemCount() = cryptoList.size
}