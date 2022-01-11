package io.github.datt16.compose_tutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.datt16.compose_tutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                CardView(msg = Message("Title", "This is Sample Card"))
            }
        }
    }
}

data class Message(val title: String, val description: String)

@Composable
fun CardView(msg: Message) {

    // Row: 横並びのレイアウト
    //  └ modifier: paddingとか指定できる。Modifier.???
    // 単位は [数値].dp などで指定 (androidx.compose.ui.unit)
    Row(modifier = Modifier.padding(all = 8.dp)) {
        // Image: 画像を配置するコンポーネント
        Image(
            // painterResource: drawableなどのリソースフォルダ上の画像をid指定
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = "sample icon",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                .background(MaterialTheme.colors.secondary)
        )

        // Spacer: 隙間を入れられる, マージンの代わりかな?
        Spacer(modifier = Modifier.width(8.dp))

        // Column: 縦に並べるレイアウト
        Column {
            Text(
                text = msg.title,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = msg.description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }

        }

    }

}

// @Preview: プレビューの定義
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)

@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        CardView(msg = Message("Title", "This is sample Card"))
    }
}
