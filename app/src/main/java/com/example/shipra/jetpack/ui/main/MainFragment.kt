package com.example.shipra.jetpack.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.shipra.jetpack.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            //set clickListener
            view.findViewById<Button>(R.id.firstbtn)?.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_firstFragment)
            }

            view.findViewById<Button>(R.id.frag_btn2)?.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_secondFragment)
            }
        }


}
