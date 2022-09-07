package com.example.bunjang_clone.src.home.detail.buy.address.models

data class DeleteResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: DeleteResult
)
data class DeleteResult(
    val shippingIdx: Int,
    val status: String
)