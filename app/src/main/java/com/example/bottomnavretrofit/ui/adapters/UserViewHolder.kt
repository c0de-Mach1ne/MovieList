package com.example.bottomnavretrofit.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavretrofit.databinding.UserItemBinding
import com.example.bottomnavretrofit.model.users.User

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = UserItemBinding.bind(itemView)

    fun onBind(user: User){
        binding.tvUserNickname.text = user.username
        binding.tvUserFullName.text = user.name
    }

}