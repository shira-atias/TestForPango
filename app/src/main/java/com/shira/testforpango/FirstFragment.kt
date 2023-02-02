package com.shira.testforpango

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shira.testforpango.Constants.MINEABLE
import com.shira.testforpango.Constants.PRICE
import com.shira.testforpango.adpter.CryptoAdapter
import com.shira.testforpango.databinding.FragmentFirstBinding
import com.shira.testforpango.model.Crypto

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)


        viewModel.getAllCryptoApi().observe(viewLifecycleOwner){
            configurePage(it)
        }
        viewModel.getAllCrytoDatabase().observe(viewLifecycleOwner){
            configurePage(it)
        }

        val bundle = arguments
        val price = bundle?.getDouble(PRICE)
        val mineable = bundle?.getBoolean(MINEABLE)

        if (bundle != null){

            viewModel.getAllCryptoApi().observe(viewLifecycleOwner){
                configurePage(it,price,mineable)
            }
            viewModel.getAllCrytoDatabase().observe(viewLifecycleOwner){
                configurePage(it,price,mineable)
            }
        }
        return binding.root
    }

    private fun configurePage(it:MutableList<Crypto>?, price:Double?, mineable:Boolean?){
        if (it != null){
            var filterCryptoList = it
            if (price != null){
                filterCryptoList = it.filter { it.price > price } as MutableList<Crypto>?
            }
            if (mineable == true){
                filterCryptoList = filterCryptoList?.filter { it.tag == true } as MutableList<Crypto>?
            }
            binding.rvCrypto.layoutManager = LinearLayoutManager(context)
            binding.rvCrypto.adapter = filterCryptoList?.let { it1 -> CryptoAdapter(it1) }

        }
    }
   private fun configurePage(it: MutableList<Crypto>?){
        if (it != null){
            binding.rvCrypto.layoutManager = LinearLayoutManager(context)
            binding.rvCrypto.adapter = CryptoAdapter(it)
        }
    }

}
object Constants{
    const val PRICE = "price"
    const val MINEABLE = "mineable"
    const val CRYPTO_LIST = "crypto List"
    const val CRYPTO = "crypto"
}