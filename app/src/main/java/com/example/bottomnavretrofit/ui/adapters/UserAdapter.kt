package com.example.bottomnavretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavretrofit.R
import com.example.bottomnavretrofit.model.users.User

class UserAdapter(
    private val listUser: List<User>
): RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

}