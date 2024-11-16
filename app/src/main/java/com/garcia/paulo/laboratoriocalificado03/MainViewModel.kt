package com.garcia.paulo.laboratoriocalificado03

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    val profeList = MutableLiveData<List<ProfeResponse>>()

    val isLoading = MutableLiveData<Boolean>()

    val errorApi = MutableLiveData<String>()

    init {
        getAllProfesores()
    }

    private fun getAllProfesores() {
        isLoading.postValue(true)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("API_CALL", "Llamando al API...")
                val response = getRetrofit().create(ProfeApi::class.java).getProfesores()

                response.teachers?.let {
                    isLoading.postValue(false)
                    profeList.postValue(it)
                }

            } catch (e: Exception) {
                errorApi.postValue(e.message)
                isLoading.postValue(false)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
