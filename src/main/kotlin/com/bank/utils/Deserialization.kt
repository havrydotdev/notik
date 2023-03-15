package com.bank.utils

import com.google.gson.Gson

fun deserializeJson(json: String): Map<String, String> = Gson().fromJson<Map<String, String>>(json, MutableMap::class.java)