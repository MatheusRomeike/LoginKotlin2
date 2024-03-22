package com.example.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil.setContentView
import com.example.login.R
import com.example.login.SecondActivity

class ThirtyActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordConfirmEditText: EditText
    private lateinit var tipoUsuarioSpinner: Spinner
    private lateinit var registerButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thirty_activity)

        sharedPreferences = getSharedPreferences("user_credentials", MODE_PRIVATE)

        usernameEditText = findViewById(R.id.usernameET)
        passwordEditText = findViewById(R.id.passwordET)
        passwordConfirmEditText = findViewById((R.id.passwordConfirmET))
        tipoUsuarioSpinner = findViewById(R.id.tipoUsuarioS)
        registerButton = findViewById(R.id.registerB)

        usernameEditText.addTextChangedListener { checkFields() }
        passwordEditText.addTextChangedListener { checkFields() }
        passwordConfirmEditText.addTextChangedListener { checkFields() }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordConfirm = passwordConfirmEditText.text.toString()
            val tipoUsuario = tipoUsuarioSpinner.selectedItem.toString()

            if (password == passwordConfirm) {
                if (isUsernameAvailable(username)) {
                    saveCredentials(username, password, tipoUsuario)
                    showDialog("Sucesso ao registrar.", "Sucesso ao registrar.")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    showDialog("Erro ao registrar.", "Usuário já registrado.")
                }
            } else {
                showDialog("Erro ao registrar." ,"As senhas não coincidem.")
            }
        }
    }

    private fun checkFields() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val passwordConfirm = passwordConfirmEditText.text.toString()

        registerButton.isEnabled = username.isNotBlank() && password.isNotBlank() && password == passwordConfirm
    }

    private fun isUsernameAvailable(username: String): Boolean {
        return !sharedPreferences.contains(username)
    }

    private fun saveCredentials(username: String, password: String, tipoUsuario: String) {
        val editor = sharedPreferences.edit()
        val userCredentials = mutableSetOf<String>()
        userCredentials.add(password)
        userCredentials.add(tipoUsuario)
        editor.putStringSet(username, userCredentials)
        editor.apply()
    }

    private fun showDialog(title: String, message: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}
