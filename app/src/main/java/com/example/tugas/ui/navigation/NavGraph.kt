package com.example.tugas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugas.ui.screens.ProfileScreen
import com.example.tugas.ui.screens.SplashScreen
import com.example.tugas.ui.screens.ToDoDetailScreen
import com.example.tugas.ui.screens.ToDoListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash-screen"
    ) {
        composable("splash-screen") {
            SplashScreen(navController = navController)
        }

        composable("todos") {
            ToDoListScreen(navController = navController)
        }

        composable(
            route = "todos/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.StringType })) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")
            ToDoDetailScreen(navController = navController, todoId = todoId)
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                name = "Mustafa Fathur Rahman",
                email = "mustafa.fathur@gmail.com"
            )
        }
    }
}