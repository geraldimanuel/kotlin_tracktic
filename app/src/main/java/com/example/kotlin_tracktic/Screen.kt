package com.example.kotlin_tracktic

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object MainScreen : Screen("main_screen")
    object StatisticScreen : Screen("statistic_screen")
    object TransactionScreen : Screen("transaction_screen")
    object NotificationScreen : Screen("notification_screen")
    object ProfileScreen : Screen("profile_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
