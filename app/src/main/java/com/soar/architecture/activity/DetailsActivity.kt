package com.soar.architecture.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.soar.architecture.base.BaseActivity
import com.soar.architecture.bean.RecipesItem
import com.soar.architecture.databinding.ActivityDetailsBinding
import java.io.Serializable

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    private lateinit var recipesItem: RecipesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        initView()
    }

    private fun getIntentData() {
        recipesItem = (intent.getSerializableExtra(RECIPE_ITEM_KEY)?:RecipesItem()) as RecipesItem
    }

    private fun initView() {
        binding.tvName.text = recipesItem.name
        binding.tvHeadline.text = recipesItem.headline
        binding.tvDescription.text = recipesItem.description
        Glide.with(this).load(recipesItem.image)
            .into(binding.ivRecipeImage)
    }

    companion object{
        val RECIPE_ITEM_KEY="RECIPE_ITEM_KEY"

        fun open(activity: Activity,bean: Serializable){
            val intent = Intent(activity,DetailsActivity::class.java)
            intent.putExtra(RECIPE_ITEM_KEY,bean)
            activity.startActivity(intent)
        }
    }

}