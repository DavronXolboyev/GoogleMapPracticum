package uz.doxmobile.google_practicum.di

import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.doxmobile.google_practicum.db.AppDatabase
import uz.doxmobile.google_practicum.db.LocationDao
import uz.doxmobile.google_practicum.service.DefaultLocationClient
import uz.doxmobile.google_practicum.service.LocationClient
import javax.inject.Singleton

/**
 *   Davron
 *   10.08.2023  |  четверг  |  9:22
 */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao = appDatabase.locationDao

    @Provides
    fun provideLocationClient(@ApplicationContext context: Context): LocationClient =
        DefaultLocationClient(context, LocationServices.getFusedLocationProviderClient(context))
}