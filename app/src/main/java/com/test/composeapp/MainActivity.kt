package com.test.composeapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp(){
        var showOnboarding by rememberSaveable { mutableStateOf(true) }
        if(showOnboarding)
            ShowOnBoardingScreen { showOnboarding = false }
        else
            RunThisShit(List(50){"$it"})
    }

    @Composable
    fun ShowOnBoardingScreen(onContinueClicked: () -> Unit){
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome To Mendhie Compose App")
                Button(
                    onClick = onContinueClicked,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }

    @Composable
    fun RunThisShit(names:List<String> = listOf("Funny Mega", "Dude")){
        LazyColumn(modifier = Modifier.padding(4.dp)){
            items(items = names){ name ->
                CardContent(name = name)
            }
        }
    }

    @Composable
    fun CardContent(name: String){
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            SayHi(name = name)
        }
    }

    @Composable
    fun SayHi(name: String){
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(
            targetValue = if(expanded) 42.dp else 0.dp,
        )

        Row(modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Column (modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))){
                Text(text = "Hello  $name")
                Text(text = "I know you",
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))
                if(expanded)
                    Text(text = "This is a long explanation of how things shold be"
                        .repeat(4))
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if(expanded)
                        Filled.ThumbUp
                    else
                        Filled.ArrowDropDown,
                    contentDescription = if(expanded)
                        stringResource(R.string.show_less)
                    else
                        stringResource(R.string.show_more
                        ))
            }
        }
    }

    
    @Preview(
        showBackground = true,
        name = "Dark Mode",
        widthDp = 320,
        uiMode = UI_MODE_NIGHT_YES
    )
    @Composable
    fun SeeHowfar(){
        ComposeAppTheme {
            RunThisShit(List(50){"$it"})
        }
    }


    @Preview(
        showBackground = true,
        name = "Light Mode",
        widthDp = 320
    )
    @Composable
    fun SeeHowfarLight(){
        ComposeAppTheme {
            RunThisShit(List(50){"$it"})
        }
    }
}