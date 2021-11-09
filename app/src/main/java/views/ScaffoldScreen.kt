package views

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.composeapp.ui.theme.ComposeAppTheme

class ScaffoldScreen {

    @Composable
    fun LayoutCodelab(){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "I Love Composables")
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) {innerPadding ->
            ContentBody(Modifier.padding(innerPadding))

        }
    }

    @Composable
    fun ContentBody(modifier: Modifier){
        Text(
            text = "Hi there!",
            modifier = modifier,
            style = MaterialTheme.typography.h4
        )
    }

    @Preview(
        name = "Night view",
        showBackground = true,
        widthDp = 380,
        uiMode = Configuration.UI_MODE_NIGHT_YES
    )
    @Composable
    fun PreviewNight(){
        ComposeAppTheme {
            LayoutCodelab()
        }
    }


    @Preview(
        name = "Day view",
        showBackground = true,
        widthDp = 380
    )
    @Composable
    fun PreviewDay(){
        ComposeAppTheme {
            LayoutCodelab()
        }
    }
}