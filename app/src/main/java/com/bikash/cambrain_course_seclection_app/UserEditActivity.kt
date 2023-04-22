package com.bikash.cambrain_course_seclection_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.bikash.cambrain_course_seclection_app.databinding.ActivityUserEditBinding
import com.google.gson.Gson

class UserEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserEditBinding
     var user: User? = null
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        get the current user info
        preferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val userJson = intent.getStringExtra("user")


//        convert the json to User object
        val gson = Gson()
        val user = gson.fromJson(userJson, User::class.java)

//        get tht data
        binding.editName.setText(user?.name)
        binding.editEmail.setText(user?.email)
        binding.editProgram.setText(user?.program)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        binding.editUser.setOnClickListener{
//            retrieve data from text fields
            val name = binding.editName.text.toString()
            val email = binding.editEmail.text.toString()
            val program = binding.editProgram.text.toString()

//            update the corresponding fields of user object
            user?.name = name
            user?.email = email
            user?.program = program

//             create intent
            val intent = Intent()
            intent.putExtra("updated_user", gson.toJson(user))
            setResult(Activity.RESULT_OK, intent)

//            show toast message display message
            Toast.makeText(this,"User info update.", Toast.LENGTH_SHORT).show()
        finish()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}