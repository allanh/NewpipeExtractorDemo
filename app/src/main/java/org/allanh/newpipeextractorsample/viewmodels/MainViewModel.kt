package org.allanh.newpipeextractorsample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.allanh.newpipeextractorsample.data.UseCaseResult
import org.allanh.newpipeextractorsample.domain.usecases.GetYoutubeUrlUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getYoutubeUrlUseCase: GetYoutubeUrlUseCase
) : ViewModel() {
    private val _youtubeUrlState = MutableStateFlow<Result<String>>(Result.Loading)
    val youtubeUrlState: StateFlow<Result<String>> = _youtubeUrlState.asStateFlow()

    fun getYoutubeUrl(url: String) {
        viewModelScope.launch {
            val result = getYoutubeUrlUseCase.run(GetYoutubeUrlUseCase.Params(url))
            _youtubeUrlState.value = when (result) {
                is UseCaseResult.Success -> {
                    Result.Success(result.data ?: "")
                }
                is UseCaseResult.Failure -> {
                    Result.Error(result.error)
                }
            }
        }
    }
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}