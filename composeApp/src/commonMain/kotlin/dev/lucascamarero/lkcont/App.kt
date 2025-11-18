package dev.lucascamarero.lkcont

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.lucascamarero.lkcont.theme.LkContTheme
import dev.lucascamarero.lkcont.viewmodels.AppsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator

@Composable
@Preview
fun App() {
    LkContTheme {

        val appsViewModel: AppsViewModel = viewModel()

        Scaffold(
            topBar = { BarraSuperior() }
        ) { innerPadding ->
            // Rellenamos toda la pantalla y aplicamos innerPadding
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ) {
                //Navigator(screen = AppListScreen)
                Navigator(AppListScreen(appsViewModel))
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
        }
    )
}