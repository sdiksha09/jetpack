package com.example.shipra.jetpack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.shipra.jetpack.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import java.security.Provider


class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    private var viewModel: MainViewModel?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init the ViewModel

        viewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)

        updateUi()



        btn_add2.setOnClickListener {

            viewModel!!.counter += 1
            updateUi()


        }
    }

    private fun updateUi() {

        text_count.text=viewModel!!.counter.toString()

    }

}
