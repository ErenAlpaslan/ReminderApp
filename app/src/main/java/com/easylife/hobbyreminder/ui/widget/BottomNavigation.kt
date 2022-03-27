package com.easylife.hobbyreminder.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DisabledByDefault
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.easylife.hobbyreminder.ui.navigation.Screen

@Composable
fun BottomNavigation(
    items: List<Screen>,
    navController: NavController,
    modifier: Modifier = Modifier,
    selectedColor: Color,
    unselectedColor: Color
) {

    Row(
        modifier = modifier.height(70.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavItem(
                route = screen.route,
                label = screen.label,
                icon = screen.icon ?: Icons.Rounded.DisabledByDefault,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                selected = currentRoute == screen.route
            ) {
                if (currentRoute != screen.route) {
                    navController.graph.startDestinationRoute?.let { item ->
                        navController.popBackStack(
                            item, false
                        )
                    }
                }
                if (currentRoute != screen.route) {
                    navController.navigate(screen.route){
                        launchSingleTop = true
                    }
                }
            }
        }
    }

}

@Composable
fun BottomNavItem(
    route: String?,
    label: Int,
    icon: ImageVector,
    selectedColor: Color,
    unselectedColor: Color,
    selected: Boolean,
    onClick: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick(route)
            }
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /*Image(
            imageVector = icon,
            contentDescription = "icon",
            colorFilter = ColorFilter.tint(
                if (selected) selectedColor else unselectedColor
            ),
            modifier = Modifier.size(24.dp)
        )*/
        Text(
            text = stringResource(id = label),
            color = if (selected) selectedColor else unselectedColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (selected) {
            Canvas(modifier = Modifier
                .size(8.dp),
                onDraw = {
                    drawCircle(color = selectedColor, radius = 8f)
                })
        }
    }
}