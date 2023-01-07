package com.ahmetcanerol.hiltnedir.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetcanerol.hiltnedir.model.Root
import com.ahmetcanerol.hiltnedir.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CoinViewModel @Inject constructor(private val repository: Repository):ViewModel() {
    private val coinLiveData = MutableLiveData<Root>()
    fun getCoins() = coinLiveData
    init {
        loadCoins()
    }
    private fun loadCoins(){
        viewModelScope.launch {
             val coins=repository.getCoin()
             if(coins.isSuccessful){
                    coinLiveData.postValue(coins.body())
                }
            else{
                 println(coins.errorBody())
                 println(coins.message())
             }
        }
    }

}
