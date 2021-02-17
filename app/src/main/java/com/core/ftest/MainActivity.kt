package com.core.ftest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.core.ftest.databinding.ActivityMainBinding
import com.core.ftest.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var userViewModel : UserViewModel
     val emailPatteren ="[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        userViewModel=ViewModelProviders.of(this).get(UserViewModel::class.java)
        activityMainBinding.userModel=userViewModel

        userViewModel.getUser().observe(this, {


                var email = it?.userEmail
                var password = it?.userPassword
                var confirmPassword = it?.userConfirmPassword

                email ?: activityMainBinding.userEmail.setError("Enter  Email")
                password ?: activityMainBinding.userPassword.setError("Enter  Password")
                confirmPassword ?: activityMainBinding.userConfirmPassword.setError("Enter  Confirm Password")



                email?.let {
                    if (!email!!.matches(emailPatteren.toRegex())) {
                        Log.e("TAG", "Enter Valid Email")
                        activityMainBinding.userEmail.setError("Enter Valid Email")
                    } else if (password?.length!! <= 8) {
                        activityMainBinding.userPassword.setError("Password Must Be Greater than Equal to 8")

                    } else if (!password.equals(confirmPassword)) {
                        activityMainBinding.userPassword.setError("Password and Confirm Password is not matched")
                    } else {
                        Toast.makeText(this, "Welcome To App", Toast.LENGTH_SHORT).show()
                    }
                }


        })


    }
}