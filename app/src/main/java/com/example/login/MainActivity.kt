package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil.setContentView
import com.example.login.R
import com.example.login.SecondActivity

class MainActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        usernameEditText = findViewById(R.id.usernameET)
        passwordEditText = findViewById(R.id.passwordET)
        loginButton = findViewById(R.id.loginB)

        usernameEditText.addTextChangedListener { checkCamposPreenchidos() }
        passwordEditText.addTextChangedListener { checkCamposPreenchidos() }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username == "user" && password == "1234") {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                showErrorDialog()
            }
        }
    }

    private fun checkCamposPreenchidos() {
        val novoUsername = usernameEditText.text.toString()
        val novaPassword = passwordEditText.text.toString()

        loginButton.isEnabled = novoUsername.isNotBlank() && novaPassword.isNotBlank()
    }

    private fun showErrorDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Erro de login")
        builder.setMessage("As credenciais fornecidas são inválidas.")
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}
