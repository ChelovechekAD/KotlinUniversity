package com.example.lab3.enums

enum class DayOfWeekRu(val russianName: String) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    companion object {
        fun fromEnglishName(englishName: String): String {
            return values().find { it.name.equals(englishName, ignoreCase = true) }?.russianName
                ?: "Неизвестный день"
        }
    }
}