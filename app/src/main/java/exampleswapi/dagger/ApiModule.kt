package exampleswapi.dagger

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.Module
import dagger.Provides
import exampleswapi.ApplicationController
import exampleswapi.network.StarWarsApi
import exampleswapi.network.StarWarsService
import exampleswapi.utilities.Constants
import okhttp3.Cache
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Modules
 */
@Module
internal class ApiModule {

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    @Provides
    fun provideStarWarsApi(): StarWarsApi {
        val cacheSize = (10 * 1024 * 1024).toLong()

        val myCache = Cache(ApplicationController.applicationContext().cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(ApplicationController.applicationContext())!!)
                        request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                    else
                        request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(Constants.SWAPI_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(StarWarsApi::class.java)
    }

    @Provides
    fun provideStarWarsService(): StarWarsService {
        return StarWarsService()
    }

}
