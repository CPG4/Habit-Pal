package com.group4.habitpal.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.card.MaterialCardView
import com.group4.habitpal.R
import com.group4.habitpal.activities.MainActivity
import com.group4.habitpal.custom_views.CustomAppButton
import com.parse.ParseUser
import java.io.File

val CAMERA_REQUEST_CODE = 42
class MyProfileFragment : Fragment() {

    val CAMERA_REQUEST_CODE = 42
    val photoFileName = "photo.jpg"
    var photoFile: File? = null

    lateinit var avatar: ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_myprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity

        mainActivity.findViewById<TextView>(R.id.text_name).text = ParseUser.getCurrentUser().getUsername()

        val btnEditProfile = mainActivity.findViewById<CustomAppButton>(R.id.btn_editprofile)
        val btnChangePassword = mainActivity.findViewById<CustomAppButton>(R.id.btn_changepassword)
        val btnChangePicture = mainActivity.findViewById<MaterialCardView>(R.id.profile_pic_container)
        avatar = mainActivity.findViewById(R.id.profile_pic)


        mainActivity.showBackButton(ProfileFragment())

        btnEditProfile.setAction {
            mainActivity.replaceFragment(EditProfileFragment())
        }

        btnChangePassword.setAction {
            mainActivity.replaceFragment(ChangePasswordFragment())
        }

        btnChangePicture.setOnClickListener {

            onLaunchCamera()


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode == AppCompatActivity.RESULT_OK) {
                val takenImage = BitmapFactory.decodeFile(photoFile!!.absolutePath)
                avatar.setImageBitmap(takenImage)
            } else {
                Toast.makeText(requireContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun onLaunchCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        photoFile = getPhotoFileUri(photoFileName)

        if(photoFile != null) {
            val fileProvider: Uri = FileProvider.getUriForFile(requireContext(), "com.codepath.fileprovider",photoFile!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            if(intent.resolveActivity(requireContext().packageManager) != null) {
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }
        }
    }

    fun getPhotoFileUri(fileName: String): File {
        val mediaStorageDir = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            MainActivity.TAG
        )

        if(!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(MainActivity.TAG, "failed to create directory")
        }

        return File(mediaStorageDir.path + File.separator + fileName)
    }






}