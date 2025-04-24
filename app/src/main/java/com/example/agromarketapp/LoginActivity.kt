package com.example.agromarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = findViewById<EditText>(R.id.editTextText1)
        val password = findViewById<EditText>(R.id.editTextTextPassword1)

        val btnAtras = findViewById<Button>(R.id.button1)
        val btnIngresar = findViewById<Button>(R.id.button2)
        val btnOlvidarContrasena = findViewById<Button>(R.id.button3)
        val btnNoTieneUsuario = findViewById<Button>(R.id.button4)

        btnIngresar.setOnClickListener {
            val usuarioIngresado = username.text.toString().trim()
            val contrasenaIngresada = password.text.toString()

            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("datos_usuario", MODE_PRIVATE)
            val usuarioGuardado = prefs.getString("usuario", null)
            val contrasenaGuardada = prefs.getString("contrasena", null)

            if (usuarioGuardado == null || contrasenaGuardada == null) {
                Toast.makeText(this, "No hay usuarios registrados. Por favor crea una cuenta.", Toast.LENGTH_SHORT).show()
            } else if (usuarioIngresado == usuarioGuardado && contrasenaIngresada == contrasenaGuardada) {
                Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnOlvidarContrasena.setOnClickListener {
            val intent = Intent(this, EmailActivity::class.java)
            startActivity(intent)
        }

        btnNoTieneUsuario.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnAtras.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}