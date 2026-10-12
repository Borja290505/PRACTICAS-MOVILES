package org.iesch.practica1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.iesch.practica1.databinding.ActivityPreguntasBinding

class Quizz : AppCompatActivity() {

    private lateinit var binding: ActivityPreguntasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var arrayProgressText = arrayOf("1/3", "2/3", "3/3")

        var arrayProgreso = arrayOf(33, 66, 100)

        var arrayPreguntaActual = arrayOf(
            "¿Qué valor tiene Y en esta situación?",
            "¿Qué valor tiene X en esta situación?",
            "¿Qué valor tiene Z en esta situación?"
        )
        var arrayPreguntaActualExtra = arrayOf(
            "10 y = 70",
            "20 x = 10",
            "30 z = 150"
        )

        var arrayOpcion1 = arrayOf(
            "y = 7",
            "x = 1",
            "z = 15"
        )

        var arrayOpcion2 = arrayOf(
            "y = 70",
            "x = 0.5",
            "z = 5"
        )

        // Opcion correcta de cada pregunta
        var arrayRespuestaCorrecta = arrayOf(1, 2, 2)

        var arrayLogos = arrayOf(
            R.drawable.icono_check,
            R.drawable.wrong_icon
        )

        var arrayAnswerGrande = arrayOf(
            "¡Correcto!",
            "¡Erróneo!"
        )

        var arrayAnswerPequeño = arrayOf(
            "Has elegido la respuesta correcta.",
            "Has elegido la respuesta incorrecta."
        )


        var preguntaActual = intent.getIntExtra("numeroPregunta", 1)
        var numeroAciertos = intent.getIntExtra("numeroAciertos", 0)
        var posicion = preguntaActual - 1


        // Inicializamos con valores por defecto
        binding.progressText.text = arrayProgressText[posicion]
        binding.barraProgreso.progress = arrayProgreso[posicion]
        binding.opcion1.text = arrayOpcion1[posicion]
        binding.opcion2.text = arrayOpcion2[posicion]
        binding.preguntaTarjetaActual.text = arrayPreguntaActual[posicion]
        binding.ecuacionPreguntaActual.text = arrayPreguntaActualExtra[posicion]


        // Logica radioButton
        var opcionEscogida = 0

        binding.radioGroup.setOnCheckedChangeListener { group, i ->

            when(i){
                binding.opcion1.id -> {
                    opcionEscogida = 1
                }
                binding.opcion2.id -> {
                    opcionEscogida = 2
                }
            }


        }


        // Logica QUIZZ
        var intentParaElQuizz = Intent(this, quizzAnswer::class.java)
        binding.botonSiguiente.setOnClickListener {

            if (opcionEscogida == 0) {
                Toast.makeText(this, "Por favor, selecciona una opción", Toast.LENGTH_SHORT).show()

            } else {
                if (opcionEscogida == arrayRespuestaCorrecta[posicion]) {

                    intentParaElQuizz.putExtra("logoCorrespondiente", arrayLogos[0])
                    intentParaElQuizz.putExtra("textoGrande", arrayAnswerGrande[0])
                    intentParaElQuizz.putExtra("textoPequeño", arrayAnswerPequeño[0])
                    intentParaElQuizz.putExtra("numeroAciertos", numeroAciertos + 1)

                } else {

                    intentParaElQuizz.putExtra("logoCorrespondiente", arrayLogos[1])
                    intentParaElQuizz.putExtra("textoGrande", arrayAnswerGrande[1])
                    intentParaElQuizz.putExtra("textoPequeño", arrayAnswerPequeño[1])
                    intentParaElQuizz.putExtra("numeroAciertos", numeroAciertos)

                }

                intentParaElQuizz.putExtra("numeroPregunta", preguntaActual)
                startActivity(intentParaElQuizz)
                finish()
            }
        }
    }
}