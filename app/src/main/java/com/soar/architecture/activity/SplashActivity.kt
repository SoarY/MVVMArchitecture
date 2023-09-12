package com.soar.architecture.activity

import android.os.Bundle
import android.os.Handler
import com.soar.architecture.base.BaseActivity
import com.soar.architecture.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            LoginActivity.open(activity)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        val SPLASH_DELAY=3000L
    }
}