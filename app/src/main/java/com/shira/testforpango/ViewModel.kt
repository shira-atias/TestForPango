package com.shira.testforpango

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shira.testforpango.database.CryptoRepository
import com.shira.testforpango.model.Crypto
import kotlinx.coroutines.Dispatchers

class ViewModel(application: Application):AndroidViewModel(application) {

    private var cryptoLiveData:MutableLiveData<MutableList<Crypto>> = MutableLiveData()
    private val apiManeger = ApiManager(application)
    private var repository = CryptoRepository(application,Dispatchers.Main)

    fun getAllCryptoApi():MutableLiveData<MutableList<Crypto>>{
        apiManeger.getDataToDatabase(cryptoLiveData)
        return cryptoLiveData
    }

    fun getAllCrytoDatabase() = repository.getAllCryptoList()


}