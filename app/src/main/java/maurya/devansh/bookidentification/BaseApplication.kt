package maurya.devansh.bookidentification

import android.app.Application
import maurya.devansh.bookidentification.di.bookInfoRepositoryModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Devansh on 28/4/19.
 */

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(bookInfoRepositoryModule))
    }
}