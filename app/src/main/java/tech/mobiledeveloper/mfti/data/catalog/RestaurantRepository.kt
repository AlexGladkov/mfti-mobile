package tech.mobiledeveloper.mfti.data.catalog

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val cache = restaurantDao.getAll()
            if (cache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = emptyList(),
                        popular = cache.map { it.mapToRemoteRestaurant() },
                        commercial = null
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                restaurantDao.insertAll(*response.nearest.map {
                    it.mapToRestaurantEntity()
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
