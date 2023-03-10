package com.example.pokedex_project.Signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import com.example.pokedex_project.MainActivity
import com.example.pokedex_project.model.Routes

class SignupViewModel:ViewModel() {

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword : LiveData<String> = _repeatPassword

    private val _isRegisterEnabled = MutableLiveData<Boolean>(false)
    val isRegisterEnabled : LiveData<Boolean> = _isRegisterEnabled

    fun onSignuChanged(name:String, password:String, repeatPassword:String){
        _name.value = name
        _password.value = password
        _repeatPassword.value = repeatPassword
        _isRegisterEnabled.value = enableRegister(name, password, repeatPassword)

    }


    fun enableRegister(name:String, password: String, repeatPassword: String) =
        (password.length > 6 && password==repeatPassword)

}

fun signupPressed2(navController: NavController){
    /*
    *
    *Ivan trabaja aquí*
    *
    *
     */
    navController.navigate(Routes.Login.route)
}