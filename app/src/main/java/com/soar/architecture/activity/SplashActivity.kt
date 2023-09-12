package com.soar.architecture.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.soar.architecture.base.BaseActivity
import com.soar.architecture.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        val SPLASH_DELAY=3000L
    }
}