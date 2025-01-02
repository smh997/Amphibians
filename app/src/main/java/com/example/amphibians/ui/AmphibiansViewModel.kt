package com.example.amphibians.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.NetworkAmphibiansRepository
import com.example.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
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
                val amphibiansRepository = NetworkAmphibiansRepository()
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException){
                AmphibiansUiState.Error
            } catch (e: HttpException){
                AmphibiansUiState.Error
            }
        }
    }

}