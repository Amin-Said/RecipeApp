package com.am.recipeapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.am.recipeapp.utils.replaceFragment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.recipeapp.R
import com.am.recipeapp.databinding.FragmentListBinding
import com.am.recipeapp.model.Recipe
import com.am.recipeapp.model.RecipesResponse
import com.am.recipeapp.utils.Config
import com.am.recipeapp.viewmodel.RecipesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ListFragment : Fragment(), RecipesAdapter.OnRecipesListener {

    private val recipesViewModel by viewModel<RecipesViewModel>()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mRecipeList: MutableList<Recipe>
    private val recipesAdapter = RecipesAdapter(arrayListOf(), this)

    private val recipesDataObserver = Observer<RecipesResponse> {
        if (!it.recipes.isNullOrEmpty()) {
            binding.progressbar.visibility = View.GONE
            binding.errorApi.visibility = View.GONE
            binding.fakeDataTimer.visibility = View.GONE
            binding.rvRecipes.visibility = View.VISIBLE
            mRecipeList = it.recipes.toMutableList()
            recipesAdapter.updateRecipesList(it.recipes)
        } else {
            binding.progressbar.visibility = View.GONE
            binding.rvRecipes.visibility = View.GONE
            binding.errorApi.visibility = View.GONE
            binding.errorNoData.visibility = View.VISIBLE
        }
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    private val recipesErrorObserver = Observer<String> {
        if (it != null) {
            binding.errorApi.text = "Error on loading data wait to get fake data \n $it"
            binding.progressbar.visibility = View.GONE
            binding.rvRecipes.visibility = View.GONE
            binding.errorApi.visibility = View.VISIBLE
            binding.errorNoData.visibility = View.GONE

            getFakeDate()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = recipesAdapter
            isNestedScrollingEnabled = false
        }
        removeBackButton()
        recipesViewModel.getAllRecipes(Config.KEY_VALUE, 20.toString(), "vegetarian", "true")
        if (recipesViewModel.showLoading.get()) {
            binding.progressbar.visibility = View.VISIBLE
            binding.rvRecipes.visibility = View.GONE
            binding.errorApi.visibility = View.GONE
            binding.errorNoData.visibility = View.GONE
        }
        recipesViewModel.recipesList.observe(viewLifecycleOwner, recipesDataObserver)
        recipesViewModel.showError.observe(viewLifecycleOwner, recipesErrorObserver)


    }

    private fun removeBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(false)
    }

    override fun onRecipesClick(pos: Int) {
        val clickedRecipe = mRecipeList[pos]
        (activity as MainActivity).replaceFragment(
            DetailsFragment.newInstance(clickedRecipe),
            R.id.fragment_container, "RecipeDetails"
        )

    }

    private fun getFakeDate() {
        binding.fakeDataTimer.visibility = View.VISIBLE
        lifecycleScope.launch {
            for (i in 5 downTo 1) {
                delay(1000L)
                binding.fakeDataTimer.text = (i - 1).toString()
            }
            recipesViewModel.getAllFakeRecipes()
        }
    }


}