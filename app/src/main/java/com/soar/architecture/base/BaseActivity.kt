package com.soar.architecture.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.soar.architecture.vm.LoginViewModel
import java.lang.reflect.ParameterizedType

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    val TAG = javaClass.simpleName

    lateinit var context: Activity

    protected val binding: V by lazy {
        //使用反射得到viewbinding的class
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as V
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        context=this
    }
}