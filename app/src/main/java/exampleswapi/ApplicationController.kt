package exampleswapi

import android.app.Application
import android.content.Context
import dev.exampleswapi.BuildConfig

import timber.log.Timber

class ApplicationController : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationController? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
