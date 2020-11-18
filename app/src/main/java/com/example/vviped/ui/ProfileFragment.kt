package com.example.vviped.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.*
import com.example.vviped.model.SellingPostItem
import com.example.vviped.model.SellingPostsAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private lateinit var auth: FirebaseAuth


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

        auth = FirebaseAuth.getInstance()

        editprofile_btn.setOnClickListener{
            (context as MainActivity).changeFragment(EditProfileFragment())
        }


        btn_logout.setOnClickListener{
            onAlertDialog(view)
        }

    }

    fun onAlertDialog(view: View) {
        //Instantiate builder variable
        val alertDialogBuilder = AlertDialog.Builder(view.context)

        // set title
        alertDialogBuilder.setTitle("Log out")

        //set content area
        alertDialogBuilder.setMessage("Are you sure?")

        //set negative button
        alertDialogBuilder.setPositiveButton(
            "OK") { dialog, id ->
            // User clicked Update Now button
            auth.signOut()
            val intent = Intent(activity, Landing::class.java)
            startActivity(intent)
            Toast.makeText(
                activity,
                "You've been logged out.",
                Toast.LENGTH_SHORT
            ).show()
        }

        //set positive button
        alertDialogBuilder.setNegativeButton(
            "Cancel") { dialog, id ->
            // User cancelled the dialog
        }

        alertDialogBuilder.show()
    }


}
