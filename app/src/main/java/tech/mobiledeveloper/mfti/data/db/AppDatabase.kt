package tech.mobiledeveloper.mfti.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.mobiledeveloper.mfti.data.catalog.RestaurantDao
import tech.mobiledeveloper.mfti.data.catalog.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}