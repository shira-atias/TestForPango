package com.shira.testforpango.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shira.testforpango.model.Crypto


@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoList(cryptoList: MutableList<Crypto>)

    @Query("SELECT * FROM crypto")
    fun getAllCryptoListDatabase():LiveData<MutableList<Crypto>>
}