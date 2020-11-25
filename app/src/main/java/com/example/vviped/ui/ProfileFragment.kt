package com.example.vviped.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.*
import com.example.vviped.model.login.Constant
import com.example.vviped.model.login.Logout
import com.example.vviped.model.login.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = PreferenceHelper(requireActivity())

        view.text_username.text = sharedPref.getString(Constant.PREF_USERNAME)
        view.text_fullname.text = sharedPref.getString(Constant.PREF_FULLNAME)
        view.user_email.text = sharedPref.getString(Constant.PREF_EMAIL)

        editprofile_btn.setOnClickListener{
            (context as MainActivity).changeFragment(EditProfileFragment())
        }

        btn_logout.setOnClickListener{
//            onAlertDialog(view)
            val intent = Intent(activity, Logout::class.java)
            startActivity(intent)
        }

    }


//    fun onAlertDialog(view: View) {
//        //Instantiate builder variable
//        val alertDialogBuilder = AlertDialog.Builder(view.context)
//
//        // set title
//        alertDialogBuilder.setTitle("Log out")
//
//        //set content area
//        alertDialogBuilder.setMessage("Are you sure?")
//
//        //set negative button
//        alertDialogBuilder.setPositiveButton(
//            "OK"
//        ) { dialog, id ->
//            val intent = Intent(activity, Landing::class.java)
//            startActivity(intent)
//            Toast.makeText(
//                activity,
//                "You've been logged out.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//        //set positive button
//        alertDialogBuilder.setNegativeButton(
//            "Cancel"
//        ) { dialog, id ->
//            // User cancelled the dialog
//        }
//
//        alertDialogBuilder.show()
//    }


}

