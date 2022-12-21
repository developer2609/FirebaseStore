package com.developer.firebasestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.developer.firebasestore.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseStorege:FirebaseStorage
    private lateinit var reference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseStorege= FirebaseStorage.getInstance()
        reference=firebaseStorege.getReference("myPhotos")
        binding.imageAction.setOnClickListener {
            binding.imageAction.start()
            getImageContent.launch("video/*")
        }
    }

    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        binding.imageAction.setVideoURI(uri)


        reference.child("rasmlar").putFile(uri!!)
            .addOnSuccessListener {
                Toast.makeText(this, "saqlandi", Toast.LENGTH_SHORT).show()
            }


    }
}