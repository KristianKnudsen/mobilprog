package com.example.myapplication.albumsRelated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAlbums2Binding
import com.example.myapplication.user.User
import com.example.myapplication.userListRelated.UserListAdapter
import com.example.myapplication.userListRelated.UsersViewModel

class Albums2Fragment : Fragment() {

    //Kan ikke initialisere her må gjøre det i lifecyclen til fragmentet.
    private lateinit var selectedUser: User
    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var binding: FragmentAlbums2Binding

    private var albums = listOf<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedUser = Albums2FragmentArgs.fromBundle(requireArguments()).selectedUser

        val albumsVMFactory = AlbumsViewModelFactory(selectedUser)
        albumsViewModel = ViewModelProvider(this, albumsVMFactory).get(AlbumsViewModel::class.java)

        binding = FragmentAlbums2Binding.inflate(layoutInflater).apply {
            viewModel = albumsViewModel
            lifecycleOwner = this@Albums2Fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val usersRV: RecyclerView = binding.usersRecyclerView
        usersRV.layoutManager = LinearLayoutManager(activity)

        albumsViewModel.albumsLoaded.observe(binding.lifecycleOwner!!){
            if (it != null && it == true){
                albums = albumsViewModel.albums.value!!
                usersRV.adapter = AlbumListAdapter(albums, this::navigateWithIndex)
            }
        }
        (activity as MainActivity).supportActionBar!!.title = "${selectedUser.username}'s albums"

        return binding.root
    }
    private fun navigateWithIndex(index: Int){
        findNavController().navigate(Albums2FragmentDirections.actionAlbums2FragmentToPhotosFragment(albums[index]))
    }
}