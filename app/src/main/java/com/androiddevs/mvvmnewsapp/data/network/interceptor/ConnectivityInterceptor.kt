package com.androiddevs.mvvmnewsapp.data.network.interceptor

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class NoInternetException : IOException("No internet connection")

interface NetworkMonitor {
    fun isConnected(): Boolean
}

@Singleton
class LiveNetworkMonitor @Inject constructor(private val connectivityManager: ConnectivityManager) :
    NetworkMonitor {

    override fun isConnected(): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return when {
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
            else -> false
        }
    }
}

@Singleton
class ConnectivityInterceptor @Inject constructor(private val monitor: LiveNetworkMonitor) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (monitor.isConnected()) {
            chain.proceed(request)
        } else {
            throw NoInternetException()
        }
    }
}
