package nocamelstyle.cuber.timeryou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nocamelstyle.cuber.timeryou.R

@Composable
fun SelectorToolbar(
    cubeName: String,
    cubeCategory: String,
    openSettings: () -> Unit,
    selectCube: () -> Unit,
    selectCategory: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = openSettings) {
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier.weight(1f).clickable { selectCube() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = cubeName)
            Text(text = cubeCategory)
        }

        IconButton(onClick = selectCategory) {
            Icon(
                painter = painterResource(R.drawable.baseline_category_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}