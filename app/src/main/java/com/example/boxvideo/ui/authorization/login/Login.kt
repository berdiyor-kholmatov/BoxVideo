package com.example.boxvideo.ui.authorization.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Login (
  state: LoginState,
  onEvent: (LoginEvents) -> Unit
) {

    Column( modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Welcome to BoxVideo!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Log in to watch more videos and access your account.",
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.username,
            onValueChange = {
                onEvent(LoginEvents.UsernameInput(it))
            },
            enabled = !state.isLoading,
            label = {
                Text(text = "Username or Email")
            },
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(
                // Цвет текста внутри поля
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                // На фото фон прозрачный — оставляем Transparent
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,

                // Цвет рамки (границы) в разных состояниях
                focusedBorderColor = Color.Gray.copy(alpha = 0.7f),   // Цвет при нажатии
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f), // Цвет в обычном состоянии

                // Цвет подсказки (label), которая улетает наверх
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Gray
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.username,
            onValueChange = {
                onEvent(LoginEvents.UsernameInput(it))
            },
            enabled = !state.isLoading,
            label = {
                Text(text = "Username or Email")
            },
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(
                // Цвет текста внутри поля
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,

                // На фото фон прозрачный — оставляем Transparent
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,

                // Цвет рамки (границы) в разных состояниях
                focusedBorderColor = Color.Gray.copy(alpha = 0.7f),   // Цвет при нажатии
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.4f), // Цвет в обычном состоянии

                // Цвет подсказки (label), которая улетает наверх
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Gray
            )
        )

        Button(
            onClick = {

            },
            modifier = Modifier.padding(6.dp)
        ) {
            Text("Login")
        }






        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier.height(0.5.dp).background(Color.Gray).weight(1f))
            Text("or", color = Color.Gray, modifier = Modifier.padding(6.dp))
            Box(modifier = Modifier.height(0.5.dp).background(Color.Gray).weight(1f))
        }
    }

}