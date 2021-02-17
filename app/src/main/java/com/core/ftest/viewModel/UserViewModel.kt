package com.core.ftest.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.ftest.model.UserModel

class UserViewModel : ViewModel() {
    var emailAddress = MutableLiveData<String>() ;
    var userPassword = MutableLiveData<String>() ;
    var userConfirmPassword = MutableLiveData<String>() ;
    var userMutableLiveData = MutableLiveData<UserModel>();

    fun getUser() : MutableLiveData<UserModel>
    {
        if(userMutableLiveData!=null)
        {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }

    fun onClick()
    {
        var userModel =
            emailAddress.value?.let { userPassword.value?.let { it1 ->
                userConfirmPassword.value?.let { it2 ->
                    UserModel(it,
                        it1, it2
                    )
                }
            } }
            userMutableLiveData.value=userModel
    }

}