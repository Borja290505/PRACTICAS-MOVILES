package org.iesch.practica1

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica1.databinding.ActivityDetailSuperheroesBinding
import org.iesch.practica1.model.SuperHeroe

class DetailActivity_superheroes : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSuperheroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Datos del superhéroe
        val superHeroe = IntentCompat.getSerializableExtra(intent, "superHeroe", SuperHeroe::class.java)
        if (superHeroe != null) {
            binding.heroNameTv.text = superHeroe.nombre
            binding.alterEgoResult.text = superHeroe.alterEgo
            binding.bioResult.text = superHeroe.bio
            binding.ratingBarInmovible.rating = superHeroe.power
        }

        // Foto (solo si se ha hecho una)
        val bitmapDirectory = intent.getStringExtra("path_heroe")
        if (!bitmapDirectory.isNullOrEmpty()) {
            val bitmap = BitmapFactory.decodeFile(bitmapDirectory)
            if (bitmap != null) {
                binding.imagenSuperHeroe.setImageBitmap(bitmap)
            }
        }
    }
}
