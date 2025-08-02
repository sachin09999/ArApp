package com.sachin.arapp.ui.screens.drill

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachin.arapp.data.Drill

@Composable
fun DrillSelectionScreen(
    viewModel: DrillViewModel = hiltViewModel(),
    onDrillSelected: (Drill) -> Unit
) {
    val drills by viewModel.drills.collectAsState()
    val selectedDrillFromVm = viewModel.selectedDrill


    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .systemBarsPadding()
    ) {
        Text(
            text = "Select Drill",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box {

            OutlinedTextField(
                value = selectedDrillFromVm?.name ?: "Select a drill",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
                    .shadow(elevation = 0.dp, shape = RoundedCornerShape(8.dp)),
                readOnly = true,
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Drill Dropdown"
                    )
                },
                singleLine = true,
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                drills.forEach { drill ->
                    DropdownMenuItem(
                        text = { Text(drill.name) },
                        onClick = {
                            viewModel.selectedDrill = drill
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedDrillFromVm?.let { onDrillSelected(it) }
            },
            enabled = selectedDrillFromVm != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start AR Drill")
        }
    }
}
