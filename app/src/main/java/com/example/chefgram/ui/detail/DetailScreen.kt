package com.example.chefgram.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.chefgram.databinding.DetailScreenBinding
import com.example.chefgram.ui.main.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private var _binding: DetailScreenBinding? = null
    private val binding: DetailScreenBinding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailScreenBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        viewModel.recipeSelected.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.image).into(binding.detailImageIv)
            binding.detailTitleTv.text = it.title
            binding.detailDescriptionTv.text = it.description
        }

        binding.detailSaveFabButton.setOnClickListener {
            viewModel.saveMeal()
            viewModel.isSavedMessage.observe(viewLifecycleOwner) {message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}