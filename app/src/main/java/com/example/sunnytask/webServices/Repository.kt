package com.example.sunnytask.webServices

import com.example.sunnytask.MsgList
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(
        hashMap: HashMap<String, String>
    ): Response<String> {
        return apiService.login(hashMap)
    }

    suspend fun getMessageHistory(
        phone: String, page: Int, entry: Int
    ): Response<MsgList> {
        return apiService.getMessageHistory(phone, page, entry)
    }
}
