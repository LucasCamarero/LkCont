package dev.lucascamarero.lkcont

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import dev.lucascamarero.lkcont.screens.AppList
import dev.lucascamarero.lkcont.screens.CreateNewApp
import dev.lucascamarero.lkcont.screens.EditApp
import dev.lucascamarero.lkcont.viewmodels.AppsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.lucascamarero.lkcont.screens.CreateMasterPassword
import dev.lucascamarero.lkcont.screens.GeneratePassword
import dev.lucascamarero.lkcont.screens.Loggin

// ENRUTAMIENTO DE VENTANAS

// Ventana para crear contraseña maestra
object CreateMasterPasswordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        CreateMasterPassword(
            onAppListClick = { navigator?.push(AppListScreen) }
        )
    }
}

// Ventana para loggearse
object LogginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Loggin(
            onAppListClick = { navigator?.push(AppListScreen) }
        )
    }
}

// Ventana principal que muestra una lista de apps
object AppListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val appsViewModel: AppsViewModel = viewModel()
        AppList(
            appsViewModel = appsViewModel,
            onCreateNewAppClick = { navigator?.push(CreateNewAppScreen) },
            onEditAppClick = { navigator?.push(EditAppScreen) }
        )
    }
}

// Ventana para crear las contraseñas de una app
object CreateNewAppScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        CreateNewApp(
            onBack = { navigator?.pop() }
        )
    }
}

// Ventana para editar las contraseñas de un app
object EditAppScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        EditApp(
            onGeneratePasswordClick = { navigator?.push(GeneratePasswordScreen) },
            onBack = { navigator?.pop() }
        )
    }
}

// Ventana para generar contraseñas aleatoriamente
object GeneratePasswordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        GeneratePassword(
            onBack = { navigator?.pop() }
        )
    }
}
