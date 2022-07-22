package com.example.bottomnavretrofit.coreUi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavretrofit.App
import com.example.bottomnavretrofit.coreUi.adapters.MovieAdapter
import com.example.bottomnavretrofit.data.movie.MovieApi
import com.example.bottomnavretrofit.databinding.ContentFragmentBinding
import com.example.bottomnavretrofit.model.movie.Movie
import com.example.bottomnavretrofit.model.movie.MovieListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ContentFragment : Fragment() {

    private lateinit var binding: ContentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContentFragmentBinding.inflate(layoutInflater, container, false)
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
        Log.d("movie", response.size.toString())
    }
}