package com.ofekte.gurushotstest.photos_feature.ui.photos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ofekte.gurushotstest.common.GET_PHOTOS_MAX_LIMIT
import com.ofekte.gurushotstest.common.MEMBER_ID
import com.ofekte.gurushotstest.common.Resource
import com.ofekte.gurushotstest.photos_feature.domain.model.Photo
import com.ofekte.gurushotstest.photos_feature.domain.usecases.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    private var offset = 0
    private var hasMorePhotosToPaginate = true

    init {
        getPhotos()
    }

    private fun getPhotos() = viewModelScope.launch {
        getPhotosUseCase(
            memberId = MEMBER_ID,
            start = offset,
            limit = GET_PHOTOS_MAX_LIMIT
        ).collect { resource ->
            val (prevAndNewData, state) = when (resource) {
                is Resource.Success -> {
                    //Got less than our requested limit size, there is no more data to load
                    if (resource.data.size < GET_PHOTOS_MAX_LIMIT) hasMorePhotosToPaginate = false

                    (uiState.value.photos + resource.data) to UiState.State.SUCCESS
                }
                is Resource.Loading -> null to UiState.State.LOADING
                is Resource.Error -> null to UiState.State.ERROR
            }
            _uiState.apply {
                value = value.copy(
                    photos = prevAndNewData ?: uiState.value.photos,
                    state = state
                )
            }
        }
    }

    fun paginatePhotos(newScrollPosition: Int) {
        offset = newScrollPosition
        if (!uiState.value.isLoading && hasMorePhotosToPaginate) {
            getPhotos()
        }
    }

    data class UiState(
        val photos: List<Photo> = emptyList(),
        val state: State = State.LOADING
    ) {
        enum class State { LOADING, SUCCESS, ERROR }

        val isLoading get() = state == State.LOADING
    }
}