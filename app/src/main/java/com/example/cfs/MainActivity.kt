package com.example.cfs

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.DocumentReference
import com.google.android.gms.tasks.OnSuccessListener





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val name = findViewById<EditText>(R.id.etext)
        val button = findViewById<Button>(R.id.button)
        val fshow = findViewById<TextView>(R.id.textView2)
        val fet = findViewById<Button>(R.id.button3)

        fet.setOnClickListener {

            mFirestore.collection("Users").get().addOnSuccessListener {
                Toast.makeText(this, "data fetched", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "data not fetched", Toast.LENGTH_LONG).show()
            }
            fshow.setText(name.text.toString())
        }


        button.setOnClickListener() {
            val usermap = HashMap<String, Any>()
            usermap.put("Name", name.text.toString())


            mFirestore.collection("Users").add(usermap).addOnSuccessListener() {
                Toast.makeText(this, "Data Stored", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener() {
                Toast.makeText(this, "Data Not Stored", Toast.LENGTH_SHORT).show()
            }
        }

    }
}