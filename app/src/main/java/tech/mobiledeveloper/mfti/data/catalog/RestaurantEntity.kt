package tech.mobiledeveloper.mfti.data.catalog

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String
)

fun RemoteRestaurant.mapToRestaurantEntity(): RestaurantEntity =
    RestaurantEntity(id = id, name = name, logo = image, time = deliveryTime)

fun RestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    fun getAll(): List<RestaurantEntity>

    @Insert
    fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    fun delete(restaurantEntity: RestaurantEntity)
}
