package com.example.mytodo.presentation.to_do_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components.DeleteToDoButton
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components.EditToDoComponent
import com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components.InsertToDoButton
import com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components.LastUpdatedAndDeadline
import com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components.ToDoItemTaskAndDetail
import kotlinx.coroutines.launch

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    toDoItem: ToDoEntity,
    viewModel: ToDoListViewModel,
    navHostController: NavController,
    onDeletePressed: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Surface(
        color = if (isSystemInDarkTheme()) Color(183, 28, 28, 255) else Color(255, 224, 130, 255),
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        viewModel.insertToDo(
                            ToDoEntity(
                                isCompleted = !toDoItem.isCompleted,
                                task = toDoItem.task,
                                detail = toDoItem.detail,
                                id = toDoItem.id,
                                pin = toDoItem.pin,
                                timeStamp = toDoItem.timeStamp,
                                deadLineDate = toDoItem.deadLineDate,
                                deadLineTime = toDoItem.deadLineTime,
                                isMonthly = toDoItem.isMonthly,
                                isWeekly = toDoItem.isWeekly
                            )
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

               LastUpdatedAndDeadline(toDoItem = toDoItem)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomCheckBox(toDoItem = toDoItem, viewModel = viewModel, scope = scope,
                            modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp))

                        ToDoItemTaskAndDetail(toDoItem = toDoItem)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        DeleteToDoButton(modifier = Modifier.align(Alignment.CenterVertically), onDeletePressed = onDeletePressed)

                        InsertToDoButton(scope = scope, viewModel = viewModel , toDoItem = toDoItem )

                        EditToDoComponent(navHostController = navHostController, toDoItem = toDoItem)
                    }
                }
            }
        }
    }
}
