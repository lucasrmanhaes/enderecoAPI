package com.example.apiendereco.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.apiendereco.api.ApiService
import kotlinx.coroutines.launch

@Composable
fun Endereco(apiService: ApiService) {

    var cep by remember { mutableStateOf("") }
    var rua by remember{ mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var localidade by remember { mutableStateOf("") }
    var StringException by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope() // Adiciona isso aqui

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = cep,
                onValueChange = { cep = it },
                placeholder = { Text(text = "CEP") },
                modifier = Modifier.width(200.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Black ),
                onClick = {
                    coroutineScope.launch { // Usa o CoroutineScope para lançar a corotina
                        try {
                            val resposta = apiService.buscarCep(cep)
                            rua = resposta.logradouro
                            bairro = resposta.bairro
                            localidade = "${resposta.localidade} - ${resposta.uf}"
                        } catch (e: Exception) {
                            StringException = "Erro ao buscar CEP."
                        }
                    }
                })
            {
                Text("Buscar")
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)) {
            OutlinedTextField(
                value = rua,
                onValueChange = { rua = it },
                placeholder = { Text(text = "Rua") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black
                )
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = bairro,
                onValueChange = { bairro = it },
                placeholder = { Text(text = "Bairro") },
                modifier = Modifier.width(200.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black
                )
            )
            OutlinedTextField(
                value = numero,
                onValueChange = { numero = it },
                placeholder = { Text(text = "Nª") },
                modifier = Modifier.width(90.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
            OutlinedTextField(
                value = localidade,
                onValueChange = { localidade = it },
                placeholder = { Text(text = "Localidade") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    cursorColor = Color.Black
                )
            )
        }
    }
}