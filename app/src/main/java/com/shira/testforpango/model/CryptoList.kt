package com.shira.testforpango.model


data class CryptoList(
    val data: List<CryptoData>
)

data class CryptoData(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: Quote,
    val tags: List<String>
)

data class Quote(
    val USD: USD
)

data class USD(
    val price: Double,
)
