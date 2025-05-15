package org.example

import kotlin.math.PI
import kotlin.random.Random

/**
 * Exercise 1 - Collections
 */
fun exercise1() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalNumbers = greenNumbers.size + redNumbers.size
    println("Total numbers: $totalNumbers")
}

/**
 * Exercise 2 - Collections
 */
fun exercise2() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * Exercise 3 - Loops
 */
fun exercise3() {
    var pizzaSlices = 0
    while (pizzaSlices < 8) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
}

/**
 * Exercise 4 - Loops
 */
fun exercise4() {
    for (i in 1..100) {
        when {
            i % 3 == 0 && i % 5 == 0 -> println("fizzbuzz")
            i % 3 == 0 -> println("fizz")
            i % 5 == 0 -> println("buzz")
            else -> println(i)
        }
    }
}

/**
 * Exercise 5 - Loops
 */
fun exercise5() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (word in words) {
        if (word.startsWith("l")) {
            println(word)
        }
    }
}

/**
 * Exercise 6 - Functions
 */
fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

fun exercise6() {
    println(circleArea(2))
}

/**
 * Exercise 7 - Functions
 */
fun circleArea2(radius: Int): Double = PI * radius * radius

fun exercise7() {
    println(circleArea2(2))
}

/**
 * Exercise 8 - Functions
 */
fun intervalInSeconds(hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Int =
    ((hours * 60) + minutes) * 60 + seconds

fun exercise8() {
    println(intervalInSeconds(1, 20, 15))
    println(intervalInSeconds(minutes = 1, seconds = 25))
    println(intervalInSeconds(hours = 2))
    println(intervalInSeconds(minutes = 10))
    println(intervalInSeconds(hours = 1, seconds = 1))
}

/**
 * Exercise 9 - Classes
 */
data class Employee(val name: String, var salary: Int)

fun exercise9() {
    val emp = Employee("Mary", 20)
    println(emp)
    emp.salary += 10
    println(emp)
}

/**
 * Exercise 10 - Classes
 */

data class Person(val name: Name, val address: Address, val ownsAPet: Boolean = true)
data class Name(val firstName: String, val lastName: String)
data class City(val name: String, val country: String)
data class Address(val street: String, val city: City)


fun exercise10() {
    val person = Person(
        Name("John", "Smith"),
        Address("123 Fake Street", City("Springfield", "US")),
        ownsAPet = false
    )
    println(person)
}

/**
 * Exercise 11 - Classes
 */
class RandomEmployeeGenerator(var minSalary: Int, var maxSalary: Int) {
    private val names = listOf("John", "Mary", "Bob", "Alice", "Tom", "Kate")

    fun generateEmployee(): Employee {
        val randomName = names.random()
        val randomSalary = Random.nextInt(from = minSalary, until = maxSalary + 1)
        return Employee(randomName, randomSalary)
    }
}

fun exercise11() {
    val empGen = RandomEmployeeGenerator(10, 30)
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())
    empGen.minSalary = 50
    empGen.maxSalary = 100
    println(empGen.generateEmployee())
}

/**
 * Exercise 12 - Null safety
 */
fun employeeById(id: Int) = when (id) {
    1 -> Employee("Mary", 20)
    2 -> null
    3 -> Employee("John", 21)
    4 -> Employee("Ann", 23)
    else -> null
}

fun salaryById(id: Int): Int = employeeById(id)?.salary ?: 0

fun main() {
    println("Exercise 1 - Collections - 1")
    exercise1()

    println("\nExercise 2 - Collections - 2")
    exercise2()

    println("\nExercise 3 - Loops - 1")
    exercise3()

    println("\nExercise 4 - Loops - 2")
    exercise4()

    println("\nExercise 5 - Loops - 3")
    exercise5()

    println("\nExercise 6 - Functions - 1")
    exercise6()

    println("\nExercise 7 - Functions - 2")
    exercise7()

    println("\nExercise 8 - Functions - 3")
    exercise8()

    println("\nExercise 9 - Classes - 1")
    exercise9()

    println("\nExercise 10 - Classes - 2")
    exercise10()

    println("\nExercise 11 - Classes - 3")
    exercise11()

    println("\nExercise 12 - Null safety - 1")
    println("Sum of salaries for employees with IDs 1-5: ${(1..5).sumOf { id -> salaryById(id) }}")
    println("Individual salaries:")
    for (id in 1..5) {
        println("Employee $id: ${salaryById(id)}")
    }
}