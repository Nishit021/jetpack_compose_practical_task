package com.salekart.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object CoreUtility {

    fun isInternetConnected(context : Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeConnection = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            activeConnection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeConnection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeConnection.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}