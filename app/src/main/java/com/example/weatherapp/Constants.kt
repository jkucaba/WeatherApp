package com.example.weatherapp

import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    const val APP_ID: String = R.string.APP_ID.toString()
    const val BASE_URL: String = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT: String = "metric"
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as android.net.ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {    //for newer versions of Android
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        }else {  //for old versions
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}