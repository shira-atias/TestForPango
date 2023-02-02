package com.shira.testforpango.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shira.testforpango.Constants.CRYPTO

@Entity(tableName = CRYPTO)
data class Crypto(
    @PrimaryKey
    val name: String,
    val symbol: String,
    val price: Double,
    val tag: Boolean
)