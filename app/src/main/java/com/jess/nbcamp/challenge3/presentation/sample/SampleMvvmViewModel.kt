package com.jess.nbcamp.challenge3.presentation.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleMvvmViewModel : ViewModel() {

    private val _event: MutableLiveData<String> = MutableLiveData()
    val event: LiveData<String> get() = _event

    fun onClickTest(action: String) {
        // 이런저런 데이터 처리, 이것저것 처리 등등...

        val result = "result $action"
        _event.value = result
    }
}