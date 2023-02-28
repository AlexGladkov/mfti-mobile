package tech.mobiledeveloper.mfti.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.mobiledeveloper.mfti.data.RestaurantRepository
import javax.inject.Inject

data class MainViewState(
    val nearestRestaurant: List<Restaurant> = emptyList(),
    val popularRestaurant: List<Restaurant> = emptyList(),
    val searchQuery: String = ""
)

@HiltViewModel
class MainViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData(MainViewState())
    val viewState: LiveData<MainViewState> = _viewState

    init {
        fetchRestaurants()
    }

    fun searchQuery(query: String) {
        _viewState.postValue(_viewState.value?.copy(searchQuery = query))
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            val response = restaurantRepository.fetchCatalog()

            _viewState.postValue(
                _viewState.value?.copy(
                    nearestRestaurant = response.nearest.map { it.mapToRestaurant() },
                    popularRestaurant = response.popular.map { it.mapToRestaurant() }
                )
            )
        }
    }
}