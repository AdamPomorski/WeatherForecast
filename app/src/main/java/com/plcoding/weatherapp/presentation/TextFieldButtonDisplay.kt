//package com.plcoding.weatherapp.presentation
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
//import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
//import com.plcoding.weatherapp.presentation.ui.theme.LightBlue
//import kotlinx.coroutines.launch
//
//@Composable
//fun TextFieldButtonDisplay() {
//    val scaffoldState = rememberScaffoldState()
//    var textFieldState by remember {
//        mutableStateOf("")
//    }
//    val scope = rememberCoroutineScope()
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        scaffoldState = scaffoldState
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .fillMaxSize()
//                .background(DarkBlue)
//        ) {
//            TextField(
//                value = textFieldState,
//                textStyle = TextStyle(color = Color.White),
//                label = { Text(text = "Enter your city", color = Color.White) },
//                onValueChange = {
//                    textFieldState = it
//                },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 20.dp),
//                shape = RoundedCornerShape(15.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = LightBlue,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent
//                )
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    scope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar("Your city is $textFieldState")
//                    }
//
//                },
//                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue),
//                shape = RoundedCornerShape(15.dp)
//            ) {
//                Text(text = "Confirm", color = Color.White)
//            }
//
////            SnackbarHost(
////                hostState = scaffoldState.snackbarHostState,
////                modifier = Modifier.fillMaxWidth(),
////            ) {
////                Snackbar(
////                    backgroundColor = Color.Blue, // Change the background color of the snackbar
////                    shape = RoundedCornerShape(8.dp) // Change the shape of the snackbar
////                ) {
////                    Text(
////                        text = it.message,
////                        color = Color.White // Change the text color of the snackbar
////                    )
////                }
////            }
//
//
//        }
//
//    }
//
//
//}