package nocamelstyle.cuber.timeryou.ui.screens.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
//                BottomNavigationItem(
//                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
//                    label = { Text(stringResource(screen.resourceId)) },
//                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                    onClick = {
//                        navController.navigate(screen.route) {
//                            // Pop up to the start destination of the graph to
//                            // avoid building up a large stack of destinations
//                            // on the back stack as users select items
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            // Avoid multiple copies of the same destination when
//                            // reselecting the same item
//                            launchSingleTop = true
//                            // Restore state when reselecting a previously selected item
//                            restoreState = true
//                        }
//                    }
//                )
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