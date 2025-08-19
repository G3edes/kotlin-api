package br.senai.sp.jandira.clienteapp.cliente

import android.content.res.Configuration
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BarraDeTitulo
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BarraInferior
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.BotaoFlutuante
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.ClienteForm
import br.senai.sp.jandira.clienteapp.cliente.cliente.componentes.Conteudo
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun ListaDeClientes(){

    var controleNavegacao = rememberNavController()

    Scaffold (
        topBar = {
            BarraDeTitulo()
        },
        bottomBar = {
            BarraInferior(
                controleNavegacao
            )
        },
        floatingActionButton = {
            BotaoFlutuante(controleNavegacao )
        },
        content = { padding ->
            //garante a margem na parte de cima
            NavHost(navController = controleNavegacao,
                startDestination = "Lista"){
                composable(route="Lista"){Conteudo(padding,controleNavegacao)}
                composable(route="Cadastro"){ClienteForm(padding, controleNavegacao)}
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