package com.example.amphibians.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val msg: String): AmphibiansUiState
    data object Error: AmphibiansUiState
    data object Loading: AmphibiansUiState
}

class AmphibiansViewModel() : ViewModel(){
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians(){
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success("success")
            } catch (e: IOException){
                AmphibiansUiState.Error
            } catch (e: HttpException){
                AmphibiansUiState.Error
            }
        }
    }

}