package com.example.bunjang_clone.src.home.detail.buy.address

import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx

interface AddressActivityInterface {

    fun onPostAddressSuccess(response : AddressResponse)

    fun onPostAddressFail(message : String)

}