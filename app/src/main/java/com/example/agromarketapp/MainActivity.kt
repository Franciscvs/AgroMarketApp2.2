package com.example.agromarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCerrarSesion = findViewById<Button>(R.id.button1)
        val btnTuTienda = findViewById<Button>(R.id.button2)
        val btnTuUsuario = findViewById<Button>(R.id.button3)
        val btnNuevosProductos = findViewById<Button>(R.id.button4)
        val btnProductosComprados = findViewById<Button>(R.id.button5)
        val btnOtrasTiendas = findViewById<Button>(R.id.button6)
        val btnOtrosUsuarios = findViewById<Button>(R.id.button7)


        btnTuTienda.setOnClickListener {
            val intent = Intent(this, YourStoreActivity::class.java)
            startActivity(intent)
        }

        btnTuUsuario.setOnClickListener {
            val intent = Intent(this, YourUsernameActivity::class.java)
            startActivity(intent)
        }

        btnNuevosProductos.setOnClickListener {
            val intent = Intent(this, NewProductsActivity::class.java)
            startActivity(intent)
        }

        btnProductosComprados.setOnClickListener {
            val intent = Intent(this, PurchasedProductsActivity::class.java)
            startActivity(intent)
        }

        btnOtrasTiendas.setOnClickListener {
            val intent = Intent(this, OtherStoresActivity::class.java)
            startActivity(intent)
        }

        btnOtrosUsuarios.setOnClickListener {
            val intent = Intent(this, OtherUsersActivity::class.java)
            startActivity(intent)
        }

        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}