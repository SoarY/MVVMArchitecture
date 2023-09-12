package com.soar.architecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soar.architecture.bean.RecipesItem
import com.soar.architecture.databinding.ItemRecipeBinding

class RecipesAdapter(private val recipes: List<RecipesItem>) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(position,recipes[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun getItem(position: Int): RecipesItem {
        return recipes[position]
    }

    fun interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    class RecipeViewHolder(private val itemBinding: ItemRecipeBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int,recipesItem: RecipesItem, itemClickListener: ItemClickListener?) {
            itemBinding.tvCaption.text = recipesItem.description
            itemBinding.tvName.text = recipesItem.name
            Glide.with(itemBinding.root.context).load(recipesItem.thumb)
                .into(itemBinding.ivRecipeItemImage)
            itemBinding.rlRecipeItem.setOnClickListener { itemClickListener?.onItemClick(position) }
        }
    }
}

