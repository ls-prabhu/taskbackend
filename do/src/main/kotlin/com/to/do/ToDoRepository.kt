// small non-functional comment added
package com.to.`do`

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ToDoRepository : JpaRepository<ToDo, Long> {

    // Sort all todos by date and time (earliest first)
    @Query(
        value = "SELECT * FROM to_do ORDER BY date ASC, time ASC",
        nativeQuery = true
    )
    fun SortAllbyDateAndTime(): List<ToDo>

    // Group/sort by status (completed last, then by date/time)
    @Query(
        value = "SELECT * FROM to_do WHERE completed=true ORDER BY date ASC, time ASC",
        nativeQuery = true
    )
    fun Completedtsk(): List<ToDo>

    @Query(
        value = "SELECT * FROM to_do WHERE completed=false ORDER BY date ASC, time ASC",
        nativeQuery = true
    )
    fun PendingTask(): List<ToDo>

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM to_do",
        nativeQuery = true
    )
    fun deleteAllTodos() : Int

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM to_do WHERE id IN (:id)",
        nativeQuery = true
    )
    fun BulkDeleteById(id: List<Long>) : Int
}