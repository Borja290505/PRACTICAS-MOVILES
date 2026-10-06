package org.iesch.practica1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica1.databinding.IntroActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: IntroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IntroActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {

            val usuario = binding.cojerAqui.text.toString()

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("usuario", usuario)

            startActivity(intent)
        }
    }
}
