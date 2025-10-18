package com.example.tugas.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugas.ui.theme.TugasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoDetailScreen(navController: NavHostController, todoId: String?) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
//                    Title(
//                        title = "Detail Tugas",
//                        modifier = Modifier
//                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Todo List")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* menghapus data tugas */ }
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Todo")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* fab click handler */ },
                containerColor = MaterialTheme.colorScheme.secondary,
            ) {
                Text(
                    text = "Tandai Sebagai Selesai",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        bottomBar = {}
    ) { innerPaddding ->
        Column(
            modifier = Modifier
                .padding(innerPaddding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Judul Tugas",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.padding(6.dp)
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Ini doang yang paling mendekati utk mewakili deskripsi"
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        "Deskripsi sadajsdnjs andkjan kjdnsakjnd kjsadnkdn kansdjsandkj naskjndjkasndkjasndkasndkjasnkj"
                    )
                }

                Spacer(Modifier.height(12.dp))

                Row(
                    Modifier.clickable(onClick = {})
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Ini doang yang paling mendekati utk mewakili deskripsi"
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Garis Mati"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ToDoDetailScreenPreview() {
    TugasTheme {
        ToDoDetailScreen(navController = rememberNavController(), todoId = null)
    }
}