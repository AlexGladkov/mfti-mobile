package tech.mobiledeveloper.mfti.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.mobiledeveloper.mfti.data.catalog.RestaurantRepository
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

    fun searchQuery(query: String) {
        _viewState.postValue(_viewState.value?.copy(searchQuery = query))
    }

    fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.fetchCatalog()
                .collect { response ->
                    Log.e("TAG", "Response $response")
                    _viewState.postValue(
                        _viewState.value?.copy(
                            nearestRestaurant = response.nearest.map { it.mapToRestaurant() },
                            popularRestaurant = response.popular.map { it.mapToRestaurant() }
                        )
                    )
                }
        }
    }
}