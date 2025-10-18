package com.example.tugas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugas.R
import com.example.tugas.ui.components.Title
import com.example.tugas.ui.theme.TugasTheme
import com.example.tugas.ui.viewmodel.ToDoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(navController: NavHostController, vm: ToDoViewModel = viewModel()) {

    // ViewModel is automatically retained and shared across composables

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    // State for DatePicker Dialog (keeping the Dialog implementation for simplicity)
    var showDatePickerDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    // Date Text for Display
    val selectedDateText = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: "Due Date"

    // Helper to reset modal states
    val resetModalState: () -> Unit = {
        taskTitle = ""
        taskDescription = ""
        datePickerState.selectedDateMillis = null
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Title(title = "Daftar Tugas", modifier = Modifier) },
                actions = {
                    IconButton(onClick = { navController.navigate("profile")}) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    resetModalState()
                    showBottomSheet = true
                },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    ) { innerPadding ->
        // Main content (ToDo List)
        Box(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .fillMaxSize())
        {
            // Use LazyColumn to efficiently display the list from the ViewModel
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(vm.todos, key = { it.id }) { todo ->
                    // Task Item Display
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Toggle Button
                        IconButton(onClick = { vm.toggleDone(todo.id) }) {
                            Icon(
                                imageVector = if (todo.isDone) Icons.Default.Done else Icons.Outlined.Delete, // Using delete icon as placeholder for unfinished task
                                contentDescription = if (todo.isDone) "Task checked" else "Task pending",
                                tint = if (todo.isDone) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Task Details (Clickable to go to detail screen)
                        Column(
                            modifier = Modifier
                                .weight(1f) // Takes remaining space
                                .clickable(onClick = { navController.navigate("todos/${todo.id}") })
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = todo.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = if (todo.isDone) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = todo.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            // Display Date
                            Text(
                                text = "Due: ${convertMillisToDate(todo.date.time)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        // Delete Button (New)
                        IconButton(onClick = { vm.removeToDo(todo.id) }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete task",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    Divider() // Visual separator
                }
            }
        }

        // Modal Bottom Sheet for adding a ToDo item
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                    resetModalState()
                },
                sheetState = sheetState,
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        label = { Text("Tugas Baru (Title)") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        label = { Text("Detail Tugas (Description)") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = selectedDateText,
                        onValueChange = { /* Read only */ },
                        label = { Text("Due Date") },
                        readOnly = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(onClick = { showDatePickerDialog = true }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Select date"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                // --------------------------------------------
                                // DYNAMIC DATA: ADD TODO LOGIC
                                // --------------------------------------------
                                val selectedDateMillis = datePickerState.selectedDateMillis
                                if (taskTitle.isNotBlank() && selectedDateMillis != null) {
                                    vm.addTodo(
                                        title = taskTitle.trim(),
                                        description = taskDescription.trim(),
                                        date = Date(selectedDateMillis)
                                    )

                                    // Close modal and reset state
                                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                            resetModalState()
                                        }
                                    }
                                }
                                // --------------------------------------------
                            },
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }

        // Date Picker Dialog (This is the standard, better approach than Popup)
        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    TextButton(onClick = { showDatePickerDialog = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePickerDialog = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState, showModeToggle = false)
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Preview(showBackground = true)
@Composable
fun ToDoListScreenPreview() {
    TugasTheme {
        // Use the standard viewModel() composable, passing the factory
        // This satisfies the linter while ensuring your mock data is used.
        ToDoListScreen(
            navController = rememberNavController(),
            vm = viewModel(factory = PreviewToDoViewModelFactory)
        )
    }
}

object PreviewToDoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            // Create the ViewModel instance
            val viewModel = ToDoViewModel()

            // Add dummy data for the preview
            viewModel.addTodo("Buy groceries", "Milk, eggs, bread", Date(System.currentTimeMillis() + 86400000))
            viewModel.addTodo("Study Compose Navigation", "Fix NavGraph issues and learn Arg passing.", Date())

            @Suppress("UNCHECKED_CAST")
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}