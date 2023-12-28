package com.example.sunnytask.webServices

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _loginResponse = MutableLiveData<Any>()
    val loginResponse: LiveData<Any> get() = _loginResponse
    private val _msgListResponse = MutableLiveData<Any>()
    val msgListResponse: LiveData<Any> get() = _msgListResponse

    fun login(
        hashMap: HashMap<String, String>
    ) {
        viewModelScope.launch {
            try {
                _loginResponse.postValue(ApiResponse.Loading(true))
                val response = repository.login(hashMap)
                if (response.isSuccessful)
                    _loginResponse.postValue(ApiResponse.Success(response.body()))
                else
                    _loginResponse.postValue(ApiResponse.Error(response.message().toString()))
            } catch (e: Exception) {
                _loginResponse.postValue(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    fun msgList(
        phone: String, page: Int, entry: Int
    ) {
        viewModelScope.launch {
            try {
                _msgListResponse.postValue(ApiResponse.Loading(true))
                val response = repository.getMessageHistory(
                    phone, page, entry

                )
                if (response.isSuccessful)
                    _msgListResponse.postValue(ApiResponse.Success(response.body()))
                else
                    _msgListResponse.postValue(ApiResponse.Error(response.message().toString()))
            } catch (e: Exception) {
                _msgListResponse.postValue(ApiResponse.Error(e.message.toString()))
            }
        }
    }

}
