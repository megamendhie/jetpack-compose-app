package views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.composeapp.ui.theme.ComposeAppTheme

class LayoutScreen {

    @Composable
    fun PhotographerCard(photographer: Photographer){
        Row(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colors.surface)
                .padding(4.dp)

        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.primary
            ) {

            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1F)
            ) {
                Text(text = photographer.name, fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "3 mins ago", style = MaterialTheme.typography.body2)
                }
            }
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically),
                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Like Button")
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text(style = MaterialTheme.typography.h5, text = "Like")
            }
        }
    }

    @Preview(
        name = "Night view",
        showBackground = true,
        widthDp = 320,
        uiMode = UI_MODE_NIGHT_YES
    )
    @Composable
    fun PreviewNight(){
        ComposeAppTheme {
            PhotographerCard(Photographer("Alex Right", 3363))
        }
    }


    @Preview(
        name = "Day view",
        showBackground = true,
        widthDp = 320
    )
    @Composable
    fun PreviewDay(){
        ComposeAppTheme {
            PhotographerCard(Photographer("Alex Right", 3363))
        }
    }


    data class Photographer(val name: String, val lastSeen: Long)
}