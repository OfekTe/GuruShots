package com.ofekte.gurushotstest.photos_feature.ui.photos_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ofekte.gurushotstest.R
import com.ofekte.gurushotstest.databinding.FragmentPhotosBinding
import com.ofekte.gurushotstest.photos_feature.domain.model.Photo
import com.ofekte.gurushotstest.photos_feature.ui.common.GridPaginationScrollListener
import com.ofekte.gurushotstest.photos_feature.ui.photos_list.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosListFragment : Fragment(), PhotosAdapter.PhotoOnClickListener {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotosListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val adapter = PhotosAdapter(this@PhotosListFragment)

        with(binding.recyclerView) {
            this.adapter = adapter
            addOnScrollListener(GridPaginationScrollListener(
                layoutManager = layoutManager as GridLayoutManager,
                isLoading = { viewModel.uiState.value.isLoading },
                onPaginateNewItems = { newPaginationOffset ->
                    viewModel.paginatePhotos(newPaginationOffset)
                }
            ))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.progressBar.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    when (it.state) {
                        PhotosListViewModel.UiState.State.LOADING -> Unit
                        PhotosListViewModel.UiState.State.SUCCESS -> adapter.submitList(it.photos)
                        PhotosListViewModel.UiState.State.ERROR -> Snackbar
                            .make(binding.root, getString(R.string.something_went_wrong), Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun photoOnClick(photo: Photo) {
        val action = PhotosListFragmentDirections.actionPhotosFragmentToPhotoDetails(photo.wideImageUrl)
        findNavController().navigate(action)
    }
}