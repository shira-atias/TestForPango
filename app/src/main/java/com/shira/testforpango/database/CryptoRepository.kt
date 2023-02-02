package com.shira.testforpango.database

import android.app.Application
import com.shira.testforpango.model.Crypto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CryptoRepository(application: Application, override val coroutineContext: CoroutineContext):CoroutineScope {
    private lateinit var cryptoDao: CryptoDao

    init {
        val db = application.let { AppDatabase.getDatabase(it) }
        if (db != null){
            cryptoDao = db.cryptoDao()
        }
    }

    fun addToDatabase(cryptoList: MutableList<Crypto>){
        launch(Dispatchers.IO ) {
            cryptoDao.insertCryptoList(cryptoList)
        }
    }

    fun getAllCryptoList() = cryptoDao.getAllCryptoListDatabase()
}