package com.example.tugas.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Title(title: String, modifier: Modifier) {
    Box(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Text(
            modifier = modifier,
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )
    }
}

@Preview
@Composable
fun TitlePreview() {
    Title(
        "Daftar Tugas",
        Modifier
            .padding(2.dp)
    )
}