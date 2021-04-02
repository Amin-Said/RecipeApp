package com.am.recipeapp.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.recipeapp.model.RecipesResponse
import com.am.recipeapp.repository.RecipesRepository
import com.am.recipeapp.utils.AppResult
import com.am.recipeapp.utils.FakeData
import com.am.recipeapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class RecipesViewModel(private val repository: RecipesRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val recipesList = MutableLiveData<RecipesResponse>()
    val showError = SingleLiveEvent<String>()

    fun getAllRecipes(key: String, number: String, tags: String, limitLicence: String) {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getAllRecipes(key, number, tags, limitLicence)
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    recipesList.value = result.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    // this is a use case for the app version on github without an api key and in case the limit of the api hit was reached
    // or any other error
    fun getAllFakeRecipes() {
        showLoading.set(true)
        recipesList.value = FakeData.generateFakeDataResponse()
    }
}