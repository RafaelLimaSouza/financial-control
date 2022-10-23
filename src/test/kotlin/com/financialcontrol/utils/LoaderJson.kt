package com.financialcontrol.utils

fun readJsonResource(fileName: String) = ClassLoader.getSystemResource("json/$fileName.json").readText()