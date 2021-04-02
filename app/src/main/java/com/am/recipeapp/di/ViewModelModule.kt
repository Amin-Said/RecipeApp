package com.am.recipeapp.di

import com.am.recipeapp.viewmodel.RecipesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        RecipesViewModel(repository = get())
    }

}