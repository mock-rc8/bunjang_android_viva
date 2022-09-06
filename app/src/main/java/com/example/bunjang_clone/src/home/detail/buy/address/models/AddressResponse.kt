package com.example.bunjang_clone.src.home.detail.buy.address.models

data class AddressResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)
data class Result(
    val address: String,
    val detailAddress: String,
    val receiverName: String,
    val receiverPhoneNum: String,
    val shippingIdx: Int,
    val status: String
)