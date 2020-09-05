package com.example.desafioradix

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.desafioradix.di.modules
import com.example.desafioradix.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    val myModule = listOf(modules, viewModelModule)

    companion object{
        lateinit var instance : MyApplication
        var temInternet: Boolean = true
    }

    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(myModule)
        }

        val connectivityManager = this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network?) {
                temInternet = false
                //record wi-fi disconnect event
            }
            override fun onUnavailable() {
                temInternet = false
            }
            override fun onLosing(network: Network?, maxMsToLive: Int) {
                temInternet = false
            }
            override fun onAvailable(network: Network?) {
                temInternet = true
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}