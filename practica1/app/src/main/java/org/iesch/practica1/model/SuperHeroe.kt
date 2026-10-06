package org.iesch.practica1.model

import java.io.Serializable

// Serializable permite pasar el objeto entero entre Activities con intent.putExtra()
data class SuperHeroe(
    val nombre: String,
    val alterEgo: String,
    val bio: String,
    val power: Float
) : Serializable
