package com.example.tugas.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugas.R
import com.example.tugas.ui.theme.TugasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    name: String = "Fathur",
    email: String = "example@mail.com"
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = email,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("todos")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Profile Page"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)
            )
        },
        content = {  innerPaddding ->
            Box(
                modifier = Modifier
                    .padding(innerPaddding)
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Foto Profile",
                            modifier = Modifier
                                .padding(12.dp)
                                .clip(CircleShape)
                                .size(128.dp)
                                .border(width = 2.dp, color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Text(text = "Halo, $name")

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
                            modifier = Modifier
                                .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                            ) {
                            Text(
                                text = "Manage Your Account",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        Box(
                            modifier = Modifier
                                .align(Alignment.Start)
                        ){
                            Text(
                                text = "More options",
                            )
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        Box(
                            modifier = Modifier
                                .border(width = 4.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                                .padding(12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = "Setting Icon Cik"
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                    Text(
                                        text = "Settings"
                                    )
                                }
                                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = "Icon Tanda Tanga Nggak ada"
                                    )
                                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                    Text(
                                        text = "Help & Feedback"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ProfileScreenPreview() {
    TugasTheme {
        ProfileScreen(
            navController = rememberNavController(),
            "Mustafa Fathur Rahman",
            "mustafa.fathur@gmail.com"
        )
    }
}