package com.example.tugas.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugas.ui.components.TopAppBar

@Composable
fun ToDoListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Splash Screen",
                onProfileClick = {navController.navigate("profile")}
            )
        }
    ) { innerPaddding ->
        Box(
            modifier = Modifier
                .padding(innerPaddding)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Text("Ini halaman daftar tugas")
        }
    }
}

@Preview
@Composable
fun ToDoListScreenPreview() {
    ToDoListScreen(navController = rememberNavController())
}