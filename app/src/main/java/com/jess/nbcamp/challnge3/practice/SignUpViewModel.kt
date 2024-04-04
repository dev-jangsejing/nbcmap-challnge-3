package com.jess.nbcamp.challnge3.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _event: MutableLiveData<SignUpEvent> = MutableLiveData()
    val event: LiveData<SignUpEvent> get() = _event

    /**
     * 회원가입 버튼을 클릭합니다.
     *
     * @param name ...
     */
    fun onClickSignUp(
        name: String,
        id: String,
        password: String,
    ) {
        // 회원의 정보를 가져와서 서버로 데이터를 보낸 후 이것저것 처리함
        _event.value = SignUpEvent.SignUpSuccess(
            SignUpMember(
                name,
                id,
                password
            )
        )

    }
}