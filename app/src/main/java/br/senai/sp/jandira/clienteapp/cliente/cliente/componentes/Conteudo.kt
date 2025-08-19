package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.ClienteService
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun Conteudo (paddingValues: PaddingValues, controleNavegacao: NavHostController?) {

    var clienteAPI = RetrofitFactory().getClienteService()
    var clientes by remember {
        mutableStateOf(listOf<Cliente>())
    }

    LaunchedEffect((Dispatchers.IO)) {
        clientes = clienteAPI.exibirTodos().await()
    }
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        Row {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person"
            )
            Text(
                text = "Lista de Clientes"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text =  "Lista de Compradores"
            )
        }
        LazyColumn(contentPadding = PaddingValues(bottom = 80.dp)) {


            items(clientes){ cliente ->
                ClienteCard(cliente, clienteAPI,controleNavegacao)
            }
        }
    }
}
@Composable
fun ClienteCard(cliente: Cliente, clienteAPI: ClienteService, controleNavegacao: NavHostController?) {
    var deletemessage by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = cliente.nome,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = cliente.email,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            IconButton(onClick = {
                deletemessage = true
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "texto",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        if (deletemessage) {
            AlertDialog(
                onDismissRequest = {
                    deletemessage = false
                },
                title = {
                    Text(text = "AVISO!")
                },
                text = {
                    Text(text = "Você tem certeza que deseja apagar este usuário?")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Attention"
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            val clientedelete=clienteAPI.excluir(cliente)
                            println("##############$clientedelete aaaaaaa")



                        }
                        controleNavegacao!!.navigate("Lista")
                    }) {
                        Text(text = "Sim")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        deletemessage = false
                    }) {
                        Text(text = "Não")
                    }
                }
            )
        }
    }
}
@Preview
@Composable
private fun ConteudoPreview(){
    ClienteAppTheme {
        Conteudo(PaddingValues(16.dp), controleNavegacao=null)
    }
}