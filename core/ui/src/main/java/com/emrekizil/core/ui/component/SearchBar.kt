package com.emrekizil.core.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.emrekizil.core.ui.R
import com.emrekizil.core.ui.theme.SpaceWatchTheme
import com.emrekizil.core.ui.util.clickableWithoutRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    updateValue: (String) -> Unit,
    @StringRes placeholderText: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = { value ->
            updateValue(value)
        },
        singleLine = true,
        textStyle = TextStyle(color = SpaceWatchTheme.colors.textFieldTextColor),
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                shape = CircleShape,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = stringResource(placeholderText),
                        color = SpaceWatchTheme.colors.placeholderColor
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.feature_list_search_placeholder),
                        tint = SpaceWatchTheme.colors.textFieldIconTint
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickableWithoutRipple {
                                updateValue("")
                            },
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(R.string.feature_list_clear_search),
                            tint = SpaceWatchTheme.colors.textFieldIconTint
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SpaceWatchTheme.colors.textFieldBackgroundColor,
                    unfocusedContainerColor =  SpaceWatchTheme.colors.textFieldBackgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLeadingIconColor = SpaceWatchTheme.colors.textFieldIconTint,
                    unfocusedLeadingIconColor = SpaceWatchTheme.colors.unfocusedTextFieldIconTint,
                    focusedTrailingIconColor = SpaceWatchTheme.colors.textFieldIconTint,
                    unfocusedTrailingIconColor = SpaceWatchTheme.colors.unfocusedTextFieldIconTint,
                    cursorColor = SpaceWatchTheme.colors.textFieldTextColor
                ),
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource
            )
        }
    )
}