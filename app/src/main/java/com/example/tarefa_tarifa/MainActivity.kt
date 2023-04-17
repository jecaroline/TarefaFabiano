package com.example.tarefa_tarifa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tarefa_tarifa.ui.theme.Tarefa_tarifaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tarefa_tarifaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TrabalhoScreen()
                }
            }
        }
    }
}
@Composable
fun TrabalhoScreen() {
    Box(modifier = Modifier.fillMaxWidth()) {
        /*Image(
            painter = painterResource(id = com.example.tarefa_tarifa.R.drawable.viagem),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )*/

        Column(
            modifier = Modifier
                .padding(30.dp),

            verticalArrangement = Arrangement.Top
        ) {
            Conteudo()
        }

    }
}
@Composable
fun Conteudo() {
    val valor = remember {
        mutableStateOf("")
    }

    val percentual = remember {
        mutableStateOf(18f)
    }

    var total15 = 0.0
    var totalCustomizado = 0.0
    var valor15 = 0.0
    var valorCustomizado = 0.0
    try {
        val valor_conta = valor.value
        val porcentagem_variavel = percentual.value
        var porcentagem_fixa: Double = 0.15

        valor15 = valor_conta.toFloat() * porcentagem_fixa
        valorCustomizado = valor_conta.toDouble() * porcentagem_variavel/100
        total15 = valor_conta.toFloat() + valor15
        totalCustomizado = valor_conta.toDouble() + valorCustomizado
    }
    catch (e : java.lang.Exception) {
        Log.i("Erro", e.message ?: "")
    }

    Text(
        text = stringResource(id = com.example.tarefa_tarifa.R.string.titulo),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        color = Color.Magenta,
    )
    Row() {
        Text(
            text = stringResource(id = com.example.tarefa_tarifa.R.string.valor),
            textAlign = TextAlign.Start,
            color = Color.Black,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
    Row() {
        OutlinedTextField(
            value = valor.value,
            onValueChange = { valor.value = it },
            label = { Text("R$0.00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
    }

    Text("Selecione um valor para cálculo  ---------  ${percentual.value} %")
    Row() {
        SeekBarDemo(percentual.value, onChange = {
            percentual.value = it.toFloat()
        })

    }
    Row() {
        Text(
            text = "",
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "15%",
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "${percentual.value}%",
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .weight(1f)
        )
    }

    Row() {
        Text(
            text = stringResource(id = com.example.tarefa_tarifa.R.string.tarifa),
            textAlign = TextAlign.End,
            color = Color.Magenta,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        Text(
            text = "$valor15",
            modifier = Modifier
                .background(Color.Gray)
                .weight(1f)
                .padding(8.dp)
            ,
            textAlign = TextAlign.Center

        )
        Text(
            text = "$valorCustomizado",
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

    }
    Row() {

        Text(
            text = stringResource(id = com.example.tarefa_tarifa.R.string.total),
            textAlign = TextAlign.End,
            color = Color.Magenta,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        Text(
            text = "$total15",
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .padding(8.dp)
            ,
            textAlign = TextAlign.Center

        )
        Text(
            text = "$totalCustomizado",
            modifier = Modifier
                .background(Color.Gray)
                .weight(1f)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

    }
}
@Composable
fun SeekBarDemo(progress: Float, onChange: (value: Int) -> Unit) {
    Slider(
        value = progress,
        onValueChange = { value ->
            onChange(value.toInt())
        },
        modifier = Modifier.width(500.dp),
        valueRange = 0f..30f,
        steps = 1

    )
}
/*@Composable
fun Calculador(porcentagem_variavel: Float, valor_conta: Float) {
var valor_conta: Float
var porcentagem_fixa: Double = 0.15
var porcentagem_variavel: Float

/*val valor15 = valor_conta * porcentagem_fixa
val valorCustomizado = valor_conta * porcentagem_variavel
val total15 = valor_conta + valor15
val totalCustomizado = valor_conta + valorCustomizado
*/
}*/

@Preview(showBackground = true, showSystemUi = true) //faz um preview da aplicação
@Composable
fun DefaultPreview() {
    Tarefa_tarifaTheme {
        TrabalhoScreen()
    }
}