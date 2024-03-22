package com.example.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil.setContentView
import com.example.login.R

class SecondActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var redirectRegisterButton: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        sharedPreferences = getSharedPreferences("user_credentials", MODE_PRIVATE)


        usernameEditText = findViewById(R.id.usernameET)
        passwordEditText = findViewById(R.id.passwordET)
        saveButton = findViewById(R.id.saveB)
        redirectRegisterButton = findViewById(R.id.redirectRegisterB)

        saveButton.isEnabled = false
        usernameEditText.addTextChangedListener { checkCamposPreenchidos() }
        passwordEditText.addTextChangedListener { checkCamposPreenchidos() }

        saveButton.setOnClickListener {
            val novoUsername = usernameEditText.text.toString()
            val novaPassword = passwordEditText.text.toString()

            salvarCredenciais(novoUsername, novaPassword)
        }

        redirectRegisterButton.setOnClickListener {
            val intent = Intent(this, ThirtyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkCamposPreenchidos() {
        val novoUsername = usernameEditText.text.toString()
        val novaPassword = passwordEditText.text.toString()

        saveButton.isEnabled = novoUsername.isNotBlank() && novaPassword.isNotBlank()
    }

    private fun salvarCredenciais(novoUsername: String, novaPassword: String) {
        Toast.makeText(this, "Novas credenciais salvas com sucesso!", Toast.LENGTH_SHORT).show()
    }
}
