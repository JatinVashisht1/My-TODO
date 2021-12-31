package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ActionWeekly(
    viewModel: AddEditToDoViewModel,
    task: ToDoEntity,
    sheetState: BottomSheetScaffoldState,
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                scope.launch {
                    if (sheetState.bottomSheetState.isCollapsed) {
                        sheetState.bottomSheetState.expand()
                    }else{
                        sheetState.bottomSheetState.collapse()
                    }
                }
            }, verticalAlignment = Alignment.CenterVertically
    ) {

        Text("Weekly")
    }

}