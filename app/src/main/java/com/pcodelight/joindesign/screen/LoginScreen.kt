package com.pcodelight.joindesign.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pcodelight.joindesign.AuthHelper
import com.pcodelight.joindesign.Conf
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.AuthData
import com.pcodelight.joindesign.repo.AuthRepository
import com.pcodelight.joindesign.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_screen_layout.*

@Suppress("UNCHECKED_CAST")
class LoginScreen : AppCompatActivity() {
    private var viewModel: LoginViewModel = LoginViewModel(AuthRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen_layout)

        if (!AuthHelper.instance.getAuthToken().isNullOrBlank()) {
            gotoStoreSelectionPage()
        }

        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this@LoginScreen, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(AuthRepository()) as T
            }
        })
            .get(LoginViewModel::class.java)
            .apply {
                errorMsg.observe(this@LoginScreen, errorObserver)
                isLoading.observe(this@LoginScreen, loadingObserver)
                authData.observe(this@LoginScreen, authDataObserver)
            }
    }

    private fun getPasswordText() = etPassword.text.toString()
    private fun getUsernameText() = etUsername.text.toString()
    private fun initView() {
        btnLogin.setOnClickListener {
            val username = getUsernameText()
            val password = getPasswordText()

            viewModel.getAuth(username, password)
        }

        val btnLoginEnabler = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnLogin.isEnabled = getPasswordText().isNotBlank() && getUsernameText().isNotBlank()
            }
        }

        etPassword.addTextChangedListener(btnLoginEnabler)
        etUsername.addTextChangedListener(btnLoginEnabler)
    }

    private val errorObserver = Observer<String> { errorMessage ->
        if (errorMessage.isNotBlank()) {
            tvError.visibility = View.VISIBLE
            tvError.text = errorMessage
        } else {
            tvError.visibility = View.GONE
        }
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            btnLogin.isEnabled = false
            etUsername.isEnabled = false
            etPassword.isEnabled = false
            tvError.visibility = View.GONE
        } else {
            btnLogin.isEnabled = true
            etUsername.isEnabled = true
            etPassword.isEnabled = true
            tvError.visibility = View.VISIBLE
        }
    }

    private val authDataObserver = Observer<AuthData?> { authData ->
        authData?.let {
            AuthHelper.instance.setAuthToken(it)
            gotoStoreSelectionPage()
        }
    }

    private fun gotoStoreSelectionPage() {
        startActivity(Intent(this@LoginScreen, StoreSelectionScreen::class.java))
        finish()
    }
}
