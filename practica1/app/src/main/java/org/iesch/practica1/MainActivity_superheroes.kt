package org.iesch.practica1

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica1.databinding.ActivityMainSuperheroesBinding
import org.iesch.practica1.model
import java.io.File

class MainActivity_superheroes : AppCompatActivity() {

    // 1 - Creamos la variable lateinit porque la vamos a inicializar luego
    private lateinit var binding: ActivityMainSuperheroesBinding
    // 1 - Creamos una variable que va a manejar el resultado de hacer la foto
    private lateinit var heroImage: ImageView
    private var heroBitMap: Bitmap? = null

    // 1 - Hay que cambiar el metodo takepicturesPreview por takePictures
    private var picturePath = ""

    private val getContent = registerForActivityResult(
        ActivityResultContracts.TakePicture()){
        // Ahora en lugar de un bitmap nos va a devolver un booleano, si la foto es exitosa o no
            success ->
        if (success && picturePath.isNotEmpty() ){
            // Cualquier imagen del directorio la podemos convertir a bitmap
            heroBitMap = BitmapFactory.decodeFile(picturePath)
            // Mostramos la imagen en el cuadradito
            heroImage.setImageBitmap(heroBitMap)
        } else {
            // Si se cancela la cámara no hay foto válida
            picturePath = ""
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2 - Inicializamos el binding
        binding = ActivityMainSuperheroesBinding.inflate(layoutInflater)
        // 3 - Usamos el binding para inflar la vista
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2 Linkeamos el elemento con abrir la cámara
        heroImage = binding.heroImage
        binding.heroImage.setOnClickListener {
            abrirCamara()
        }

        binding.buttonGuardar.setOnClickListener {
            val superHeroName = binding.heroNameEdit.text.toString()
            val alterEgo = binding.alterEgoEdit.text.toString()
            val bio = binding.resumenBiografiaText.text.toString()
            val power = binding.miRatingBar.rating
            val superHeroe = SuperHeroe(superHeroName, alterEgo, bio, power)
            irADetailActivity(superHeroe)
        }

    }

    fun abrirCamara() {
        val imageFile = crearImagenFile()
        val uri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", imageFile)
        getContent.launch(uri)
    }

    private fun crearImagenFile() : File {
        val fileName = "superhero_Image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = imageFile.absolutePath
        return imageFile
    }

    fun irADetailActivity(superHeroe: SuperHeroe) {
        val intent = Intent(this, DetailActivity_superheroes::class.java)
        intent.putExtra("path_heroe", picturePath)
        intent.putExtra("superHeroe", superHeroe)
        startActivity(intent)
    }
}