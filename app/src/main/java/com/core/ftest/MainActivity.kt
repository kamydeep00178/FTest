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

                email ?: activityMainBinding.userEmail.setError(getString(R.string.validEmail))
                password ?: activityMainBinding.userPassword.setError(getString(R.string.validPassword))
                confirmPassword ?: activityMainBinding.userConfirmPassword.setError(getString(R.string.validConfirmPassword))



                email?.let {
                    if (!email!!.matches(emailPatteren.toRegex())) {
                        Log.e("TAG", "Enter Valid Email")
                        activityMainBinding.userEmail.setError(getString(R.string.validEmail))
                    } else if (password?.length!! <= 8) {
                        activityMainBinding.userPassword.setError(getString(R.string.lengthPassword))

                    } else if (!password.equals(confirmPassword)) {
                        activityMainBinding.userPassword.setError(getString(R.string.matchPassword))
                    } else {
                        Toast.makeText(this, getString(R.string.welcomeMsg), Toast.LENGTH_SHORT).show()
                    }
                }


        })


    }
}