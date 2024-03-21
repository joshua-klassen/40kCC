package com.example.a40kcc.ui.screen

import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.painterResource
import androidx.core.content.res.getResourceIdOrThrow
import com.example.a40kcc.R

@Composable
fun DataScreen(resources: Resources, headerID: Int, dataID: Int, onBackClick: () -> Unit) {
    Column {
        val margin = dimensionResource(id = R.dimen.margin_small)
        Button(onClick = onBackClick,
            modifier = Modifier.padding(margin, margin)) {
            Column {
                Text("Back")
            }
        }

        val headerArray: Array<String> = stringArrayResource(id = headerID)
        val dataArray: TypedArray = resources.obtainTypedArray(dataID)

        Row(modifier = Modifier.padding(margin, margin)) {
            for (header in headerArray.iterator()) {
                Column(modifier = Modifier.padding(margin, margin)){
                    Text(
                        header,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        LazyColumn {
            items(dataArray.length()) { mission: Int ->
                Row(modifier = Modifier.padding(margin, margin)) {
                    val objectArray: TypedArray = resources.obtainTypedArray(dataArray.getResourceIdOrThrow(mission))
                    var i = 0
                    while(i<objectArray.length()){
                        Column(modifier = Modifier.padding(margin, margin)){
                            val objectResourceId = objectArray.getResourceId(i, 0)
                            if (objectResourceId != 0) {
                                if(resources.getResourceTypeName(objectResourceId) == "drawable") {
                                    Image(
                                        painter = painterResource(objectResourceId),
                                        contentDescription = "Deployment Image",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            else
                            {
                                objectArray.getString(i)?.let {
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                            i++
                        }
                    }
                    objectArray.recycle()
                }
            }
        }

    }
}