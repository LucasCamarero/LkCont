package dev.lucascamarero.lkcont.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lucascamarero.lkcont.models.App
import dev.lucascamarero.lkcont.viewmodels.AppsViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateNewApp(
    appsViewModel: AppsViewModel,
    onAppListClick: () -> Unit
) {

    var appName by remember { mutableStateOf("") }
    var appUser by remember { mutableStateOf("") }
    var pw1 by remember { mutableStateOf("") }
    var errorPw1 by remember { mutableStateOf(false) }
    var confirm1 by remember { mutableStateOf("") }
    var errorConfirm1 by remember { mutableStateOf(false) }
    var pw2 by remember { mutableStateOf("") }
    var errorPw2 by remember { mutableStateOf(false) }
    var confirm2 by remember { mutableStateOf("") }
    var errorConfirm2 by remember { mutableStateOf(false) }
    var url by remember { mutableStateOf("") }
    // estado que gestiona el Snackbar (es como el "controlador" del mensaje)
    val snackbarHostState = remember { SnackbarHostState() }
    // corrutina scope para lanzar tareas en segundo plano (necesario para mostrar el Snackbar)
    val scope = rememberCoroutineScope()
    var app by remember { mutableStateOf(App("", "","","", ""))}
    var checked by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        // Botón para volver atrás
        item {
            CreateIconBack(onAppListClick)
        }

        item {
            HorizontalDivider()
        }

        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start) {

                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    // Texto App
                    Text("Nueva App",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary)
                }

                Spacer(modifier = Modifier.padding(16.dp))

                // Nombre de la app
                Text("Nombre de la app",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.padding(3.dp))

                CreateOutlinedTextField(appName,
                    "Nombre de la app",
                    false,
                    { appName = it }
                )

                Spacer(modifier = Modifier.padding(14.dp))

                // Usuario
                Text("Usuario",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.padding(3.dp))

                CreateOutlinedTextField(appUser,
                    "Usuario",
                    false,
                    { appUser = it },
                    true
                )

                Spacer(modifier = Modifier.padding(14.dp))

                // Contraseña 1
                Text("Contraseña",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.padding(3.dp))

                CreateOutlinedTextField(pw1,
                    "Contraseña",
                    errorPw1,
                    { pw1 = it
                    errorPw1 = false},
                    true
                )

                Spacer(modifier = Modifier.padding(14.dp))

                // Confirmacion contraseña 1
                Text("Confirmación contraseña",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.padding(3.dp))

                CreateOutlinedTextField(confirm1,
                    "Confirmar contraseña",
                    errorConfirm1,
                    { confirm1 = it
                    errorConfirm1 = false},
                    true
                )

                Spacer(modifier = Modifier.padding(14.dp))

                // componente que muestra realmente el Snackbar en pantalla
                SnackbarHost(
                    hostState = snackbarHostState
                ) { data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }

                // Checkbox para mostrar u ocultar segunda contraseña
                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary, // color del check marcado
                            uncheckedColor = MaterialTheme.colorScheme.primary, // color del borde cuando está desmarcado
                            checkmarkColor = MaterialTheme.colorScheme.onPrimary // color del símbolo dentro del check
                        )
                    )

                    Text(text = if (checked) "Ocultar segunda contraseña"
                    else "Añadir otra contraseña (OPCIONAL)",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(14.dp))

                if (checked != false) {
                    // Contraseña 2
                    Text("(OPCIONAL) Otra contraseña",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary)

                    Spacer(modifier = Modifier.padding(3.dp))

                    CreateOutlinedTextField(pw2,
                        "(OPCIONAL) Otra contraseña",
                        errorPw2,
                        { pw2 = it
                            errorPw2 = false},
                        true
                    )

                    Spacer(modifier = Modifier.padding(14.dp))

                    // Confirmacion contraseña 2
                    Text("(OPCIONAL) Confirmar segunda contraseña",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary)

                    Spacer(modifier = Modifier.padding(3.dp))

                    CreateOutlinedTextField(confirm2,
                        "(OPCIONAL) Confirmar segunda contraseña",
                        errorConfirm2,
                        { confirm2 = it
                            errorConfirm2 = false},
                        true
                    )

                    Spacer(modifier = Modifier.padding(14.dp))
                }


                // Url
                Text("Url",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.padding(3.dp))


            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {

                    if(pw1 != confirm1 || pw1.isEmpty()) {
                        errorPw1 = true
                        errorConfirm1 = true
                        pw1 = ""
                        confirm1 = ""
                        scope.launch {
                            snackbarHostState.showSnackbar("Las contraseñas no coinciden" +
                                    " o alguna no se ha introducido")
                        }
                    } else if (pw2 != confirm2){
                        errorPw2 = true
                        errorConfirm2 = true
                        pw2 = ""
                        confirm2 = ""
                        scope.launch {
                            snackbarHostState.showSnackbar("Las contraseñas no coinciden")
                        }
                    } else if (appName.isEmpty()){
                        scope.launch {
                            snackbarHostState.showSnackbar("Debes darle un nombre a la App")
                        }
                    } else if (appUser.isEmpty()){
                        scope.launch {
                            snackbarHostState.showSnackbar("Debes introducir un usuario ")
                        }
                    } else {
                        app.name = appName.uppercase()
                        app.user = appUser
                        app.pw1 = pw1
                        app.pw2 = pw2
                        app.url = url
                        appsViewModel.addApp(app)
                        onAppListClick()
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary)
                ) {
                    Text("Crear App",
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun CreateIconBack(funcionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        IconButton(
            onClick = funcionClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Regresar",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            "Volver atrás",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CreateOutlinedTextField(
    s1: String,
    s2: String,
    error: Boolean,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = s1,
        onValueChange = onValueChange,
        placeholder = { Text(s2) },
        isError = error,
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            // Solo mostrar si es un campo de contraseña
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible)
                            "Ocultar contraseña"
                        else
                            "Mostrar contraseña"
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.tertiary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary
        )
    )
}