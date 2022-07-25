package com.example.bottomnavretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bottomnavretrofit.App
import com.example.bottomnavretrofit.data.movie.UserApi
import com.example.bottomnavretrofit.databinding.RecyclerFragmentBinding
import com.example.bottomnavretrofit.model.users.User
import com.example.bottomnavretrofit.ui.adapters.UserAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserFragment : Fragment() {

    private lateinit var binding: RecyclerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecyclerFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUserList((activity?.application as App).userApi)
    }

    private fun fetchUserList(userApi: UserApi?) {

        val compositeDisposable = CompositeDisposable()
        userApi?.let {
            userApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse(it)
                }, {
                    onFailure(it)
                }).let(compositeDisposable::add)
        }
    }

    private fun onResponse(listUser: List<User>) {
        binding.recycler.adapter = UserAdapter(listUser).apply {
            notifyDataSetChanged()
        }
    }

    private fun onFailure(it: Throwable?){
        Toast.makeText(requireContext(), it?.toString(), Toast.LENGTH_LONG).show()
    }
}