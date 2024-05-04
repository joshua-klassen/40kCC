package com.example.a40kcc.data.`object`

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.getResourceIdOrThrow

class DataObject(resources: Resources, headerID: Int, dataID: Int) {
    private var headers: Array<String> = resources.getStringArray(headerID)
    private var data: Map<String, Array<Any>> = emptyMap()

    init {
        val dataArray: TypedArray = resources.obtainTypedArray(dataID)
        var dataArrayIterator = 0
        while (dataArrayIterator < dataArray.length()) {
            val objectArray: TypedArray =
                resources.obtainTypedArray(dataArray.getResourceIdOrThrow(dataArrayIterator))
            var objectArrayIterator = 1
            val mapKey = objectArray.getString(0)!!
            var mapData: Array<Any> = emptyArray<Any>()
            while (objectArrayIterator < objectArray.length()) {
                val objectResourceId = objectArray.getResourceId(objectArrayIterator, 0)
                if (objectResourceId != 0) {
                    if (resources.getResourceTypeName(objectResourceId) == "drawable") {
                        mapData +=
                            ResourcesCompat.getDrawable(
                                resources,
                                objectResourceId,
                                resources.newTheme()
                            )!!
                    }
                } else {
                    objectArray.getString(objectArrayIterator)?.let {
                        mapData += it.replace(oldValue = "|", newValue = "\n")
                    }
                }
                objectArrayIterator++
            }
            data += Pair(mapKey, mapData)
            dataArrayIterator++
            objectArray.recycle()
        }
    }

    fun getHeaders(): Array<String> {
        return headers
    }

    fun getDataKeys(): Set<String> {
        return data.keys
    }

    fun getDataValue(key: String): Array<Any>? {
        return data[key]
    }
}