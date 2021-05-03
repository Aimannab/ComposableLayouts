package com.example.composablelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composablelayouts.ui.theme.ComposeLayoutsTheme

class MovieTicket : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MovieTicketCard()
                }
            }
        }
    }
}

@Composable
fun MovieTicketCard(modifier: Modifier = Modifier) {
    Row(
        movieCardModifier(modifier)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_the_great_gatsby_foreground),
                contentDescription = "The Great Gatsby",
                Modifier.background(Color.Black)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "🎉 MOVIE NIGHT 🎉",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                )
            )
            Text(
                text = "The Great Gatsby",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Friday | March 7 | 9pm",
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Seat XX | Row YY",
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun movieCardModifier(modifier: Modifier) = modifier
    .padding(8.dp) //is not included in the clickable area
    .clip(RoundedCornerShape(4.dp))
    .background(Color.DarkGray)
    .clickable(onClick = { "/* Ignoring onClick*/" })
    .padding(16.dp) //is included in the clickable area
    .graphicsLayer {
        shape = MovieTicketShape(24.dp.toPx())
        clip = true
    }
    .drawBehind {
        scale(scale = 0.9f) {
            drawPath(
                path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                color = Color.Red,
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )
            )
        }
    }
    .padding(start = 32.dp, top = 64.dp, 32.dp, bottom = 64.dp)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLayoutsTheme {
        MovieTicketCard()
    }
}