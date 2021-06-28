package com.brian.githubuserdemo.repository

import com.brian.githubuserdemo.api.GithubAPI
import com.brian.githubuserdemo.di.NetworkModule
import com.brian.githubuserdemo.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun loadUserInfo(task: OnTaskFinish) {
        val githubAPI = NetworkModule.client.create(GithubAPI::class.java)
        githubAPI.getUsers().enqueue(object : Callback<List<UserData>> {
            override fun onResponse(
                call: Call<List<UserData>>,
                response: Response<List<UserData>>
            ) {
                val list = ArrayList<UserData>()

                if (response.isSuccessful && response.body() != null) {
                    response.body()?.forEach {
                        list.add(it)
                    }
                }

                task.onFinish(list)
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
            }
        })
    }

    interface OnTaskFinish {
        fun onFinish(data: ArrayList<UserData>)
    }
}