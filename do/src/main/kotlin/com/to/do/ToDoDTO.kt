package com.to.`do`

import java.time.LocalDate
import java.time.LocalTime

data class ToDoPatch(
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val title: String? = null,
    val completed: Boolean? = null
)

data class ToDoBulkDelete(
    val ids: List<Long>
)