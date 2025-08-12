package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await


@Composable
fun ClienteForm(padding: PaddingValues, controleNavegacao: NavHostController?) {

    var nomeCliente by remember { mutableStateOf("") }
    var emailCliente by remember { mutableStateOf("") }
    val clienteAPI = RetrofitFactory().getClienteService()

    Surface(
        modifier = Modifier.padding(padding).fillMaxSize()
    ) {
        Column(
        modifier = Modifier
            .background(
                MaterialTheme
                    .colorScheme
                    .primaryContainer
            )
            .fillMaxSize()
    ){
        Row(
            modifier= Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person"
            )
            Text(
                text = "Novo Cliente",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer( modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = emailCliente,
            onValueChange = {emailCliente= it},
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            label = {
                Text(text = "Email do cliente")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = {nomeCliente= it},
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            label = {
                Text(text = "Nome do cliente")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        Button(
            onClick = {
                val cliente = Cliente(
                    id = null,
                    nome = nomeCliente,
                    email = emailCliente
                )
                GlobalScope.launch ( Dispatchers.IO ){
                    val clienteNovo = clienteAPI.gravar(cliente).await()
                    println("***************$clienteNovo")
                    controleNavegacao?.navigate("conteudo")
                }
            },
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(text = "Adicionar Cliente")
        }
    }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
private fun ClienteFormPreview(){
    ClienteAppTheme { ClienteForm(PaddingValues(0.dp), null) }
}