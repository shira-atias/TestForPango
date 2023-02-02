package com.shira.testforpango

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.shira.testforpango.database.CryptoRepository
import com.shira.testforpango.model.Crypto
import com.shira.testforpango.model.CryptoList
import kotlinx.coroutines.Dispatchers
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=03cc0c29-ce37-4d10-9583-a81d9a98cbbd&convert=USD&start=1&limit=100

class ApiManager (val activity: Application){
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pro-api.coinmarketcap.com/v1/").build()
        .create(CoinMarketCapService::class.java)
    val apiKey = "03cc0c29-ce37-4d10-9583-a81d9a98cbbd"
    fun getDataToDatabase(cryptoLiveData:MutableLiveData<MutableList<Crypto>>){
        val retrofitData = retrofit.getCryptoList(apiKey,"USD",1,5000)
        retrofitData.enqueue(object :Callback<CryptoList>{

            override fun onResponse(call: Call<CryptoList>, response: Response<CryptoList>) {
                val cryptoList = response.body()
                if (cryptoList != null) {
                    val crypto = cryptoList.data
                    var mineable = false
                    val cryptoArr = mutableListOf<Crypto>()
                    for (cor in crypto) {
                        mineable = getTag(cor.tags)
                        cryptoArr.add(Crypto(cor.name,cor.symbol, cor.quote.USD.price, mineable))
                    }
                    val f = CryptoRepository(activity, Dispatchers.Main)
                    f.addToDatabase(cryptoArr)
                    cryptoLiveData.postValue(cryptoArr)
                }
            }

            override fun onFailure(call: Call<CryptoList>, t: Throwable) {
                print("noInternt")
            }
        })
    }
    private fun getTag(tags:List<String>):Boolean{
        for (tag in tags){
            if (tag == "mineable"){
                return true
            }
        }
        return false
    }

}



interface CoinMarketCapService {
    @GET("cryptocurrency/listings/latest")
    fun getCryptoList(
        @Query("CMC_PRO_API_KEY") apiKey: String,
        @Query("convert") convert: String,
        @Query("start") start: Int,
        @Query("limit") limit: Int
    ): Call<CryptoList>
}