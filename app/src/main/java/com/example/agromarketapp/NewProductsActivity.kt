// NewProductsActivity.kt
package com.example.agromarketapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class NewProductsActivity : AppCompatActivity() {

    private lateinit var prefs: android.content.SharedPreferences
    private lateinit var usuarioActual: String
    private lateinit var contenedor: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_products)

        prefs = getSharedPreferences("datos_usuario", MODE_PRIVATE)
        usuarioActual = prefs.getString("usuario_actual", "") ?: ""
        contenedor = findViewById(R.id.contenedorNuevosProductos)

        val todosLosUsuarios = prefs.getString("usuarios", null)?.split(";")?.mapNotNull {
            val campos = it.split("|")
            campos.getOrNull(0)
        } ?: emptyList()

        contenedor.removeAllViews()

        todosLosUsuarios.forEach { usuario ->
            val productosStr = prefs.getString("productos_$usuario", null)
            val productos = productosStr?.split(";")?.map {
                val campos = it.split("|")
                Producto(
                    campos.getOrElse(0) { "" },
                    campos.getOrElse(1) { "" },
                    campos.getOrElse(2) { "0.0" }.toDoubleOrNull() ?: 0.0,
                    campos.getOrElse(3) { "" },
                    campos.getOrElse(4) { "" },
                    campos.getOrElse(5) { "" },
                    campos.getOrElse(6) { "" },
                    campos.getOrElse(7) { "" }
                )
            } ?: emptyList()

            productos.forEachIndexed { index, producto ->
                val layout = LinearLayout(this)
                layout.orientation = LinearLayout.VERTICAL
                layout.setPadding(16)

                val nombre = TextView(this)
                nombre.text = "${producto.nombre} - $${producto.precio}"
                nombre.textSize = 18f
                layout.addView(nombre)

                val uri = prefs.getString("imagen_producto_${usuario}_$index", null)
                if (!uri.isNullOrEmpty()) {
                    val image = ImageView(this)
                    image.setImageURI(Uri.parse(uri))
                    image.layoutParams = LinearLayout.LayoutParams(300, 300)
                    layout.addView(image)
                }

                layout.setOnClickListener {
                    val intent = Intent(this, ViewProductActivity::class.java)
                    intent.putExtra("nombre", producto.nombre)
                    intent.putExtra("descripcion", producto.descripcion)
                    intent.putExtra("precio", producto.precio)
                    intent.putExtra("tipoCultivo", producto.tipoCultivo)
                    intent.putExtra("unidadVenta", producto.unidadVenta)
                    intent.putExtra("fechaCosecha", producto.fechaCosecha)
                    intent.putExtra("certificacion", producto.certificacion)
                    intent.putExtra("regionOrigen", producto.regionOrigen)
                    intent.putExtra("imagenUri", uri)
                    startActivity(intent)
                }

                contenedor.addView(layout)
            }
        }

        findViewById<Button>(R.id.buttonVolver).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.buttonCerrarSesion).setOnClickListener {
            prefs.edit().remove("usuario_actual").apply()
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}
