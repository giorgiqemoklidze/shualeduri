package com.example.memenator.viewmodels

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memenator.models.ResponseModel
import com.example.memenator.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<ResponseModel>().apply {
        mutableListOf<ResponseModel>()
    }

    val _itemsLiveData : MutableLiveData<ResponseModel> = itemsLiveData



    fun init(){
        CoroutineScope(Dispatchers.IO).launch {
            getItems()
        }
    }

    private suspend fun  getItems(){




       try{ val itemsList = RetrofitService.RetrofitService().getList()


            val items = itemsList.body()
            itemsLiveData.postValue(items)


           }catch (e: IOException) {
           d("msg","Error checking internet connection" )
       }




    }
}