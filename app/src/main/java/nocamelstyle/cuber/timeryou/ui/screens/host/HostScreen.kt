package nocamelstyle.cuber.timeryou.ui.screens.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nocamelstyle.cuber.timeryou.R
import nocamelstyle.cuber.timeryou.ui.screens.statistics.StatisticsScreen
import nocamelstyle.cuber.timeryou.ui.screens.timer.TimerScreenWrapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == "timer" } == true,
                    onClick = {
                        navController.navigate("timer") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_timer_24),
                            contentDescription = null
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == "records" } == true,
                    onClick = {
                        navController.navigate("records") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_format_list_bulleted_24),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    ) { paddings ->
        NavHost(
            navController = navController,
            startDestination = "timer",
            modifier = Modifier.padding(paddings)
        ) {
            composable("timer") { TimerScreenWrapper() }
            composable("records") { StatisticsScreen() }
        }
    }
}