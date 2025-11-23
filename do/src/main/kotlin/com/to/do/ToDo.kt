package com.to.`do`

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.GeneratedValue

@jakarta.persistence.Entity
data class ToDo(
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var date: java.time.LocalDate = java.time.LocalDate.now(),
    @JsonFormat(pattern = "HH:mm")
    var time : java.time.LocalTime? = null,
    var completed: Boolean = false,
    var completedDate: java.time.LocalDate? = null,
)