package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BotaoFlutuante(controleNavegacao: NavHostController?) {
    FloatingActionButton(
        onClick = {controleNavegacao!!.navigate("cadastro")}
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botao adicionar"
        )
    }
}