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
    fun create(@RequestBody toDo: ToDo): ToDo{
        return repo.save(toDo)
    }

    // Use PUT for updates
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody updatedToDo: ToDo
    ) : ToDo{
        val todo = repo.findById(id).orElseThrow()
        todo.date = updatedToDo.date
        todo.time = updatedToDo.time
        todo.title = updatedToDo.title
        todo.completed = updatedToDo.completed
        when{
            !todo.completed&&updatedToDo.completed->
                updatedToDo.completedDate=LocalDate.now()
            todo.completed&&!updatedToDo.completed->
                updatedToDo.completedDate=null
        }
        todo.completedDate=updatedToDo.completedDate
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
    fun CompleteTask() : List<ToDo> = repo.Completedtsk()

    @DeleteMapping("/deleteall")
fun deleteAll(): String {
        val deletecount = repo.deleteAllTodos()
        return "$deletecount todos deleted"
    }

    @DeleteMapping("/deletebyid/{id}")
    fun deletebyid(@RequestBody ids: List<Long>) : String {
        val DeleteCount = repo.BulkDeleteById(ids)
        return "${DeleteCount}deleted"
    }
}