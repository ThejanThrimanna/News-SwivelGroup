package com.swivelngroup.news.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.swivelngroup.news.R
import com.swivelngroup.news.utils.SharedPref
import com.swivelngroup.news.view.activity.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private var mFragmentView: View? = null
    private var mCategory: Spinner? = null
    private var mLaungage: Spinner? = null
    private var mCountry: Spinner? = null
    private var mSave: Button? = null

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mFragmentView != null) {
            return mFragmentView
        }
        val view =
            inflater.inflate(R.layout.fragment_profile, container, false)
        mFragmentView = view
        mCategory = view.findViewById(R.id.spinnerCategory)
        mLaungage = view.findViewById(R.id.spinnerLanguage)
        mCountry = view.findViewById(R.id.spinnerCountry)
        mSave = view.findViewById(R.id.btnSave)
        init()
        return view
    }

    private fun init() {
        initView()
        initAction()
    }

    private fun initAction() {
        mSave!!.setOnClickListener {
            SharedPref.saveInteger(SharedPref.SELECTED_CATEGORY_INDEX,mCategory!!.selectedItemPosition)
            SharedPref.saveInteger(SharedPref.SELECTED_LANGUAGE_INDEX,mLaungage!!.selectedItemPosition)
            SharedPref.saveInteger(SharedPref.SELECTED_COUNTRY_INDEX,mCountry!!.selectedItemPosition)

            SharedPref.saveString(SharedPref.SELECTED_CATEGORY_VALUE,  mCategory!!.selectedItem.toString())
            SharedPref.saveString(SharedPref.SELECTED_LANGUAGE_INDEX,mLaungage!!.selectedItem.toString())
            SharedPref.saveString(SharedPref.SELECTED_COUNTRY_INDEX,mCountry!!.selectedItem.toString())

            Toast.makeText(activity as MainActivity, getString(R.string.data_saved), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        val categoryAdapter = ArrayAdapter<String>(
            activity as MainActivity,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.categories)
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mCategory!!.adapter = categoryAdapter
        mCategory!!.setSelection(SharedPref.getInteger(SharedPref.SELECTED_CATEGORY_INDEX,0))

        val languageAdapter = ArrayAdapter<String>(
            activity as MainActivity,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.language)
        )
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mLaungage!!.adapter = languageAdapter
        mLaungage!!.setSelection(SharedPref.getInteger(SharedPref.SELECTED_LANGUAGE_INDEX,0))

         val countryAdapter = ArrayAdapter<String>(
            activity as MainActivity,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.country)
        )
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mCountry!!.adapter = countryAdapter
        mCountry!!.setSelection(SharedPref.getInteger(SharedPref.SELECTED_COUNTRY_INDEX,0))

    }


}
