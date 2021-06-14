package com.example.memenator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memenator.databinding.ViewHolderLayoutBinding
import com.example.memenator.models.DataGlideModel
import com.example.memenator.models.ResponseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.viewHolder>() {

    private  var items = mutableListOf<ResponseModel.Memes>()



    inner class viewHolder(private val binding : ViewHolderLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var model: ResponseModel.Memes
        fun bind(){
            model = items[adapterPosition]
            binding.items = model
            binding.images = DataGlideModel(model.url.toString())



            binding.saveBtn.setOnClickListener {
                var userid = FirebaseAuth.getInstance().currentUser?.uid
                var  id : Long = System.currentTimeMillis()
                    if (userid != null) {
                        FirebaseDatabase.getInstance().getReference("UserSaved").child(userid).child(
                            id.toString()
                        ).setValue(model.url)

                }
            }
        }




    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bind()

    }

    override fun getItemCount()= items.size

    fun setData(items : MutableList<ResponseModel.Memes>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = ViewHolderLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(itemView)

    }


}