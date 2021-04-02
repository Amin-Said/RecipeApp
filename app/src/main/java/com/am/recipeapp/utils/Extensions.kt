package com.am.recipeapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.am.recipeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    backStackTag: String? = null
) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, circularProgressDrawable: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(circularProgressDrawable).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load(uri).into(this)
}