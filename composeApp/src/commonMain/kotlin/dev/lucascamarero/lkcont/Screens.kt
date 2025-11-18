package dev.lucascamarero.lkcont

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import dev.lucascamarero.lkcont.screens.*
import dev.lucascamarero.lkcont.viewmodels.AppsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class CreateMasterPasswordScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        CreateMasterPassword(
            onAppListClick = { navigator?.push(AppListScreen(vm)) }
        )
    }
}

class LogginScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Loggin(
            onAppListClick = { navigator?.push(AppListScreen(vm)) }
        )
    }
}

class AppListScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        AppList(
            appsViewModel = vm,
            onCreateNewAppClick = { navigator?.push(CreateNewAppScreen(vm)) },
            onEditAppClick = { navigator?.push(EditAppScreen(vm)) }
        )
    }
}

class CreateNewAppScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        CreateNewApp(
            appsViewModel = vm,
            onAppListClick = { navigator?.pop() }
        )
    }
}

class EditAppScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        EditApp(
            onGeneratePasswordClick = { navigator?.push(GeneratePasswordScreen(vm)) },
            onBack = { navigator?.pop() }
        )
    }
}

class GeneratePasswordScreen(
    private val vm: AppsViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        GeneratePassword(
            onBack = { navigator?.pop() }
        )
    }
}

