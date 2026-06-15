package com.example.boxvideo.ui.authorization.login


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.boxvideo.R

@Composable
fun Login (
  state: LoginState,
  onEvent: (LoginEvents) -> Unit,
  onRegister: () -> Unit
) {

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Welcome to BoxVideo!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Log in to watch more videos and access your account.",
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = Color.Gray
            )
        }


        Spacer(
            modifier = Modifier.height(32.dp)
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
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = {
                onEvent(LoginEvents.PasswordInput(it))
            },
            enabled = !state.isLoading,
            label = {
                Text(text = "Password")
            },
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(
                // Цвет текста внутри поля
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

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

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = { onEvent(LoginEvents.LoginPressed) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE), // Поставьте нужный вам сочный цвет (например, синий)
                contentColor = Color.White,         // Цвет текста/иконки внутри
                disabledContainerColor = Color.Gray.copy(alpha = 0.4f), // Цвет, когда state.isLoading == true
                disabledContentColor = Color.White.copy(alpha = 0.6f)
            )
        ) {
            Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }


        Spacer(modifier = Modifier.height(24.dp))



        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier
                .height(0.5.dp)
                .background(Color.LightGray)
                .weight(1f))
            Text("or", color = Color.Gray, modifier = Modifier.padding(6.dp), fontSize = 12.sp)
            Box(modifier = Modifier
                .height(0.5.dp)
                .background(Color.LightGray)
                .weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SignInMethodsBox(R.drawable.material_icon_theme__google) {}
            SignInMethodsBox(R.drawable.logos__facebook) {}
            SignInMethodsBox(R.drawable.logos__apple) {}
        }

        Spacer(modifier = Modifier.height(16.dp))



            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                ) {
                    append("Don't have an account yet? ")
                }

                withLink(
                    LinkAnnotation.Clickable(
                        tag = "custom_action",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                fontSize = 14.sp,
                                color = Color(0xFF111827),
                                fontWeight = FontWeight.Bold,
                            )
                        ),
                        linkInteractionListener = { }
                    )
                ) {
                    append("Register")
                }
            }
            Text(text = annotatedString)
    }
}

@Composable
fun SignInMethodsBox(icon: Int, onClick: () -> Unit){
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = Modifier
            .size(width = 88.dp, height = 44.dp)
            .clip(shape)
            .border(1.dp, color = Color.Gray, shape = shape)
            .background(color = Color.Transparent)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

//
//@Preview(
//    showSystemUi = true,
//)
//@Composable
//fun LoginView(){
//    Login(LoginState(), { print("Event: $it") }, {})
//}