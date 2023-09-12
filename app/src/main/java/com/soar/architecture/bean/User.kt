package com.soar.architecture.bean

import java.io.Serializable


data class User(
    val email: String = "",
    val latlng: String = "",
    val name: String = ""
) : Serializable
