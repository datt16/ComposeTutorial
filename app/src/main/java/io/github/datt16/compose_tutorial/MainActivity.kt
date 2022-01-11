package io.github.datt16.compose_tutorial

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.datt16.compose_tutorial.ui.theme.ComposeTutorialTheme


val msgList = Messages().sampleMessageList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Conversation(msgList)
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            CardView(message)
        }
    }
}

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

        // isExpanded: カードの展開の制御に使用
        // 多分 ReactのuseStateみたいな感じ
        // こんな書き方も可能 var (isExpanded, setIsExpanded) = remember { mutableStateOf(false) }
        var isExpanded by remember { mutableStateOf(false) }

        // animateColorAsState: 色がアニメーションで変えることができる
        val surfaceColor by animateColorAsState(if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface)

        // Column: 縦に並べるレイアウト
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.title,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp),
                    // 三項演算子の代わりに if 使う
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }

        }

    }

}


@Preview(showBackground = true)

@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(messages = msgList)
    }
}