package com.example.memenator.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memenator.databinding.SavedItemsViewholderBinding
import com.example.memenator.databinding.ViewHolderLayoutBinding
import com.example.memenator.models.DataGlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SavedRecyclerAdapter(): RecyclerView.Adapter<SavedRecyclerAdapter.viewHolder>() {

    private var database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef : DatabaseReference = database.getReference("UserSaved")
    private var userId = FirebaseAuth.getInstance().currentUser?.uid


    private var savedItems = mutableListOf<String>()


    inner class viewHolder(private val binding : SavedItemsViewholderBinding) : RecyclerView.ViewHolder(binding.root){

        private lateinit var url : String
        fun bind(){
            url = savedItems[adapterPosition]
            var kay = url.split("=")[0]

            binding.images = DataGlideModel(url.drop(15))

            binding.deleteBtn.setOnClickListener {
                userId?.let { it1 ->
                    FirebaseDatabase.getInstance().getReference("UserSaved").child(
                        it1
                    ).child(kay.drop(1)).removeValue()
                }

                notifyDataSetChanged()


                d("eesaaa",url.split("=")[0].drop(1))
//                userId?.let { it1 -> myRef.child(it1).removeValue() }

            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = SavedItemsViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind()
    }


    override fun getItemCount()=savedItems.size

    fun getitems(items : MutableList<String>){
        savedItems.clear()
        savedItems.addAll(items)
        notifyDataSetChanged()

    }


}