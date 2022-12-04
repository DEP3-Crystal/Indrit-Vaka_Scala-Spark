var amount = -100
amount match {
  case 50 => println("$50")
  case 100 => println("$100")
  case _ => println("not pattern match")
}
amount match {

  case a if a == 50 => print("bill is == 50")
  case a if a == 100 => print("bill is == 100")
  case a => println("The amount is: " + a)
}

// expresion match
var result: String = amount match {

  case a if a == 50 => "bill is == 50"
  case a if a == 100 => ("bill is == 100")
  case a => ("The amount is: " + a)
}
print(result)