package com.swivelngroup.news.view.fragment


import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.fragment.app.Fragment
import com.swivelngroup.news.R

/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment() {

    var progressDialog : Dialog? = null

    fun createProgressDialog(activity: Activity): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(0)
        )
        dialog.setContentView(R.layout.fragment_dialog_progress)
        dialog.setCancelable(false)
        dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)
        dialog.window!!.setDimAmount(0.0f)
        dialog.show()
        return dialog
    }

    fun showProgress(activity: Activity){
        progressDialog = createProgressDialog(activity)
    }

    fun hideProgress(){
        progressDialog!!.dismiss()
    }

}
