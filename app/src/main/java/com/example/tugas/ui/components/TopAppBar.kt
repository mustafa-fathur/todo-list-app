package com.example.tugas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, onProfileClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Title(title = title, modifier = Modifier.padding(2.dp))
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile"
                )
            }
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(title = "Daftar Tugas", onProfileClick = {})
}