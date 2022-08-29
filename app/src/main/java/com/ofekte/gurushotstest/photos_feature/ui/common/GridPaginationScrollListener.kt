package com.ofekte.gurushotstest.photos_feature.ui.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridPaginationScrollListener(
    private val prefetchDistance: Int = 5,
    private val layoutManager: GridLayoutManager,
    private val isLoading: () -> Boolean,
    private val onPaginateNewItems: (newPaginationOffset: Int) -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
        val withinPrefetchDistance = (lastItemPosition + prefetchDistance) >= layoutManager.itemCount
        if (!isLoading() && withinPrefetchDistance){
            onPaginateNewItems(lastItemPosition + prefetchDistance)
        }
    }
}