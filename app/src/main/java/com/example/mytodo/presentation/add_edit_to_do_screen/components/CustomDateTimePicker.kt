package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.runtime.Composable
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun CustomDateTimePicker() {
    val dialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Okay")
            negativeButton("Cancel")
        }
    ) {
        timepicker{

        }
    }
}