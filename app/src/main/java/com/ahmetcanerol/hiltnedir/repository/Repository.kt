package com.ahmetcanerol.hiltnedir.repository

import androidx.lifecycle.LiveData
import com.ahmetcanerol.hiltnedir.api.APIService
import com.ahmetcanerol.hiltnedir.constants.Constants


class Repository(private val apiService: APIService) {
    suspend fun getCoin()=apiService.getCoins(apikey = Constants.apikey, 50)

}