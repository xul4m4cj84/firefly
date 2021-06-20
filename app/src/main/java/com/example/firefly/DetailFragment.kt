package com.example.firefly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.firefly.database.BugDatabase
import com.example.firefly.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = BugDatabase.getInstance(application).bugDatabaseDao

        viewModel = ViewModelProvider(requireActivity(), MyViewModelFactory(dataSource)).get(MyViewModel::class.java)

        val args = DetailFragmentArgs.fromBundle(requireArguments())
        viewModel.getBug(args.rawId)
        //when getBug() this function will get database and value store into live data selecteBug

        viewModel.selectedBug.observe(viewLifecycleOwner, {
            binding.bug = it
        })

        binding.mapbutton.setOnClickListener{
            val passedBug = viewModel.selectedBug.value!!
            it.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapsFragment(passedBug.name,passedBug.address))
        }

        binding.wheatherButton.setOnClickListener{
            val passedBug = viewModel.selectedBug.value!!
            it.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToWheatherFragment(passedBug.city))
        }

        return binding.root
    }
}