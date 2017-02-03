class Person(firstName: String) {
  println("Outer constructor")
  def this(firstName: String, lastName: String) {
    this(firstName)
    println("Inner constructor")
  }
  def talk() = println("Hi")
}

val bob = new Person("Bob")
println bob
val bobTate = new Person("Bob", "Tate")
println bobTate
