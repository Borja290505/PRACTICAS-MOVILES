package org.iesch.practica1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.iesch.practica1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Si no llega usuario (null) o llega vacío, mostramos "Usuario sin nombre"
        val usuario = intent.getStringExtra("usuario")
        val nombre = if (usuario.isNullOrBlank()) getString(R.string.usuario_sin_nombre) else usuario
        binding.mainSaludoText.text = getString(R.string.hola_usuario, nombre)

        binding.layoutEdadCanina.setOnClickListener {
            startActivity(Intent(this, EdadCanina::class.java))
        }

        binding.layoutSuperHeroes.setOnClickListener {
            startActivity(Intent(this, MainActivity_superheroes::class.java))
        }
    }
}
