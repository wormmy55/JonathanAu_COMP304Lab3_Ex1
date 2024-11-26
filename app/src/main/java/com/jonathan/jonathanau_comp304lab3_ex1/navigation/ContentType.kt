package com.jonathan.jonathanau_comp304lab3_ex1.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}