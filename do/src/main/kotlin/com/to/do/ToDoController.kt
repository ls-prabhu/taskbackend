package com.to.`do`

import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin
import java.time.LocalDate

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/")
class ToDoController(private val repo : ToDoRepository) {

    @GetMapping
    fun getAll() : List<ToDo> = repo.findAll()

    @PostMapping
    fun create(@RequestBody toDo: ToDo): ToDo {
        return repo.save(toDo)
    }


    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long, @RequestBody body: ToDoPatch): ToDo {
    val todo = repo.findById(id).orElseThrow()

    body.title?.let { todo.title = it }
    body.date?.let { todo.date = it }
    body.time?.let { todo.time = it }

    body.completed?.let { newValue ->
        if (!todo.completed && newValue) todo.completedDate = LocalDate.now()
        if (todo.completed && !newValue) todo.completedDate = null
        todo.completed = newValue
    }

    return repo.save(todo)
}

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        repo.deleteById(id)
    }

    // Get all todos sorted by date and time
    @GetMapping("/st")
    fun getSortedTodos() : List<ToDo> = repo.SortAllbyDateAndTime()

    // Get todos grouped by status (incomplete first, then completed)
    @GetMapping("/completed")
    fun completeTask() : List<ToDo> = repo.Completedtsk()

    @GetMapping("/pending")
    fun pendingTask() : List<ToDo> = repo.PendingTask()

    @DeleteMapping("/deleteall")
fun deleteAll(): String {
        val deletecount = repo.deleteAllTodos()
        return "$deletecount todos deleted"
    }

    @DeleteMapping("/bulkdelete")
    fun deletebyid(@RequestBody body: ToDoBulkDelete) : String {
        val deleteCount = repo.BulkDeleteById(body.ids)
        return "${deleteCount}deleted"
    }
}