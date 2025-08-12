package br.senai.sp.jandira.clienteapp.cliente

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BarraInferior
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.Conteudo
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun ListaDeClientes(modifier: Modifier = Modifier){

    var controleNavegacao = rememberNavController()

    Scaffold (
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            BarraInferior()
        },
        floatingActionButton = {
            BotoaoFlutuante()
        },
        content = { padding ->
            //garante a margem na parte de cima
            NavHost(navController = controleNavegacao,
                startDestination = "Lista"){
                composable(route="lista"){Conteudo(padding)}
            }
        }
    )
}




@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListaDeClientesPreview(){
    ClienteAppTheme {
        ListaDeClientes()
    }
}