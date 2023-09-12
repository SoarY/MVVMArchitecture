package com.soar.architecture.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.soar.architecture.adapter.RecipesAdapter
import com.soar.architecture.base.BaseActivity
import com.soar.architecture.bean.Recipes
import com.soar.architecture.data.RecipesListRepository
import com.soar.architecture.data.Resource
import com.soar.architecture.databinding.ActivityHomeBinding
import com.soar.architecture.utils.ErrorMapper
import com.soar.architecture.vm.RecipesListViewModel

class RecipesListActivity : BaseActivity<ActivityHomeBinding>() {

    private lateinit var recipesAdapter: RecipesAdapter

    private val recipesListViewModel: RecipesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        observeViewModel()
        initData()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
    }

    private fun observeViewModel() {
        val recipesListRepository = RecipesListRepository()
        recipesListViewModel.recipesListRepository=recipesListRepository

        recipesListViewModel.recipesLiveData.observe(this, ::handleRecipesList)
    }

    private fun initData() {
        recipesListViewModel.getRecipes()
    }

    private fun handleRecipesList(status: Resource<Recipes>) {
        when (status) {
            is Resource.Loading -> {
                binding.pbLoading.visibility=View.VISIBLE
                binding.tvNoData.visibility=View.GONE
                binding.rvRecipesList.visibility=View.GONE
            }
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                binding.pbLoading.visibility=View.GONE
                binding.tvNoData.visibility=View.VISIBLE
                binding.rvRecipesList.visibility=View.GONE
                status.errorCode?.let {
                    Toast.makeText(this, ErrorMapper.getErrorString(this,status.errorCode), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun bindListData(recipes: Recipes) {
        if (!(recipes.recipesList.isNullOrEmpty())) {
            binding.pbLoading.visibility=View.GONE
            binding.tvNoData.visibility=View.GONE
            binding.rvRecipesList.visibility=View.VISIBLE

            recipesAdapter = RecipesAdapter(recipes.recipesList)
            binding.rvRecipesList.adapter = recipesAdapter
            recipesAdapter.setItemClickListener(itemClickListener)
        } else {
            binding.pbLoading.visibility=View.GONE
            binding.tvNoData.visibility=View.VISIBLE
            binding.rvRecipesList.visibility=View.GONE
        }
    }

    private val itemClickListener = RecipesAdapter.ItemClickListener {
        val item = recipesAdapter.getItem(it)
        DetailsActivity.open(this,item)
    }

    companion object{
        fun open(activity: Activity){
            val intent = Intent(activity,RecipesListActivity::class.java)
            activity.startActivity(intent)
        }
    }
}