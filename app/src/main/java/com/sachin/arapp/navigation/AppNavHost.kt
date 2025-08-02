
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sachin.arapp.data.Drill
import androidx.hilt.navigation.compose.hiltViewModel
import com.sachin.arapp.ui.screens.ar.ArScreen
import com.sachin.arapp.ui.screens.drill.DrillDetailScreen
import com.sachin.arapp.ui.screens.drill.DrillSelectionScreen
import com.sachin.arapp.ui.screens.drill.DrillViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    val drillViewModel: DrillViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Drill App") }
            )
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "drill_selection",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("drill_selection") {
                DrillSelectionScreen(
                    viewModel = drillViewModel,
                    onDrillSelected = { drill ->
                        drillViewModel.selectedDrill = drill
                        navController.navigate("drill_detail")
                    }
                )
            }

            composable("drill_detail") {
                val drill: Drill = drillViewModel.selectedDrill!!
                DrillDetailScreen(
                    drill = drill,
                    onStartAr = { navController.navigate("ar_screen") }
                )
            }

            composable("ar_screen") {
                val drill = drillViewModel.selectedDrill
                Log.d("DrillRepository", "Drill: $drill")
                if (drill != null) {
                    ArScreen(modelFileName = drill.modelFileName)
                } else {
                    Text("No drill selected. Please go back and select a drill.")
                }
            }
        }
    }
}
