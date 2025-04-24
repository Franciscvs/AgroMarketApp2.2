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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val newUsername = findViewById<EditText>(R.id.editTextText1)
        val names = findViewById<EditText>(R.id.editTextText2)
        val lastNames = findViewById<EditText>(R.id.editTextText3)
        val email = findViewById<EditText>(R.id.editTextText4)
        val password = findViewById<EditText>(R.id.editTextTextPassword1)
        val confirmPassword = findViewById<EditText>(R.id.editTextTextPassword2)
        val btnAtras = findViewById<Button>(R.id.button1)
        val btnCrearCuenta = findViewById<Button>(R.id.button2)

        btnCrearCuenta.setOnClickListener {
            val nuevoUsuario = newUsername.text.toString().trim()
            val nombres = names.text.toString().trim()
            val apellidos = lastNames.text.toString().trim()
            val correo = email.text.toString().trim()
            val contrasena = password.text.toString()
            val confirmarContrasena = confirmPassword.text.toString()

            if (nuevoUsuario.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contrasena != confirmarContrasena) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("datos_usuario", MODE_PRIVATE)
            val usuarioGuardado = prefs.getString("usuario", "")

            if (nuevoUsuario == usuarioGuardado) {
                Toast.makeText(this, "El usuario ya existe. Por favor inicia sesión.", Toast.LENGTH_SHORT).show()
            } else {
                with(prefs.edit()) {
                    putString("usuario", nuevoUsuario)
                    putString("contrasena", contrasena)
                    apply()
                }
                Toast.makeText(this, "Cuenta creada exitosamente para $nuevoUsuario.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, BaseActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish()
            }
        }

        btnAtras.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}