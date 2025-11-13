package dev.lucascamarero.lkcont

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lucascamarero.lkcont.theme.LkContTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    LkContTheme {

        Scaffold(
            // barra superior
            topBar = { BarraSuperior() },

            // floating action button
            floatingActionButton = { FAB() }

            // cuerpo central
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // definición de rutas de pantallas

            }
        }
    }
}

// Barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior() {

    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(
                "Lk Cont",
                style = MaterialTheme.typography.titleLarge
            )
        },

    )
}

// Floating Action Button
@Composable
fun FAB() {
    FloatingActionButton(
        onClick = {
            // Abrir CreateNewApp
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(60.dp)
        )
    }
}