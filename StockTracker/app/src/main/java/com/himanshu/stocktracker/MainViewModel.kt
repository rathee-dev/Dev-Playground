package com.himanshu.stocktracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _cryptoState = mutableStateOf(CryptoState())
    val cryptoState: State<CryptoState> = _cryptoState

    init {
        fetchCrypto()
    }

    private fun fetchCrypto(){
        viewModelScope.launch {
            try{
                val response= cryptoService.getCoins()
                _cryptoState.value = _cryptoState.value.copy(
                    list = response,
                    loading = false,
                    error =  null
                )
            }catch(e: Exception){
                _cryptoState.value = _cryptoState.value.copy(
                    loading = false,
                    error = "Error fetching Crypto-Currencies ${e.message}"
                )
            }
        }
    }
    data class CryptoState(
        val loading: Boolean = true,
        val list: List<Crypto> = emptyList(),
        val error: String? = null
    )
}
