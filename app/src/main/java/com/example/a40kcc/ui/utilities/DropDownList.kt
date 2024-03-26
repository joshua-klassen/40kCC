package com.example.a40kcc.ui.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun DropDownList(
    itemList: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    preText: String? = "",
    onItemClick: (Int) -> Unit
) {
    var showDropdown by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        Column(
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
        ) {
            if (!preText.isNullOrBlank()) {
                Text(
                    text = preText, modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
        ) {
            Box(
                modifier = modifier.clickable { showDropdown = true }
            ) {
                Text(
                    text = itemList[selectedIndex], modifier = modifier,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Box {
                if (showDropdown) {
                    Popup(
                        alignment = Alignment.TopCenter,
                        properties = PopupProperties(
                            focusable = true,
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true
                        ),
                        onDismissRequest = { showDropdown = false }
                    ) {
                        Column(
                            modifier = modifier
                                .heightIn(max = 90.dp)
                                .verticalScroll(state = scrollState)
                                .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                                .background(MaterialTheme.colorScheme.onBackground),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            itemList.onEachIndexed { index, item ->
                                if (index != 0) {
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = MaterialTheme.colorScheme.outlineVariant
                                    )
                                }
                                Box(
                                    modifier = modifier
                                        .clickable {
                                            onItemClick(index)
                                            showDropdown = !showDropdown
                                        }
                                ) {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}