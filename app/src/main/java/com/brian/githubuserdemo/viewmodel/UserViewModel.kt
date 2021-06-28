package com.brian.githubuserdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brian.githubuserdemo.model.UserData
import com.brian.githubuserdemo.repository.UserRepository

class UserViewModel(var userRepository: UserRepository): ViewModel() {
    private var userinfoLiveData = MutableLiveData<ArrayList<UserData>>()

    fun callUsers():LiveData<ArrayList<UserData>> {
        userRepository.loadUserInfo(object : UserRepository.OnTaskFinish {
            override fun onFinish(data:  ArrayList<UserData>) {
                userinfoLiveData.postValue(data)
            }
        })
        return userinfoLiveData
    }


}