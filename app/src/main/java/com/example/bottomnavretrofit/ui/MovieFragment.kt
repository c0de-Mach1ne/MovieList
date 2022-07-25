package com.example.bottomnavretrofit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bottomnavretrofit.App
import com.example.bottomnavretrofit.ui.adapters.MovieAdapter
import com.example.bottomnavretrofit.data.movie.MovieApi
import com.example.bottomnavretrofit.databinding.RecyclerFragmentBinding
import com.example.bottomnavretrofit.model.movie.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieFragment : Fragment() {

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

        fetchMovieList((activity?.application as? App)?.movieApi)
    }

    private fun fetchMovieList(movieApi: MovieApi?) {

        val compositeDisposable = CompositeDisposable()
        movieApi?.let {
            movieApi.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onResponse(it)
                }, {
                    onFailure(it)
                }).let(compositeDisposable::add)
        }
    }

    private fun onFailure(t: Throwable?) {
        Toast.makeText(binding.recycler.context, t?.message, Toast.LENGTH_LONG).show()
    }

    private fun onResponse(response: List<Movie>) {
        binding.recycler.adapter = MovieAdapter(response).apply {
            notifyDataSetChanged()
        }
    }
}