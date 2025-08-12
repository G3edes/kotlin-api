package br.senai.sp.jandira.clienteapp.cliente.cliente.componentes

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun ClienteForm(){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier= Modifier.fillMaxWidth()
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
    }
}

@Preview
@Composable
private fun ClienteFormPreview(){
    ClienteAppTheme {  }()
}