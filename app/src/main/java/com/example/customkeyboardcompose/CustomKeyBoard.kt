package com.example.customkeyboardcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customkeyboardcompose.ui.theme.backButtonBackgroundColor
import com.example.customkeyboardcompose.ui.theme.colorSecondaryBackground
import com.example.customkeyboardcompose.ui.theme.customkeyBoardbuttoncolor
import com.example.customkeyboardcompose.ui.theme.customkeyBoardbuttoncolorsecond


@Composable
fun CustomKeyBoard(
    onclick: (String) -> Unit,
    onDeleteIconClick: () -> Unit,
    onDoneIconClick: () -> Unit,
    onItemSelected: (String) -> Unit,
) {
    val keyboardNumbers = listOf(1, 2, 3, 15, 4, 5, 6, 16, 7, 8, 9, 17, 10, 0, 18, 19)
    val RowListItem = listOf(
        "g",
        "oz",
        "ml",
        "Large Egg",
        "Medium Egg",
        "Small Egg",
        "cup",
        "tbsp",
        "tsp",
        "fl oz",
        "lb"
    )

    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column {
        LazyRow {
            items(RowListItem) { item ->
                RowItem(
                    itemtext = item,
                    isSelected = selectedItem == item,
                    onClick = {
                        selectedItem = item
                        onItemSelected(item)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        ) {
            itemsIndexed(keyboardNumbers) { index, item ->
                KeyBoardButton(
                    item.toString(),
                    onDeleteIconClick = onDeleteIconClick,
                    onDoneIconClick = onDoneIconClick,
                    onclick = onclick,
                )
            }
        }
    }
}

@Composable
fun KeyBoardButton(
    number: String,
    onclick: (String) -> Unit,
    onDeleteIconClick: () -> Unit,
    onDoneIconClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                if (number == "10") Color.Transparent
                else if (number == "18") Color.Transparent
                else if (number == "19") Color.White
                else if (number != "15" && number != "16" && number != "17" && number != "18" ) customkeyBoardbuttoncolor
                else customkeyBoardbuttoncolorsecond
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (number != "10" && number != "18") {
                    onclick(number)
                } else if (number == "10") {
                    onclick(".")
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        when (number) {
            "10" -> {
                Text(text = ".",
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }

            "15" -> {
                CustomIconButton(
                        icon = Icons.Default.HorizontalRule,
                onIconButtonClick = {
                    onclick("/")
                })
            }

            "16" -> {
                CustomIconButton(
                    icon = Icons.Default.SpaceBar,
                    onIconButtonClick = {
                        onclick(" ")
                    })
            }

            "17" -> {
                CustomIconButton(
                    icon = Icons.Default.Delete,
                    onIconButtonClick = {
                        onDeleteIconClick()
                    })
            }

            "18" -> {
                // Do nothing as requested
            }

            "19" -> {
                Text(
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onDoneIconClick()
                        }
                        .pointerInput(true) {
                            detectTapGestures(
                                onPress = { onDoneIconClick() }
                            )
                        },
                    text = "Done",
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }

            else -> {
                Text(text = number,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
fun CustomIconButton(icon: ImageVector, onIconButtonClick: () -> Unit) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onIconButtonClick()
            }
            .pointerInput(true) {
                detectTapGestures(
                    onPress = { onIconButtonClick() }
                )
            },
        tint = Color.White
    )
}


@Composable
fun RowItem(itemtext: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .wrapContentHeight()
            .wrapContentWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(if (isSelected) Color.White else Color.Gray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(7.dp)
        ) {
            Text(text = itemtext,
                color = if (isSelected) Color.Black else Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentWidth())
        }
    }
}



@Composable
fun CustomKeyBoard(onclick: (String) -> Unit, onDeleteIconClick: () -> Unit,onLongClick:()->Unit) {
    val keyboardNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 12)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        itemsIndexed(keyboardNumbers) { index, item ->
            KeyBoardButton1(
                item.toString(),
                onDeleteIconClick = onDeleteIconClick,
                onclick = onclick,
                onLongClick = onLongClick
            )
        }
    }
}

@Composable
fun KeyBoardButton1(number: String, onclick: (String) -> Unit, onDeleteIconClick: () -> Unit,onLongClick:()->Unit) {
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (number != "12" && number != "10") colorSecondaryBackground else Color.Transparent)
            .padding(vertical = 12.dp)
            .clickable(indication = null, interactionSource = remember {
                MutableInteractionSource()
            }) {
                if (number != "12" && number != "10") {
                    onclick(number)
                } else {
                    onclick(".")
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        if (number == "10") {
            Text(text = ".",
                color = Color.White,
                textAlign = TextAlign.Start
            )
        } else if (number == "12") {
            Icon(
                imageVector = Icons.Outlined.Backspace,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(indication = null, interactionSource = remember {
                        MutableInteractionSource()
                    }) {
                        onDeleteIconClick()
                    }
                    .pointerInput(true) {
                        detectTapGestures(
                            onLongPress = {
                                onLongClick()
                            },
                            onPress = {
                                onDeleteIconClick()
                            }
                        )
                    },
                tint = Color.White
            )
        } else {
            Text(text = number,
                color = Color.White,
                textAlign = TextAlign.Start)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomKeybordTextField(
    weight: String,
    onValueChange: (String) -> Unit,
    enable:Boolean=true,
    isFocused: (isFocus: Boolean) -> Unit,
    unit: String = "kg"
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .background(backButtonBackgroundColor)
            .fillMaxWidth()
            .border(1.dp, Color.White, RoundedCornerShape(4.dp))
            .padding(vertical = 7.dp, horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            value = weight,
            onValueChange = { input ->
                if (weight.isEmpty() && input == ".") {
                    return@BasicTextField
                }
                if (input.length <= 10) {
                    onValueChange(input)
                }
            },
            modifier = Modifier
                .weight(6f)
                .padding(end = 10.dp)
                .onFocusChanged {
                    if (it.isFocused || it.hasFocus || it.isCaptured) {
                        isFocused(true)
                        keyboardController?.hide()
                    }
                },
            singleLine = true,
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                color = Color.White,
            ),
            cursorBrush = SolidColor(Color.White),
            decorationBox = { innerTextField ->
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    keyboardController?.hide()
                }
            ), enabled = enable
        )
        Text(text = unit,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier.wrapContentWidth())
    }
}
