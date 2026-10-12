package org.iesch.practica1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.iesch.practica1.databinding.ActivitySolucionBinding

class quizzAnswer : AppCompatActivity() {

    private lateinit var binding: ActivitySolucionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySolucionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logoCorrespondiente = intent.getIntExtra("logoCorrespondiente", 0)
        val textoGrande = intent.getStringExtra("textoGrande") ?: ""
        val textoPequeño = intent.getStringExtra("textoPequeño") ?: ""
        val numeroAciertos = intent.getIntExtra("numeroAciertos", 0)
        val numeroPregunta = intent.getIntExtra("numeroPregunta", 1)

        if (logoCorrespondiente != 0) {
            binding.imagenAnswer.setImageResource(logoCorrespondiente)
        }
        binding.textoRespuestaAnswer.text = textoGrande
        binding.textoRespuestaAnswerExtra.text = textoPequeño

        if (numeroPregunta >= 3) {
            binding.textoAnswerFinalizado.visibility = View.VISIBLE
            binding.numAciertos.visibility = View.VISIBLE
            binding.numAciertos.text = "Has acertado: $numeroAciertos preguntas"
            binding.buttonSiguienteAnswer.text = "Finalizar"
            binding.buttonVolverIntentar.visibility = View.VISIBLE

            binding.buttonSiguienteAnswer.setOnClickListener {
                val intentMain = Intent(this, MainActivity::class.java)
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentMain)
                finish()
            }

            binding.buttonVolverIntentar.setOnClickListener {
                val intentRetry = Intent(this, Quizz::class.java)
                intentRetry.putExtra("numeroPregunta", 1)
                intentRetry.putExtra("numeroAciertos", 0)
                startActivity(intentRetry)
                finish()
            }
        } else {
            binding.textoAnswerFinalizado.visibility = View.GONE
            binding.numAciertos.visibility = View.GONE
            binding.buttonVolverIntentar.visibility = View.GONE

            binding.buttonSiguienteAnswer.setOnClickListener {
                val intentNext = Intent(this, Quizz::class.java)
                intentNext.putExtra("numeroPregunta", numeroPregunta + 1)
                intentNext.putExtra("numeroAciertos", numeroAciertos)
                startActivity(intentNext)
                finish()
            }
        }
    }
}
