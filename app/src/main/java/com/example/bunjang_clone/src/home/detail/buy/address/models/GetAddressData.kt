package com.example.bunjang_clone.src.home.detail.buy.address.models

data class GetAddressData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<GetAddress>
)
data class GetAddress(
    val address: String,
    val detailAddress: String,
    val receiverName: String,
    val receiverPhoneNum: String,
    val shippingIdx: Int,
    val status: String
)