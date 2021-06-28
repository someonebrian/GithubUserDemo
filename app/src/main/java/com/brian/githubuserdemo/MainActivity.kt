package com.brian.githubuserdemo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.brian.githubuserdemo.adapter.UserListAdapter
import com.brian.githubuserdemo.repository.UserRepository
import com.brian.githubuserdemo.viewmodel.UserFactory
import com.brian.githubuserdemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userFactory: UserFactory
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRepository = UserRepository()
        userFactory = UserFactory(userRepository)
        userViewModel = ViewModelProvider(this, userFactory).get(UserViewModel::class.java)

        call_api.setOnClickListener {
            val dialog = ProgressDialog.show(
                this, "",
                "Loading...", true
            )
            dialog.show()

            userViewModel.callUsers().observe(this, Observer {
                dialog.dismiss()

                user_list.apply {
                    adapter = UserListAdapter(it)
                    layoutManager = LinearLayoutManager(context)
                }
            })
        }
    }
}