package tech.mobiledeveloper.mfti.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MainViewState(
    val nearestRestaurant: List<Restaurant> = emptyList(),
    val popularRestaurant: List<Restaurant> = emptyList(),
    val searchQuery: String = ""
)

class MainViewModel : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData(MainViewState())
    val viewState: LiveData<MainViewState> = _viewState

    init {
        val nearestRestaurant = listOf(
            Restaurant(
                name = "KFC",
                deliveryTime = "12 min",
                logo = "https://play-lh.googleusercontent.com/s7slUGiae9bq7XuYur0GWd_qDp_UXgo_5BIpzOT_BvKGg17TYG5QDr3ckqPcpq20jVU"
            ),
            Restaurant(
                name = "Burger King",
                deliveryTime = "10 min",
                logo = "https://img.wongnai.com/p/1920x0/2019/02/26/cebf62f2742e4756a42b6b505070d6fe.jpg"
            )
        )

        val popularRestaurant = listOf(
            Restaurant(
                name = "Stars Coffee",
                deliveryTime = "25 min",
                logo = "https://riamo.ru/files/image/23/76/25/-gallery!00r2.jpg"
            ),
            Restaurant(
                name = "Black Star Burger",
                deliveryTime = "5 min",
                logo = "https://www.rusfranch.ru/u/www/images/catalog/logo_image_nwna9cyklpaicbxlpzm2c7pvqbck0mgfa.png"
            )
        )

        _viewState.postValue(
            _viewState.value?.copy(
                nearestRestaurant = nearestRestaurant,
                popularRestaurant = popularRestaurant
            )
        )
    }

    fun searchQuery(query: String) {
        _viewState.postValue(_viewState.value?.copy(searchQuery = query))
    }
}