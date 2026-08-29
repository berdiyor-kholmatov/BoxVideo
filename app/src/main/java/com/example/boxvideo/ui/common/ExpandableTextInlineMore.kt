package com.example.boxvideo.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink

@Composable
fun ExpandableTextInlineMore(
    text: String,
    minimizedMaxLines: Int = 3
) {

    var expanded by remember {mutableStateOf(false)}

    var displayText by remember(text, minimizedMaxLines) {
        mutableStateOf(text)
    }

    var isCalculated by remember(text, minimizedMaxLines) {
        mutableStateOf(false)
    }

    Text(
        text = buildAnnotatedString {
            if (expanded) {
                append(text)
            } else {
                append(displayText)
                if (displayText != text) {
                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "custom_action",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            ),
                            linkInteractionListener = {
                                if (displayText != text) {
                                    expanded = !expanded
                                }
                            }
                        )
                    ) {
                        append("...more")
                    }
                }
            }
        },
        maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
        overflow = TextOverflow.Clip,
        onTextLayout = { result: TextLayoutResult ->
            if (!expanded && !isCalculated && result.hasVisualOverflow) {
                val endIndex = result.getLineEnd(
                    lineIndex = minimizedMaxLines - 1,
                    visibleEnd = true
                )

                val cutIndex = (endIndex - "...more".length)
                    .coerceAtLeast(0)

                displayText = text.take(cutIndex).trimEnd()
                isCalculated = true
            } else if (expanded ) {

            }
        }
    )
}