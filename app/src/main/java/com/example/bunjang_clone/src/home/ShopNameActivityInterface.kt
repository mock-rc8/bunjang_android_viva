package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.ShopNameResponse

interface ShopNameActivityInterface {

    fun onShopNameSuccess(response : ShopNameResponse)

    fun onShopNameFail(message : String)

}