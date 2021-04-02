package com.am.recipeapp.view

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.recipeapp.databinding.RecipeItemBinding
import com.am.recipeapp.model.Recipe
import com.am.recipeapp.utils.getProgressDrawable
import com.am.recipeapp.utils.loadImage

class RecipesAdapter(
    private val recipesList: ArrayList<Recipe>, private var mRecipeListener: OnRecipesListener
) : RecyclerView.Adapter<RecipesAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(
            RecipeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mRecipeListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val itemRecipe = recipesList[position]
        holder.bindRecipe(itemRecipe)
    }


    override fun getItemCount(): Int = recipesList.size


    class RecyclerViewHolder(
        private val binding: RecipeItemBinding,
        private var mListener: OnRecipesListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindRecipe(recipe: Recipe) {
            binding.recipeTitle.text = recipe.title
            binding.recipeDetails.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(recipe.summary, Html.FROM_HTML_MODE_COMPACT)
            } else {
                @Suppress("DEPRECATION")
                Html.fromHtml(recipe.summary)
            }
            binding.recipeIv.loadImage(recipe.image, getProgressDrawable(binding.root.context))

            binding.root.setOnClickListener { mListener.onRecipesClick(adapterPosition) }

        }


    }

    fun updateRecipesList(newRecipesList: List<Recipe>) {
        recipesList.clear()
        recipesList.addAll(newRecipesList)
        notifyDataSetChanged()

    }

    interface OnRecipesListener {
        fun onRecipesClick(pos: Int)
    }


}
