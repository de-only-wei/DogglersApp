/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout.GRID
import com.example.dogglers.data.DataSource
import com.example.dogglers.data.DataSource.dogs

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private var layout: Int

): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    //initialized the data using the List found in data/DataSource
    private val dogList = DataSource.dogs
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        //initialized all of the list item UI components
        val textViewName: TextView? = view!!.findViewById(R.id.dog_name)
        val textViewAge: TextView? = view!!.findViewById(R.id.dog_age)
        val textViewHobbies: TextView? = view!!.findViewById(R.id.dog_hobbies)
        val imageView: ImageView? = view!!.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        //conditional statement to determine the layout type
        val adapterLayout = when (layout){
            //if GRID inflate grid_list_item, else vertical_horizontal_list_item
            GRID->LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item,parent, false)
            else->LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item,parent, false)
        }
        //returns the updated inflated layout
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {return dogList.size}

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        //get the data at the current position
        val dogData = dogList[position]

        //set the image resource for the current dog
        holder.imageView?.setImageResource(dogData.imageResourceId)

        //set the text for the current dog's name
        holder.textViewName?.text = dogData.name

        //set the text for the current dog's age
        holder.textViewAge?.text = dogData.age
        val resources = context?.resources
        //set the text for the current dog's hobbies by passing the hobbies to the

        //holder.textViewAge.text = resources?.getString(R.string.dog_age, dogData.age)
        holder.textViewHobbies?.text = resources?.getString(R.string.dog_hobbies, dogData.hobbies)
    }
}
