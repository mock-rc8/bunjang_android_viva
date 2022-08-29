package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.HomeAdResponse

interface HomeAdActivityInterface {

    fun onHomAdSuccess(response : HomeAdResponse)

    fun onHomAdFail(message : String)

}