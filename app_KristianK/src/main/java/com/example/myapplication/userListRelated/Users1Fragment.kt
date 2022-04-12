package com.example.myapplication.userListRelated

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.UserApplication
import com.example.myapplication.databinding.FragmentUsers1Binding
import com.example.myapplication.user.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView


class Users1Fragment : Fragment() {

    private var users = listOf<User>()
    private lateinit var binding: FragmentUsers1Binding
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(this.activity).application as UserApplication
        val repository = application.userRepository
        val VMF = UsersViewModelFactory(repository)
        usersViewModel = ViewModelProvider(this, VMF).get(UsersViewModel::class.java)


        binding = FragmentUsers1Binding.inflate(layoutInflater).apply {
            viewModel = usersViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val usersRV: RecyclerView = binding.usersRecyclerView
        usersRV.layoutManager = LinearLayoutManager(activity)

        usersViewModel.usersLoaded.observe(viewLifecycleOwner){
            if (it != null && it == true){
                users = usersViewModel.users.value!!
                usersRV.adapter = UserListAdapter(users, this::navigateWithIndex)
            }
        }
        (activity as MainActivity).supportActionBar!!.title = "Users"


        setHasOptionsMenu(true)

        return binding.root
    }

    private fun navigateWithIndex(index: Int){
        findNavController().navigate(Users1FragmentDirections.actionUsers1FragmentToAlbums2Fragment(users[index]))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.users_items_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                findNavController().navigate(Users1FragmentDirections.actionUsers1FragmentToSettings())
                true
            }
            R.id.about -> {
                MaterialAlertDialogBuilder(activity as MainActivity)
                    .setTitle("Om appen")
                    .setMessage("Laget av:\n" +
                            "Kristian Knudsen.")
                    .setCancelable(true)
                    .setNegativeButton("Lukk") { dialog, which ->
                        // closes dialog box
                    }
                    .show()
                true
            }
            R.id.quit -> {
                (activity as MainActivity).finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}