package com.example.firefly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.firefly.database.BugDatabase
import com.example.firefly.databinding.FragmentWheatherBinding


class wheatherFragment : Fragment() {

    private lateinit var binding: FragmentWheatherBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_wheather, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BugDatabase.getInstance(application).bugDatabaseDao

        val args = wheatherFragmentArgs.fromBundle(requireArguments())

        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(dataSource)).get(MyViewModel::class.java)
        //get the weather information
        viewModel.retrieveWeather(args.city)

        //do data binding in the layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}