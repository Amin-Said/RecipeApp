package com.am.recipeapp.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.am.recipeapp.databinding.FragmentDetailsBinding
import com.am.recipeapp.model.Recipe
import com.am.recipeapp.utils.getProgressDrawable
import com.am.recipeapp.utils.loadImage


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(data: Recipe) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("recipe", data)
            }
        }
    }

    private var recipe: Recipe? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        recipe = arguments?.getParcelable("recipe")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enableBackButton()
        binding.titleDetails.text = recipe?.title
        binding.summaryDetails.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(recipe?.summary, Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(recipe?.summary)
        }
        binding.ivDetails.loadImage(recipe?.image, getProgressDrawable(binding.root.context))


    }

    private fun enableBackButton() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }

}