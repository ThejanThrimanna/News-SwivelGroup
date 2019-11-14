package com.swivelngroup.news.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.swivelngroup.news.R

/**
 * A simple [Fragment] subclass.
 */
class CustomFragment : Fragment() {

    companion object {
        fun newInstance(): CustomFragment {
            return CustomFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom, container, false)
    }


}
