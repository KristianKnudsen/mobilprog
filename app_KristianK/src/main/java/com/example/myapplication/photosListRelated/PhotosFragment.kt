package com.example.myapplication.photosListRelated

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.albumsRelated.Album
import com.example.myapplication.databinding.PhotosFragmentBinding

class PhotosFragment : Fragment() {

    private lateinit var binding: PhotosFragmentBinding
    private lateinit var selectedAlbum: Album
    private lateinit var photosViewModel: PhotosViewModel

    private var photos = listOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedAlbum = PhotosFragmentArgs.fromBundle(requireArguments()).selectedAlbum

        val photosVMFactory = PhotosViewModelFactory(selectedAlbum)
        photosViewModel = ViewModelProvider(this, photosVMFactory).get(PhotosViewModel::class.java)

        binding = PhotosFragmentBinding.inflate(layoutInflater).apply {
            viewModel = photosViewModel
            lifecycleOwner = this@PhotosFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val albumsRV: RecyclerView = binding.photosRecyclerView
        albumsRV.layoutManager = LinearLayoutManager(activity)

        photosViewModel.photosLoaded.observe(binding.lifecycleOwner!!){
            if(it != null && it == true){
                photos = photosViewModel.photos.value!!
                albumsRV.adapter = PhotosListAdapter(photos, this::navigateWithIndex)
            }
        }
        (activity as MainActivity).supportActionBar!!.title = selectedAlbum.title

        return binding.root
    }

    private fun navigateWithIndex(index: Int) {
        val action = PhotosFragmentDirections.actionPhotosFragmentToPhotoFragment(photos[index])
        findNavController().navigate(action)
    }
}