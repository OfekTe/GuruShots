package com.ofekte.gurushotstest.photos_feature.ui.photo_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.request.ImageRequest
import com.ofekte.gurushotstest.R
import com.ofekte.gurushotstest.databinding.FragmentPhotoDetailsBinding

class PhotoDetailsFragment : Fragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: PhotoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoIv.load(args.url) {
            listener(
                onStart = { binding.progressBar.visibility = View.VISIBLE },
                onSuccess = { _, _ -> binding.progressBar.visibility = View.GONE }
            )
        }
    }
}