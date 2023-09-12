package com.soar.architecture.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.soar.architecture.base.BaseActivity
import com.soar.architecture.bean.LoginResponse
import com.soar.architecture.data.LoginRepository
import com.soar.architecture.data.Resource
import com.soar.architecture.data.local.LocalData
import com.soar.architecture.databinding.ActivityLoginBinding
import com.soar.architecture.utils.ErrorMapper
import com.soar.architecture.vm.LoginViewModel
import java.io.Serializable

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeViewModel()
    }

    fun observeViewModel() {
        val localData = LocalData(this)
        val loginRepository = LoginRepository()
        loginRepository.localRepository=localData
        loginViewModel.loginRepository= loginRepository
        loginViewModel.loginLiveData.observe(this, ::handleLoginResult)
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> binding.loaderView.visibility= View.VISIBLE
            is Resource.Success -> status.data?.let {
                binding.loaderView.visibility= View.GONE
                RecipesListActivity.open(this)
                finish()
            }
            is Resource.DataError -> {
                binding.loaderView.visibility= View.GONE
                status.errorCode?.let {
                    Toast.makeText(this, ErrorMapper.getErrorString(this,status.errorCode), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initView() {
        binding.login.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            binding.username.text.trim().toString(),
            binding.password.text.toString()
        )
    }

    companion object{
        fun open(activity: Activity){
            val intent = Intent(activity,LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }
}