package com.example.bunjang_clone.src.register

import com.example.bunjang_clone.src.register.models.CategoryData
import com.example.bunjang_clone.src.register.models.RegisterResponse

interface RegisterActivityInterface {

    fun onRegisterSuccess(response : RegisterResponse)

    fun onRegisterFail(message : String)

    fun onMainCategorySuccess(response : CategoryData)

    fun onMainCategoryFail(message : String)

    fun onSubCategorySuccess(response : CategoryData)

    fun onSubCategoryFail(message : String)
}