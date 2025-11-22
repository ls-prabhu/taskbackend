package com.to.`do`
import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class ToDo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var date: LocalDate = LocalDate.now(),
    @JsonFormat(pattern = "HH:mm")
    var time : LocalTime? = null,
    var title: String,
    var completed: Boolean = false,
    var completedDate: LocalDate? = null,
)
