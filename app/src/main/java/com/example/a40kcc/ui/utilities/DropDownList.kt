package com.example.a40kcc.ui.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
                .wrapContentHeight()
        ) {
            Box(
                modifier = modifier
                    .clickable { showDropdown = true }
                    .defaultMinSize(minWidth = 50.dp)
            ) {
                Text(
                    text = itemList[selectedIndex],
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Box(modifier = modifier) {
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
                            modifier = Modifier
                                .heightIn(max = 90.dp)
                                .widthIn(max = 150.dp)
                                .verticalScroll(state = scrollState)
                                .background(MaterialTheme.colorScheme.background)
                                .wrapContentWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            itemList.onEachIndexed { index, item ->
                                if (index != 0) {
                                    HorizontalDivider(
                                        thickness = 1.dp
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