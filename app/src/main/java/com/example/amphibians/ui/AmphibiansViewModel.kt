package com.example.amphibians.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.network.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val amphibians: List<Amphibian>): AmphibiansUiState
    data object Error: AmphibiansUiState
    data object Loading: AmphibiansUiState
}

class AmphibiansViewModel(val amphibiansRepository: AmphibiansRepository) : ViewModel(){
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians(){
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException){
                AmphibiansUiState.Error
            } catch (e: HttpException){
                AmphibiansUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                AmphibiansViewModel(amphibiansRepository = application.container.amphibiansRepository)
            }
        }
    }

}