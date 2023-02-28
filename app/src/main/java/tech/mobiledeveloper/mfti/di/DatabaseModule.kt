package tech.mobiledeveloper.mfti.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.mobiledeveloper.mfti.data.catalog.RestaurantDao
import tech.mobiledeveloper.mfti.data.db.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantDao =
        appDatabase.restaurantDao()
}