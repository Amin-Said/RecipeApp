package com.am.recipeapp.core

import com.am.recipeapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import androidx.multidex.MultiDexApplication


class RecipesApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RecipesApp)
            modules(
                listOf(
                    apiModule,
                    viewModelModule,
                    repositoryModule,
                    networkModule
                )
            )
        }
    }
}