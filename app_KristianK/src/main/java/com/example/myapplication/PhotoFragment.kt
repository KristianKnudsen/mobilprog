package com.example.myapplication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.myapplication.albumsRelated.AlbumsViewModelFactory
import com.example.myapplication.databinding.PhotoFragmentBinding
import com.example.myapplication.photosListRelated.Photo

class PhotoFragment : Fragment() {

    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var binding: PhotoFragmentBinding
    private lateinit var selectedPhoto: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedPhoto = PhotoFragmentArgs.fromBundle(requireArguments()).selectedPhoto

        val photoVMFactory = PhotoViewModelFactory(selectedPhoto)
        photoViewModel = ViewModelProvider(this, photoVMFactory).get(PhotoViewModel::class.java)

        binding = PhotoFragmentBinding.inflate(layoutInflater).apply {
            viewModel = photoViewModel
            lifecycleOwner = this@PhotoFragment
        }

        val url = GlideUrl(selectedPhoto.url, LazyHeaders.Builder()
            .addHeader("User-Agent", "android")
            .build())
        Glide.with(requireActivity())
            .load(url)
            .into(binding.fullImg)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.title.text = selectedPhoto.title
        
        binding.titleSubmit.setOnClickListener {
            val newTitle = binding.titleEditText.text
            if (newTitle.isNotEmpty()) {
                photoViewModel.setTitle(newTitle.toString())
            }
        }

        binding.deleteBtn.setOnClickListener {
            photoViewModel.delete()
        }

        return binding.root
    }

}