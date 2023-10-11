package com.example.chefgram.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.example.chefgram.R

class FilterAdapter(val contextNuevo: Context, private val listFilterOptions: ArrayList<FilterItem>) :
    ArrayAdapter<FilterItem>(contextNuevo, 0, listFilterOptions) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val filterItem = getItem(position);
        var newView: View? = null

        if (convertView == null) {
            newView = LayoutInflater.from(contextNuevo).inflate(R.layout.filter_item_list, parent, false)
        }


        val ingredient: CheckBox = newView!!.findViewById(R.id.filter_option_box);

        if (filterItem != null) {
            ingredient.text = filterItem.name
        }

        return newView;
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent)
    }
}