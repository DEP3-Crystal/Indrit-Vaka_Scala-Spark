# Companion object

A companion object in Scala is an object that’s declared in the same file as a class, and has the same name as the
class. For instance, when the following code is saved in a file named Pizza.scala, the Pizza object is considered to be
a companion object to the Pizza class:

```scala worksheet
class Pizza {
}

object Pizza {
}
```

This has several benefits. First, a companion object and its class can access each other’s private members (fields and
methods). This means that the printFilename method in this class will work because it can access the HiddenFilename
field in its companion object:

```scala worksheet

class SomeClass {
  def printFilename() = {
    println(SomeClass.HiddenFilename)
  }
}

object SomeClass {
  private val HiddenFilename = "/tmp/foo.bar"
}
```

A companion object offers much more functionality than this, and we’ll demonstrate a few of its most important features
in the rest of this lesson.

Creating new instance