package com.example.tugas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tugas.ui.viewmodel.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoDetailScreen(
    navController: NavHostController,
    todoId: String?,
    vm: ToDoViewModel = viewModel()
) {
    // Attempt to find the task using the ID from the navigation arguments
    val todo = todoId?.let { vm.getTodoById(it) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(todo?.title ?: "Task Not Found") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (todo != null) {
                        IconButton(onClick = {
                            vm.removeToDo(todo.id)
                            navController.popBackStack() // Go back after deletion
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (todo == null) {
            // Handle case where todoId is null or the task is not found
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "The requested To-Do item was not found.",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            // Display Task Details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = todo.description,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Due Date:",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(todo.date),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = todo.isDone,
                        onCheckedChange = { vm.toggleDone(todo.id) },
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (todo.isDone) "Completed" else "Mark as Complete",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}